package heap;

import chainexception.ChainException;

public class InvalidTypeException extends ChainException
{
  public InvalidTypeException()
  {
  }

  public InvalidTypeException(Exception paramException, String paramString)
  {
    super(paramException, paramString);
  }
}