package bufmgr;

import chainexception.ChainException;

public class PageIdException extends ChainException {

	public PageIdException(Exception e, String name) {
		super(e, name);
	}
}
