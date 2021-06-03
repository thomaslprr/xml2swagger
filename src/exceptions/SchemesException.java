package exceptions;

/**
 * Exception thrown if a scheme type that does not exist has been entered.
 */
public class SchemesException extends Exception {

	public SchemesException(String exceptionText) {
		super(exceptionText);
	}
	
}
