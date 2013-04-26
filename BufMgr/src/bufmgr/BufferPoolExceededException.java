package bufmgr;

import chainexception.ChainException;

public class BufferPoolExceededException extends ChainException {

	public BufferPoolExceededException(Exception e, String name) {
		// TODO Auto-generated constructor stub
		super(e, name);
	}

}
