package exceptions;

public class WrongTypeException extends Exception {

	String errorMessage;
	
	public WrongTypeException(String message)
	{
		errorMessage = message;
	}
	
	public String getErrorMessage()
	{
		return errorMessage;
	}
}
