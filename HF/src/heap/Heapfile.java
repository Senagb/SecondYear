package heap;

import global.PageId;
import global.RID;
import global.SystemDefs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import bufmgr.BufMgrException;
import bufmgr.BufferPoolExceededException;
import bufmgr.HashEntryNotFoundException;
import bufmgr.HashOperationException;
import bufmgr.InvalidBufferException;
import bufmgr.InvalidFrameNumberException;
import bufmgr.PageNotReadException;
import bufmgr.PagePinnedException;
import bufmgr.PageUnpinnedException;
import bufmgr.ReplacerException;
import diskmgr.DiskMgrException;
import diskmgr.DuplicateEntryException;
import diskmgr.FileIOException;
import diskmgr.FileNameTooLongException;
import diskmgr.InvalidPageNumberException;
import diskmgr.InvalidRunSizeException;
import diskmgr.OutOfSpaceException;
import diskmgr.Page;

public class Heapfile {

	private LinkedList<PageId> pages = new LinkedList<PageId>();
	private ArrayList<PageId> pagesWithFreeSpace = new ArrayList<PageId>();
	private HFPage page = new HFPage();
	private PageId firstDirPageId;
	private boolean fileDeleted;
	private String filename = "";

	public Heapfile(String name) throws FileIOException,
			InvalidPageNumberException, DiskMgrException, IOException,
			FileNameTooLongException, InvalidRunSizeException,
			DuplicateEntryException, OutOfSpaceException,
			BufferPoolExceededException, HashOperationException,
			ReplacerException, HashEntryNotFoundException,
			InvalidFrameNumberException, PagePinnedException,
			PageUnpinnedException, PageNotReadException, BufMgrException,
			HFDiskMgrException, HFBufMgrException, HFException {
		boolean found = false;
		if (name == null || name.equals("")) {
			filename = "temp";
		} else {
			filename = name;
			found = true;
		}

		firstDirPageId = null;
		firstDirPageId = get_file_entry(filename);

		if (firstDirPageId == null) {
			firstDirPageId = newPage(page, 1);
			if (firstDirPageId == null)
				throw new HFException(null, "can't make new page");
			addFileEntry(filename, firstDirPageId);
			HFPage hfp = new HFPage();
			hfp.init(firstDirPageId, new Page(page.getHFpageArray()));
			insert(firstDirPageId, hfp);
			PageId id = new PageId(firstDirPageId.pid);
			HFPage hf = new HFPage();

			for (int i = 0; i < 5; i++) {
				unpinPage(id, true);
				id = newPage(page, 1);// /////////////////////////////////////////////////
				hf.init(id, new Page(page.getHFpageArray()));// //////////////////////////
				hfp.setNextPage(id);
				insert(id, hf);
				hf.setPrevPage(hfp.getCurPage());
				hfp.init(id, new Page(hf.getHFpageArray()));

			}
			unpinPage(id, true);
		} else {
			page = new HFPage();
			pinPage(firstDirPageId, page, false);
			page.setCurPage(firstDirPageId);
			unpinPage(firstDirPageId, true);
			PageId id = page.getNextPage();
			insert(firstDirPageId, page);
			while (id.pid != -1) {
				HFPage hf = new HFPage();
				pinPage(id, hf, false);
				insert(id, hf);
				unpinPage(id, true);
				id = hf.getNextPage();
			}
		}

	}

	private void insert(PageId id, HFPage p) throws IOException {

		if (p.available_space() > 28) {
			pagesWithFreeSpace.add(id);
		}
		pages.add(id);
	}

	public int getRecCnt() throws InvalidSlotNumberException,
			InvalidTupleSizeException, HFBufMgrException, IOException,
			ReplacerException, HashOperationException, PageUnpinnedException,
			InvalidFrameNumberException, PageNotReadException,
			BufferPoolExceededException, PagePinnedException, BufMgrException,
			HashEntryNotFoundException

