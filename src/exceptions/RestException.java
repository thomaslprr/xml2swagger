package exceptions;

/**
 * Exception thrown if the XML file does not have a Rest tag which is a fundamental and mandatory tag.
 */
public class RestException extends Exception {

	public RestException() {
		super("You must have a <rest> tag in the xml global structure.");
	}
}
