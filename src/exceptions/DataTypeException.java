package exceptions;

public class DataTypeException extends Exception{

	public DataTypeException(String dataType) {
		super(exceptionToSend(dataType));
	}
	
	private static String exceptionToSend(String dataType) {
		if(dataType==null) {
			return "Every <property> and <items> tag must have a <type> tag.";
		}else if(dataType.equals("")){
			return "<type> tag can't be empty.";
		}
		else {
			return dataType+" does not exist. \n \n"
					+ "Possible values are : \n - integer\n"
					+ "- long\n"
					+ "- float\n"
					+ "- double\n"
					+ "- string\n"
					+ "- byte\n"
					+ "- binary\n"
					+ "- boolean\n"
					+ "- date\n"
					+ "- dateTime\n"
					+ "- password";
		}
	}
}
