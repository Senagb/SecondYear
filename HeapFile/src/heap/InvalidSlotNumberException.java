package heap;

import chainexception.ChainException;

public class InvalidSlotNumberException extends ChainException {
	public InvalidSlotNumberException() {
	}

	public InvalidSlotNumberException(Exception paramException,
			String paramString) {
		super(paramException, paramString);
	}
}