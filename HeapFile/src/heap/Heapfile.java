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

/**
 * Heap file class that contains group of pages
 * 
 * @variables pages linkedlist contains all the pages id in thsi file
 * @variable pagesWithFreeSpace contain the pages id with free space
 * 
 * */
public class Heapfile {

	private LinkedList<PageId> pages = new LinkedList<PageId>();// contain the
																// pages ids
																// that exists
																// in the file
	private ArrayList<PageId> pagesWithFreeSpace = new ArrayList<PageId>();// contain
																			// the
																			// ids
																			// of
																			// the
																			// pages
																			// in
																			// the
																			// file
																			// and
																			// free
	private HFPage page = new HFPage();// temp HFpage
	private PageId firstDirPageId;// first id in the file
	private boolean fileDeleted;// boolean to check if the file iss deleted or
								// not
	private String filename = "";// file name

	// constructor of the file take the name of the file as a parameter

	public Heapfile(String name) throws FileIOException,
			InvalidPageNumberException, DiskMgrException, IOException,
			FileNameTooLongException, InvalidRunSizeException,
			DuplicateEntryException, OutOfSpaceException,
			BufferPoolExceededException, HashOperationException,
			ReplacerException, HashEntryNotFoundException,
			InvalidFrameNumberException, PagePinnedException,
			PageUnpinnedException, PageNotReadException, BufMgrException,
			HFDiskMgrException, HFBufMgrException, HFException {

		if (name == null || name.equals("")) {// check the name which entered
			filename = "temp";
		} else {
			filename = name;

		}

		firstDirPageId = null;
		firstDirPageId = get_file_entry(filename);// get the first page id of
													// the file

		if (firstDirPageId == null) {// if null this means this file donot
										// exists on the hard
			firstDirPageId = newPage(page, 1);// get a new page
			if (firstDirPageId == null)// null means the disk is full
				throw new HFException(null, "can't make new page");
			addFileEntry(filename, firstDirPageId);// add this entery to the
													// database
			HFPage hfp = new HFPage();// temp hfPage
			hfp.init(firstDirPageId, new Page(page.getHFpageArray()));// Initiate
																		// the
																		// hfp
			insert(firstDirPageId, hfp);// insert in the pages ids LL
			PageId id = new PageId(firstDirPageId.pid);
			HFPage hf = new HFPage();
			// loop to get 5 pages in this file as a start
			for (int i = 0; i < 5; i++) {
				unpinPage(id, true);// unpin the page
				id = newPage(page, 1);// get new page
				hf.init(id, new Page(page.getHFpageArray()));// Initiate the hf
				hfp.setNextPage(id);// set the next
				insert(id, hf);// insert this page
				hf.setPrevPage(hfp.getCurPage());// set the pervous
				hfp.init(id, new Page(hf.getHFpageArray()));// initiate with the
															// new page

			}
			unpinPage(id, true);// unpin the page
		} else {
			// this case the file already exists
			page = new HFPage();
			pinPage(firstDirPageId, page, false);// get the first page
			page.setCurPage(firstDirPageId);
			unpinPage(firstDirPageId, true);// unpin this page
			PageId id = page.getNextPage();
			insert(firstDirPageId, page);// insert this id in the LL
			// loop to get the rest of pages
			while (id.pid != -1) {
				HFPage hf = new HFPage();
				pinPage(id, hf, false);
				insert(id, hf);
				unpinPage(id, true);
				id = hf.getNextPage();
			}
		}

	}

	// to insert the pages in the LL depend on its free space
	private void insert(PageId id, HFPage p) throws IOException {

		if (p.available_space() > 28) {
			pagesWithFreeSpace.add(id);
		}
		pages.add(id);

	}

	// to get the number of the records
	public int getRecCnt() throws InvalidSlotNumberException,
			InvalidTupleSizeException, HFBufMgrException, IOException,
			ReplacerException, HashOperationException, PageUnpinnedException,
			InvalidFrameNumberException, PageNotReadException,
			BufferPoolExceededException, PagePinnedException, BufMgrException,
			HashEntryNotFoundException

	{
		// using the scan to scan all the pages
		int answer = 0;
		Scan s = new Scan(this);
		Tuple t = s.getNext(new RID());
		while (t != null) {
			answer++;
			t = s.getNext(new RID());
		}
		s.closescan();// close the scan
		return answer;
	}

	// pin page
	private void pinPage(PageId pageno, Page page, boolean emptyPage)
			throws HFBufMgrException {

		try {
			SystemDefs.JavabaseBM.pinPage(pageno, page, emptyPage);
		} catch (Exception e) {
			throw new HFBufMgrException(e, " pinPage was failed");
		}

	}

	// unpin page
	private void unpinPage(PageId pageno, boolean dirty)
			throws HFBufMgrException {

		try {
			SystemDefs.JavabaseBM.unpinPage(pageno, dirty);
		} catch (Exception e) {
			throw new HFBufMgrException(e, "unpinPage was failed");
		}

	}

	// delete the file entry
	private void deleteFileEntry(String filename) throws HFDiskMgrException {
		try {
			SystemDefs.JavabaseDB.delete_file_entry(filename);
		} catch (Exception e) {
			throw new HFDiskMgrException(e,
					"Heapfile.java: delete_file_entry() failed");
		}

	}

