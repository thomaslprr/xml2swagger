package exceptions;

public class PathException extends Exception {

	public PathException(String path) {
		super(exceptionToSend(path));
	}
	
	private static String exceptionToSend(String path) {
		if(path.equals("")) {
			return "Path name can't be empty !";
		}else if(path.charAt(0)!='/') {
			return "Path name : "+path+" must start with \"/\" and can't start with "+path.charAt(0)+"\n"
					+ "The correct path name is : "+"/"+path;
		}
		return "";
	}
}