	{
		int answer = 0;
		Scan s = new Scan(this);
		Tuple t = s.getNext(new RID());
		while (t != null) {
			answer++;
			t = s.getNext(new RID());
		}
		s.closescan();
		return answer;
	}

	private void pinPage(PageId pageno, Page page, boolean emptyPage)
			throws HFBufMgrException {

		try {
			SystemDefs.JavabaseBM.pinPage(pageno, page, emptyPage);
		} catch (Exception e) {
			throw new HFBufMgrException(e, " pinPage was failed");
		}

	}

	private void unpinPage(PageId pageno, boolean dirty)
			throws HFBufMgrException {

		try {
			SystemDefs.JavabaseBM.unpinPage(pageno, dirty);
		} catch (Exception e) {
			throw new HFBufMgrException(e, "unpinPage was failed");
		}

	}

	private void deleteFileEntry(String filename) throws HFDiskMgrException {
		try {
			SystemDefs.JavabaseDB.delete_file_entry(filename);
		} catch (Exception e) {
			throw new HFDiskMgrException(e,
					"Heapfile.java: delete_file_entry() failed");
		}

	}

	public boolean deleteRecord(RID rid) throws HFBufMgrException, IOException,
			InvalidSlotNumberException {
		PageId id = rid.pageNo;
		HFPage f = new HFPage();
		pinPage(id, f, false);
		f.deleteRecord(rid);
		unpinPage(id, true);
		return true;

	}

	/**
	 * Delete the file from the database.
	 * 
	 * @exception InvalidSlotNumberException
	 *                invalid slot number
	 * @exception InvalidTupleSizeException
	 *                invalid tuple size
	 * @exception FileAlreadyDeletedException
	 *                file is deleted already
	 * @exception HFBufMgrException
	 *                exception thrown from bufmgr layer
	 * @exception HFDiskMgrException
	 *                exception thrown from diskmgr layer
	 * @exception IOException
	 *                I/O errors
	 * @throws DiskMgrException
	 * @throws BufMgrException
	 * @throws HashEntryNotFoundException
	 * @throws PageUnpinnedException
	 * @throws PagePinnedException
	 * @throws BufferPoolExceededException
	 * @throws PageNotReadException
	 * @throws InvalidFrameNumberException
	 * @throws HashOperationException
	 * @throws ReplacerException
	 * @throws InvalidBufferException
	 */
	public void deleteFile() throws InvalidSlotNumberException,
			FileAlreadyDeletedException, InvalidTupleSizeException,
			HFBufMgrException, HFDiskMgrException, IOException,
			InvalidBufferException, ReplacerException, HashOperationException,
			InvalidFrameNumberException, PageNotReadException,
			BufferPoolExceededException, PagePinnedException,
			PageUnpinnedException, HashEntryNotFoundException, BufMgrException,
			DiskMgrException {
		if (fileDeleted)
			throw new FileAlreadyDeletedException(null,
					"file is already deleted");

		fileDeleted = true;
		deleteFileEntry(filename);
		HFPage temp = new HFPage();
		PageId t = new PageId(firstDirPageId.pid);
		while (t.pid != -1) {
			pinPage(t, temp, false);
			unpinPage(t, true);
			SystemDefs.JavabaseBM.freePage(new PageId(t.pid));
			t = temp.getNextPage();

		}
	}

	public Tuple getRecord(RID rid) throws HFBufMgrException,
			InvalidSlotNumberException, IOException {
		PageId id = rid.pageNo;
		HFPage p = new HFPage();
		pinPage(new PageId(id.pid), p, false);
		Tuple t = p.getRecord(rid);
		unpinPage(new PageId(id.pid), true);
		return t;

	}

	public Scan openScan() throws ReplacerException, HashOperationException,
			PageUnpinnedException, InvalidFrameNumberException,
			PageNotReadException, BufferPoolExceededException,
			PagePinnedException, BufMgrException, IOException,
			HashEntryNotFoundException {
		return new Scan(this);
	}

