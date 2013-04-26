package bufmgr;

import chainexception.ChainException;

public class PageException extends ChainException {

	public PageException(Exception e, String name) {
		super(e,name);
	}
	
}
