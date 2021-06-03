package exceptions;

/**
 * Exception thrown if a licence url is entered but no licence name refers to that url.
 */
public class LicenceException extends Exception{

	public LicenceException() {
		super("You can't have <licence-url> tag without <licence-name> tag. \n"
				+ "Licence name must be absolutely defined if you specify a licence url \n");
	}
	
}
