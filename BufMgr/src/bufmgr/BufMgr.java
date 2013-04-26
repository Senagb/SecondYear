package bufmgr;

import java.io.IOException;

import global.*;
import diskmgr.*;

public class BufMgr {

	String replacer = "";// replacement policy argument
	byte[][] pool = null;// pool array of frames
	Descriptor[] frameDescriptor = null;// array of the descriptor of buffers in
										// the pool
	Hashtable pageHashtable = null;// hash table to hash the frame position
	int numbufs = 0;// number of expected frames in the pool
	Queue replace = null;// Queue that contain the frame with pinCount==0

	/**
	 * Create the BufMgr object. Allocate pages (frames) for the buffer pool in
	 * main memory and make the buffer manager aware that the replacement policy
	 * is specified by replacerArg (i.e. Clock, LRU, MRU etc.).
	 * 
	 * @param numbufs
	 *            : number of buffers in the buffer pool.
	 * @param replacerArg
	 *            : name of the buffer replacement policy.
	 */
	public BufMgr(int numbufs, String replacerArg) throws Exception {
		// initialization of the buffer variables
		pool = new byte[numbufs][GlobalConst.MINIBASE_PAGESIZE];
		replacer = replacerArg;
		this.numbufs = numbufs;
		pageHashtable = new Hashtable(numbufs);
		SystemDefs.JavabaseBM = this;
		frameDescriptor = new Descriptor[numbufs];
		for (int j = 0; j < frameDescriptor.length; j++) {
			frameDescriptor[j] = new Descriptor(-1);
			frameDescriptor[j].setDirty(false);
		}
		// checking the replacement policy
		if (replacer == "Clock") {
			replace = new Queue();
		} else
			throw new UnsupportedOperationException(
					"unsupported replacement policy type");
		// enqueue all the frames
		for (int frameNumber = 0; frameNumber < numbufs; frameNumber++) {
			replace.enqueue(frameNumber);
		}
	}

	/**
	 * Pin a page. First check if this page is already in the buffer pool. If it
	 * is, increment the pin_count and return a pointer to this page. If the
	 * pin_count was 0 before the call, the page was a replacement candidate,
	 * but is no longer a candidate. If the page is not in the pool, choose a
	 * frame (from the set of replacement candidates) to hold this page, read
	 * the page (using the appropriate method from {\em diskmgr} package) and
	 * pin it. Also, you must write out the old page in chosen frame if it is
	 * dirty before reading new page. (You can assume that emptyPage==false for
	 * this assignment.)
	 * 
	 * @param pageId
	 *            : page number in the minibase.
	 * @param page
	 *            : the pointer point to the page.
	 * @param emptyPage
	 *            : true (empty page); false (non-empty page)
	 */
	public void pinPage(PageId pin_pgid, Page page, boolean emptyPage)
			throws Exception {
		try {
			int pos; // Position of the frame
			// if not found
			if (!pageHashtable.containsKey(pin_pgid.pid)) {
				// if there isn't any place to replace
				if (replace.isEmpty()) {
					throw new BufferPoolExceededException(null,
							"Buffer is Full");
				} else {
					// reading from disk
					pos = replace.dequeue();
					if (frameDescriptor[pos].getPage().pid != -1
							&& frameDescriptor[pos].isDirty())
						flushPage(new PageId(frameDescriptor[pos].getPage().pid));
					if (frameDescriptor[pos].getPage().pid != -1
							&& pageHashtable.containsKey(frameDescriptor[pos]
									.getPage().pid))
						pageHashtable
								.remove(frameDescriptor[pos].getPage().pid);
					page.setpage(pool[pos]);

					(SystemDefs.JavabaseDB).read_page(new PageId(pin_pgid.pid),
							page);
					frameDescriptor[pos] = new Descriptor(pin_pgid.pid);
					frameDescriptor[pos].increasePinCount();

					pageHashtable.put(pin_pgid.pid, pos);
				}
			} else {
				// found and increased the counter
				pos = pageHashtable.get(pin_pgid.pid);
				// check if the counter =0 remove from the queue
				if (frameDescriptor[pos].getPinCount() == 0)
					replace.remove(pos);
				frameDescriptor[pos].increasePinCount();
				page.setpage(pool[pos]);
			}
		} catch (InvalidPageNumberException e) {
			throw new InvalidPageNumberException(e, "invalid page number ");
		} catch (FileIOException e) {
			throw new FileIOException(e, "File IO exception in pinning");
		} catch (IOException e) {
			throw new IOExceptionBM(e, "IO Exception in pinning ");
		}
	}

