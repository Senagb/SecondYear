package heap;

import global.GlobalConst;
import global.PageId;
import global.RID;
import global.SystemDefs;

import java.io.IOException;

import bufmgr.BufMgrException;
import bufmgr.BufferPoolExceededException;
import bufmgr.HashEntryNotFoundException;
import bufmgr.HashOperationException;
import bufmgr.InvalidFrameNumberException;
import bufmgr.PageNotReadException;
import bufmgr.PagePinnedException;
import bufmgr.PageUnpinnedException;
import bufmgr.ReplacerException;

/**
 * Scan class to iterate on the heap file record by record
 */
public class Scan implements GlobalConst {
	public boolean isFirstScan = false;// boolean to check entered in the scan
										// or not for the first time
	private PageId id = null;// page id for the iterator
	private HFPage hPage = new HFPage();
	RID currentRecord;
	RID firstRec;

	/**
	 * Constructor of the Scan class
	 * 
	 * @param Heapfile
	 * */

	public Scan(Heapfile hf) throws IOException, ReplacerException,
			HashOperationException, PageUnpinnedException,
			InvalidFrameNumberException, PageNotReadException,
			BufferPoolExceededException, PagePinnedException, BufMgrException,
			HashEntryNotFoundException {
		id = hf.getFirstPageId(); // set the id with first page in the file
		SystemDefs.JavabaseBM.pinPage(id, hPage, false);// pin the page to be
														// used
		hPage.setCurPage(id);// set the current id by the id
		firstRec = hPage.firstRecord();// get the first record in the file
		isFirstScan = true;// initialize the boolean
	}

	/**
	 * this method advance the courser of the scan
	 * 
	 * @param RID
	 */
	public Tuple getNext(RID rid) throws InvalidTupleSizeException,
			IOException, InvalidSlotNumberException, ReplacerException,
			HashOperationException, PageUnpinnedException,
			InvalidFrameNumberException, PageNotReadException,
			BufferPoolExceededException, PagePinnedException, BufMgrException,
			HashEntryNotFoundException {
		if (isFirstScan) {// check if first time to enter the scan

			currentRecord = hPage.firstRecord();// get the first record

			rid.copyRid(currentRecord);// copy the record to the sent rid

			Tuple t = hPage.getRecord(currentRecord); // get the tple

			isFirstScan = false;// set the boolean to false

			return t;
		} else {
			currentRecord = hPage.nextRecord(currentRecord);// get the next
															// record
			if (currentRecord == null) {// if true this means that the page
										// records finished
				SystemDefs.JavabaseBM.unpinPage(id, true);// unpin the curent
															// page
				id = hPage.getNextPage();// get the next page id
				if (id.pid == -1) {// return null if end of file id == null
					return null;
				} else {

					SystemDefs.JavabaseBM.pinPage(id, hPage, false);// pin the
																	// new page
					currentRecord = hPage.firstRecord();// get the first record
					if (currentRecord == null) {// if true this means that we
												// reached and a page with no
												// record
						SystemDefs.JavabaseBM.unpinPage(id, true);// unpin this
																	// page
						return null;
					}
					// else if this page contains records
					rid.copyRid(currentRecord);// copy the currentrid to rid
					Tuple t = hPage.getRecord(currentRecord);// get the tuple
					return t;// return the tple
				}
			} else {
				rid.copyRid(currentRecord);// copy the currentrid to rid
				Tuple t = hPage.getRecord(currentRecord);// get the tuple
				return t;// return the tple
			}
		}

	}

	/**
	 * this method set the courser of the scan to specific rid
	 * 
	 * @param RID
	 */
	public boolean position(RID rid) throws InvalidTupleSizeException,
			IOException, InvalidSlotNumberException, ReplacerException,
			HashOperationException, PageUnpinnedException,
			InvalidFrameNumberException, PageNotReadException,
			BufferPoolExceededException, PagePinnedException, BufMgrException,
			HashEntryNotFoundException {

		if (rid != null) {

			currentRecord.copyRid(firstRec);// copy rid of the first to current
			RID r = new RID();
			while (this.getNext(r) != null) {// loop until you get the spcefic
												// rid and return true or return
												// false if not found
				if (r.equals(rid)) {
					rid.copyRid(currentRecord);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * close the scan and free its variables
	 */
	public void closescan() {
		isFirstScan = false;
		currentRecord = null;
		hPage = null;
		id = null;
		firstRec = null;
		// SystemDefs.JavabaseBM.unpinPage(id, false);
	}
}
