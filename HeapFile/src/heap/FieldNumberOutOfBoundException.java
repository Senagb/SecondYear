package heap;

import chainexception.ChainException;

public class FieldNumberOutOfBoundException extends ChainException
{
  public FieldNumberOutOfBoundException()
  {
  }

  public FieldNumberOutOfBoundException(Exception paramException, String paramString)
  {
    super(paramException, paramString);
  }
}