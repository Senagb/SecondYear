package bufmgr;

import global.*;

class Descriptor {
	private PageId pageNumber = null;
	private int pinCount;
	private boolean dirty;

	public Descriptor(int pId) {
		pageNumber = new PageId();
		// System.out.println();
		pageNumber.pid = pId;
		pinCount = 0;
		dirty = false;
	}

	public PageId getPage() {
		return pageNumber;
	}

	public int getPinCount() {
		return pinCount;
	}

	public boolean isDirty() {
		return dirty;
	}

	public void setPage(int page) {
		pageNumber.pid = page;
	}

	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}

	public void setPinCount(int pinCount) {
		this.pinCount = pinCount;
	}

	public void increasePinCount() {
		pinCount++;
	}

	public void decreasePinCount() {
		pinCount--;
	}
}