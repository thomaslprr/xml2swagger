package security;

import java.util.ArrayList;
import java.util.List;

import exceptions.SchemesException;

public class Validator {

	public static void SchemesValidator(List<String> schemesList) throws SchemesException {
		
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
	
}
