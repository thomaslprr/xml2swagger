package validator;

import java.util.ArrayList;
import java.util.List;

import exceptions.MethodException;
import exceptions.SchemesException;

public class Validator {

	/**
	 * Checks the correct entry of a scheme by verifying that the entry belongs to the possible schemes with Swagger
	 * @param schemesList
	 * @throws SchemesException
	 */
	public static void SchemesValidator(List<String> schemesList) throws SchemesException {
		//Definition of all the possible schemes value
		List<String> possibleSchemes = new ArrayList<>();
		possibleSchemes.add("http");
		possibleSchemes.add("https");
		possibleSchemes.add("ws");
		possibleSchemes.add("wss");
		
		if(schemesList.size()>4) {
			throw new SchemesException("The <schemes> tag can only contains <scheme> tag with the following value: \n "
					+ "- http \n"
					+ "- https \n"
					+ "- ws \n"
					+ "- wss \n"
					+ "A same <scheme> tag can be add twice or more in the XML file. \n\n"
					+ "The <schemes> tag contains "+schemesList.size()+" <scheme> tag but the maximum is 4.\n");
		}
		
		for(String s : schemesList) {
			if(!possibleSchemes.contains(s)) {
				throw new SchemesException("<schemes> tag contains <scheme> value that does not exist. \n"
						+ "http, https, ws, wss are the only possible values \n"
						+ "\""+s+"\" is not a correct value.");
			}
		}	
	}
	
	/**
	 * Checks the correct entry of a method type by verifying that the method belongs to the existing methods with Swagger
	 * @param methodType
	 * @throws MethodException
	 */
	public static void methodValidator(String methodType) throws MethodException {
		List<String> possibleMethodsType = new ArrayList<>();
		possibleMethodsType.add("get");
		possibleMethodsType.add("put");
		possibleMethodsType.add("post");
		possibleMethodsType.add("delete");
		possibleMethodsType.add("options");
		possibleMethodsType.add("head");
		possibleMethodsType.add("patch");
		if(!possibleMethodsType.contains(methodType)) {
			throw new MethodException(methodType);
		}
	}
	
	/**
	 * Checks the correct entry of a method response by verifying that the response 
	 * returns either "default" or a valid html status code (between 100 and 599)
	 * @param methodType
	 * @throws Exception
	 */
	public static void isResponseValid(String methodType) throws Exception {
		if(!methodType.equals("default")) {
			try {
				int number = Integer.parseInt(methodType);
				if(! (number>=100&&number<=599)) {
					throw new Exception("Response name can only be \"default\" or a HTTP Status Code (number between 100 and 599)\n"
							+ "number"+ " is not between 100 and 599");	
				}
			}catch(NumberFormatException e) {
				throw new Exception("Response name can only be \"default\" or a HTTP Status Code (number between 100 and 599)\n"
						+ "\""+methodType+"\" is not a correct value");
			}	
		}		
	}
	
}
