package exceptions;

/**
 * Exception thrown if a non-existent method type has been entered
 */
public class MethodException extends Exception{

	public MethodException(String methodType) {
		super(methodType+" is not a correct method type. \n\n"
				+ "Only get, put, post, delete, options, head and patch are possible values.");
	}
	
}
