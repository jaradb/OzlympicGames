package exceptions;

// An exception for when the wrong type of athlete it scheduled to play a game they cannot compete in

@SuppressWarnings("serial")
public class WrongTypeException extends Exception {

	String errorMessage;

	public WrongTypeException(String message) {
		errorMessage = message;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
