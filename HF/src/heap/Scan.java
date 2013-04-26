package heap;

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

import diskmgr.Page;

import global.GlobalConst;
import global.PageId;
import global.RID;
import global.SystemDefs;

public class Scan implements GlobalConst {
	public boolean isFirstScan = false;
	PageId id = null;
	HFPage hPage = new HFPage();
	RID currentRecord;

	public Scan(Heapfile hf) throws IOException, ReplacerException,
			HashOperationException, PageUnpinnedException,
			InvalidFrameNumberException, PageNotReadException,
			BufferPoolExceededException, PagePinnedException, BufMgrException,
			HashEntryNotFoundException {
		id = hf.getFirstPageId();
		SystemDefs.JavabaseBM.pinPage(id, hPage, false);
		hPage.setCurPage(id);
		isFirstScan = true;
	}

	public Tuple getNext(RID rid) throws InvalidTupleSizeException,
			IOException, InvalidSlotNumberException, ReplacerException,
			HashOperationException, PageUnpinnedException,
			InvalidFrameNumberException, PageNotReadException,
			BufferPoolExceededException, PagePinnedException, BufMgrException,
			HashEntryNotFoundException {
		if (isFirstScan) {
			currentRecord = hPage.firstRecord();
			rid.copyRid(currentRecord);
			Tuple t = hPage.getRecord(currentRecord);
			isFirstScan = false;
			return t;
		} else {
			currentRecord = hPage.nextRecord(currentRecord);
			if (currentRecord == null) {
				SystemDefs.JavabaseBM.unpinPage(id, true);
				id = hPage.getNextPage();
				if (id.pid == -1) {// return null if end of file id == null
					return null;
				} else {

					SystemDefs.JavabaseBM.pinPage(id, hPage, false);
					currentRecord = hPage.firstRecord();
					if (currentRecord == null) {
						SystemDefs.JavabaseBM.unpinPage(id, true);
						return null;
					}
					rid.copyRid(currentRecord);
					Tuple t = hPage.getRecord(currentRecord);
					return t;
				}
			} else {
				rid.copyRid(currentRecord);
				Tuple t = hPage.getRecord(currentRecord);
				return t;
			}
		}

	}

	public boolean position(RID rid) throws InvalidTupleSizeException,
			IOException {

		return false;
	}

	public void closescan() {
		isFirstScan = false;
		// SystemDefs.JavabaseBM.unpinPage(id, false);
	}
}