	public boolean updateRecord(RID rid, Tuple newtuple) throws Exception {
		// boolean done = false;
		PageId id = rid.pageNo;
		HFPage p = new HFPage();
		try {
			pinPage(id, p, false);
		} catch (HFBufMgrException e) {
			throw new HFBufMgrException(null, "HFBM EX");
		}
		// p.setCurPage(id);
		Tuple t;
		try {
			t = p.returnRecord(rid);

			if (t == null)
				return false;
			if (t.getLength() == newtuple.getLength()) {
				t.tupleCopy(newtuple);
				// SystemDefs.JavabaseBM.flushPage(id);
				unpinPage(id, true);

				// unpinPage(id, true);
				return true;
			} else {
				unpinPage(id, true);
				throw new InvalidUpdateException();
			}

		} catch (InvalidSlotNumberException e) {
			throw new InvalidSlotNumberException(null, "Slot number prob");
		} catch (IOException e) {
		} catch (HFBufMgrException e) {
			throw new HFBufMgrException(null, "HFBM EX");

		}
		return fileDeleted;
	}

	private PageId get_file_entry(String filename) throws HFDiskMgrException {

		PageId tmpId = new PageId();

		try {
			tmpId = SystemDefs.JavabaseDB.get_file_entry(filename);
		} catch (Exception e) {
			throw new HFDiskMgrException(e,
					"Heapfile.java: get_file_entry() failed");
		}

		return tmpId;

	}

	private PageId newPage(Page page, int num) throws HFBufMgrException {

		PageId tmpId = new PageId();

		try {
			tmpId = SystemDefs.JavabaseBM.newPage(page, num);
		} catch (Exception e) {
			throw new HFBufMgrException(e, "Heapfile.java: newPage() failed");
		}

		return tmpId;

	}

	private void addFileEntry(String filename, PageId pageno)
			throws HFDiskMgrException {

		try {
			SystemDefs.JavabaseDB.add_file_entry(filename, pageno);
		} catch (Exception e) {
			throw new HFDiskMgrException(e,
					"Heapfile.java: add_file_entry() failed");
		}

	}

	private PageId getbest(int size) throws HFBufMgrException, IOException {
		HFPage p = new HFPage();
		PageId id = null;
		for (int i = 0; i < pagesWithFreeSpace.size(); i++) {
			id = pagesWithFreeSpace.get(i);
			pinPage(id, p, false);
			if (p.available_space() > size) {
				unpinPage(id, true);
				return id;
			}
			check(p, id);
			unpinPage(id, true);
		}

		id = pages.getLast();
		pinPage(id, p, false);
		p.setCurPage(id);
		HFPage ptemp = new HFPage();
		PageId tempid = newPage(ptemp, 1);
		insert(tempid, ptemp);
		p.setNextPage(tempid);
		ptemp.setPrevPage(id);
		unpinPage(id, true);
		unpinPage(tempid, true);

		return tempid;

	}

	private void check(HFPage p, PageId m) throws IOException {
    	  if(!pagesWithFreeSpace.contains(m)&&p.available_space()>0){
    		  pagesWithFreeSpace.add(m);
    	  }else{
    			  for(int i=0;i<pagesWithFreeSpace.size();i++){
    				  if(pagesWithFreeSpace.get(i)==m){
    					  if(p.available_space()<=0)
    					  pagesWithFreeSpace.remove(i);
    					  i=pagesWithFreeSpace.size();
    				  }
    			  }  
    	  }
	}

	public RID insertRecord(byte recPtr[]) throws Exception,
			SpaceNotAvailableException {
		PageId id;
		try {
			id = getbest(recPtr.length);
			HFPage p = new HFPage();
			pinPage(id, p, false);
			p.setCurPage(id);
			RID r = p.insertRecord(recPtr);
			if (r == null) {
				throw new SpaceNotAvailableException();
			}
			unpinPage(id, true);
			return r;
		} catch (HFBufMgrException e) {
			throw new HFBufMgrException(null, "HFBM EX");
		} catch (IOException e) {
			throw new IOException();
		}
	}

	public PageId getFirstPageId() {

		return firstDirPageId;
	}
}
