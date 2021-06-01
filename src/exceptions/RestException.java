package exceptions;

public class RestException extends Exception {

	public RestException() {
		super("You must have a <rest> tag in the xml global structure.");
	}
}
