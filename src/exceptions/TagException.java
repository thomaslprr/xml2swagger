package exceptions;

/**
 * Exception thrown if the same tag is present twice
 */
public class TagException extends Exception {

	public TagException(String tagName) {
		super("A same tag can't be add twice. \n\n"
				+ tagName+" is present more than once in the list of tags.");
	}
}
