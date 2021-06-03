package exceptions;

/**
 * Exception thrown if a summary contains 120 characters or more
 */
public class SummaryException extends Exception {

	public SummaryException() {
		super("The summary should be less than 120 characters !");
	}
}
