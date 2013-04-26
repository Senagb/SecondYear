package bufmgr;

import chainexception.ChainException;

public class IOExceptionBM extends ChainException {

	public IOExceptionBM(Exception e, String name) {
		super(e, name);

	}
}
