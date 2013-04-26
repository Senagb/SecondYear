package heap;

import chainexception.ChainException;

public class InvalidTupleSizeException extends ChainException
{
  public InvalidTupleSizeException()
  {
  }

  public InvalidTupleSizeException(Exception paramException, String paramString)
  {
    super(paramException, paramString);
  }
}