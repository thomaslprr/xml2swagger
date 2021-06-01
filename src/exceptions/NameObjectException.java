package exceptions;

public class NameObjectException extends Exception {

	public NameObjectException(String nameValue) {
		super(exceptionToSend(nameValue));
	}
	
	private static String exceptionToSend(String nameValue) {
		
		if(nameValue ==null) {
			return "You must have a <name> tag for each object definition";
		}else if(nameValue.trim().equals("")) {
			return "<name> tag of an object can't be empty.";
		} 
		return "";
	}
}
