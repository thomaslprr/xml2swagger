package exceptions;

public class SummaryException extends Exception {

	public SummaryException() {
		super("The summary should be less than 120 characters !");
	}
}
