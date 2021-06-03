package test;

import java.io.File;

import converter.XmlParser;

public class Main {
	
	//Main method for console commands
	public static void main(String[] args) throws Exception {
		//Correct program call
		if(args.length>=2) {
			//File parameter
			if(args[0].equals("-f")) {
				
				File file = new File(args[1]);
				XmlParser.xmlFileToSwaggerJson(file);
				
			}
			//String input parameter
			else if(args[0].equals("-s")) {
				XmlParser.xmlStringToSwaggerJson(args[1]);
			}
			//Bad input
			else {
				throw new Exception("First argument must be :\n  "
						+ "'-f' for a file url  \n"
						+ "'-s' for a xml string input");
			}
			
		}
		//Bad program call
		else {
		
			throw new Exception("You must have 2 arguments. \n"
					+ "First argument must be : \n"
					+ "'-f' for a file url  \n"
					+ "'-s' for a xml string input \n \n"
					+ "Second argument must be : \n"
					+ "File url if '-f' is used \n"
					+ "XML string input if '-s' is used");
			
		}
		
	}

}