	/**
	 * Unpin a page specified by a pageId. This method should be called with
	 * dirty==true if the client has modified the page. If so, this call should
	 * set the dirty bit for this frame. Further, if pin_count>0, this method
	 * should decrement it. If pin_count=0 before this call, throw an exception
	 * to report error. (For testing purposes, we ask you to throw an exception
	 * named PageUnpinnedException in case of error.) 2
	 * 
	 * @param pageId
	 *            : page number in the minibase.
	 * @param dirty
	 *            the dirty bit of the frame
	 */
	public void unpinPage(PageId pageId, boolean dirty) throws Exception {
		// if this page ID doesnot exists
		if (!pageHashtable.containsKey(pageId.pid)) {
			throw new HashEntryNotFoundException(null, "Page not in buffer");
		} else {
			int frameNum = pageHashtable.get(pageId.pid);
			// if unpinning frame with already pincount =0
			if (frameDescriptor[frameNum].getPinCount() == 0) {
				throw new PagePinnedException(null, "Pin count is already 0");
			}
			// set the dirty boolean and decrease the number of the count
			frameDescriptor[frameNum].setDirty(dirty);
			frameDescriptor[frameNum].decreasePinCount();
			// return it to the queue if the pin count ==0
			if (frameDescriptor[frameNum].getPinCount() == 0) {
				replace.enqueue(frameNum);
			}
		}
	}

	/**
	 * Allocate new pages. Call DB object to allocate a set of new pages and
	 * find a frame in the buffer pool for the first page and pin it. (This call
	 * allows a client of the Buffer Manager to allocate pages on disk.) If
	 * buffer is full, i.e., you can't find a frame for the first page, ask DB
	 * to deallocate all these pages, and return null.
	 * 
	 * @param firstpage
	 *            the address of the first page.
	 * @param howmany
	 *            total number of allocated new pages.
	 * 
	 * @return the first page id of the new pages. null, if error.
	 */
	public PageId newPage(Page firstpage, int howmany) throws Exception {
		PageId firstPageId = null;
		// if the number of pages less than 0 throw exception
		if (howmany <= 0) {
			throw new InvalidRunSizeException(null,
					"error .. invalid number of pages entered");

		} else if (firstpage == null) {
			// if the firstpage == null this mean wrong entery and throw
			// exception
			throw new PageException(null, "check the page inialisation");

		} else if (pageHashtable.size() < numbufs) {// check if there is place
													// and then allocate and pin
													// it
			firstPageId = new PageId();
			(SystemDefs.JavabaseDB).allocate_page(firstPageId, howmany);
			pinPage(firstPageId, firstpage, false);
		}
		return firstPageId;
	}

	/**
	 * This method should be called to delete a page that is on disk. This
	 * routine must call the method in diskmgr package to deallocate the page.
	 * 
	 * @param globalPageId
	 *            the page number in the data base.
	 */
	public void freePage(PageId globalPageId) throws Exception {

		try {
			// check the key if exists
			if (pageHashtable.containsKey(globalPageId.pid)) {
				int index = pageHashtable.get(globalPageId.pid);// get position
				int x = frameDescriptor[index].getPinCount();// get number of
																// the pincount
				if (x > 1) {// if more than 1 throw error that means more than
							// one using the frame
					throw new PagePinnedException(null,
							"the pincount of the page = " + x);
				} else {
					if (x == 1)// decrease pincount
						unpinPage(globalPageId, false);
					if (x == 0)// enqeueu in the frame place
						replace.enqueue(index);
					// free
					(SystemDefs.JavabaseDB).deallocate_page(new PageId(
							globalPageId.pid));
					// remove from hash table
					pageHashtable.remove(globalPageId.pid);
					// reintilaz the descriptor
					frameDescriptor[index].setDirty(false);
					frameDescriptor[index].setPage(-1);
					frameDescriptor[index].setPinCount(0);
				}
			} else {
				// else deallocate the place from the hard
				(SystemDefs.JavabaseDB).deallocate_page(new PageId(
						globalPageId.pid));

			}
		} catch (InvalidRunSizeException e) {
			throw new InvalidPageNumberException(e,
					"invalid id number exception");
		} catch (InvalidPageNumberException e) {
			throw new InvalidPageNumberException(e, "invalid page ID");
		} catch (FileIOException e) {
			throw new FileIOException(e, "IO Exception");
		} catch (DiskMgrException e) {
			throw new DiskMgrException(e, "problem in disk manager");
		} catch (IOException e) {
			throw new IOExceptionBM(e, "IO error");
			// TODO Auto-generated catch block

		}

	};

	/**
	 * Used to flush a particular page of the buffer pool to disk. This method
	 * calls the write_page method of the diskmgr package.
	 * 
	 * @param pageid
	 *            the page number in the database.
	 */
	public void flushPage(PageId pageid) throws Exception {
		try {
			// flush the dirty page
			int pos = pageHashtable.get(pageid.pid);
			Page page = new Page(pool[pos]);

			(SystemDefs.JavabaseDB).write_page(new PageId(pageid.pid), page);
		} catch (InvalidPageNumberException e) {
			throw new InvalidPageNumberException(e, "invalid page number ");
		} catch (FileIOException e) {
			throw new FileIOException(e, "File IO exception in pinning");
		} catch (IOException e) {
			throw new IOExceptionBM(e, "IO Exception in pinning ");
		}

	}

	public void flushAllPages() throws Exception {
		for (int i = 0; i < frameDescriptor.length; i++)
			if (frameDescriptor[i].getPage().pid != -1) {
				flushPage(new PageId(frameDescriptor[i].getPage().pid));
			}
	}

	public int getNumUnpinnedBuffers() {
		// TODO Auto-generated method stub
		return replace.size();
	}

}