	// delete a reord from a page
	public boolean deleteRecord(RID rid) throws HFBufMgrException, IOException,
			InvalidSlotNumberException {
		PageId id = rid.pageNo;// get the page
		HFPage f = new HFPage();
		pinPage(id, f, false);// pin the required page
		f.deleteRecord(rid);// delete the record
		check(f, id);// check the page state
		unpinPage(id, true);// unpin the page
		return true;

	}

	// delete the whole file from the disk
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

		fileDeleted = true;// set the boolean to true
		deleteFileEntry(filename);// delet the entry
		HFPage temp = new HFPage();
		PageId t = new PageId(firstDirPageId.pid);
		// loop to delete all the pages
		while (t.pid != -1) {
			pinPage(t, temp, false);
			unpinPage(t, true);
			SystemDefs.JavabaseBM.freePage(new PageId(t.pid));
			t = temp.getNextPage();

		}
	}

	// return the record with specific rid
	public Tuple getRecord(RID rid) throws HFBufMgrException,
			InvalidSlotNumberException, IOException {
		PageId id = rid.pageNo;// get the page of thsi rid
		HFPage p = new HFPage();
		pinPage(new PageId(id.pid), p, false);
		Tuple t = p.getRecord(rid);// get the record from the page
		unpinPage(new PageId(id.pid), true);
		return t;// return the tuple

	}

	// open new scan
	public Scan openScan() throws ReplacerException, HashOperationException,
			PageUnpinnedException, InvalidFrameNumberException,
			PageNotReadException, BufferPoolExceededException,
			PagePinnedException, BufMgrException, IOException,
			HashEntryNotFoundException {
		return new Scan(this);
	}

	// update a record with specific rid
	public boolean updateRecord(RID rid, Tuple newtuple) throws Exception {

		PageId id = rid.pageNo;// get the specfic page
		HFPage p = new HFPage();
		try {
			pinPage(id, p, false);
		} catch (HFBufMgrException e) {
			throw new HFBufMgrException(null, "HFBM EX");
		}

		Tuple t;
		try {
			t = p.returnRecord(rid);// get the record

			if (t == null)
				return false;
			if (t.getLength() == newtuple.getLength()) {
				t.tupleCopy(newtuple);// copy tuple
				unpinPage(id, true);
				return true;// return true indicate the success
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
		return false;
	}

	// to get file entry
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

	// to get new page
	private PageId newPage(Page page, int num) throws HFBufMgrException {

		PageId tmpId = new PageId();

		try {
			tmpId = SystemDefs.JavabaseBM.newPage(page, num);
		} catch (Exception e) {
			throw new HFBufMgrException(e, "Heapfile.java: newPage() failed");
		}

		return tmpId;

	}

	// add file entery
	private void addFileEntry(String filename, PageId pageno)
			throws HFDiskMgrException {

		try {
			SystemDefs.JavabaseDB.add_file_entry(filename, pageno);
		} catch (Exception e) {
			throw new HFDiskMgrException(e,
					"Heapfile.java: add_file_entry() failed");
		}

	}

	// return the best candidate to insert the record in
	private PageId getbest(int size) throws HFBufMgrException, IOException {
		HFPage p = new HFPage();
		PageId id = null;
		// loop on the free pages to get page with suitable space
		for (int i = 0; i < pagesWithFreeSpace.size(); i++) {
			id = pagesWithFreeSpace.get(i);
			pinPage(id, p, false);
			if (p.available_space() > size) {
				unpinPage(id, true);
				return id;
			}
			unpinPage(id, true);
		}
		// this means that no page is avilable
		id = pages.getLast();// get the id of the last
		pinPage(id, p, false);
		p.setCurPage(id);
		HFPage ptemp = new HFPage();
		PageId tempid = newPage(ptemp, 1);// get new page
		insert(tempid, ptemp);// insert in the LL
		p.setNextPage(tempid);// set the next of the last page
		ptemp.setPrevPage(id);// set the perv of the new page
		unpinPage(id, true);
		unpinPage(tempid, true);

		return tempid;

	}

	// check if the page need to be removed from the free candidate AL or not
	private void check(HFPage p, PageId m) throws IOException {
		if (!pagesWithFreeSpace.contains(m) && p.available_space() > 0) {
			pagesWithFreeSpace.add(m);
		} else {
			for (int i = 0; i < pagesWithFreeSpace.size(); i++) {
				if (pagesWithFreeSpace.get(i) == m) {
					if (p.available_space() <= 0)
						pagesWithFreeSpace.remove(i);
					i = pagesWithFreeSpace.size();
				}
			}

		}

	}

	// insert new record in a page
	public RID insertRecord(byte recPtr[]) throws Exception,
			SpaceNotAvailableException {
		PageId id;
		try {
			id = getbest(recPtr.length);// get thee id of the suitable page
			HFPage p = new HFPage();
			pinPage(id, p, false);
			p.setCurPage(id);
			RID r = p.insertRecord(recPtr);// insert the record
			if (r == null) {
				throw new SpaceNotAvailableException();
			}
			check(p, id);// update the AL
			unpinPage(id, true);
			return r;// return the rid of the new record
		} catch (HFBufMgrException e) {
			throw new HFBufMgrException(null, "HFBM EX");
		} catch (IOException e) {
			throw new IOException();
		}
	}

	// return the first page id
	public PageId getFirstPageId() {

		return firstDirPageId;
	}
}
