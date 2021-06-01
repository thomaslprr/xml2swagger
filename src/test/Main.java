package test;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import converter.XmlParser;
import global.Global;

public class Main {

	public static void main(String[] args) throws Exception {
		
		if(args.length>=2) {
			
			if(args[0].equals("-f")) {
				
				File file = new File(args[1]);
				XmlParser.xmlFileToSwaggerJson(file);
				
			}else if(args[0].equals("-s")) {
				XmlParser.xmlStringToSwaggerJson(args[1]);
			}else {
				throw new Exception("First argument must be :\n  "
						+ "'-f' for a file url  \n"
						+ "'-s' for a xml string input");
			}
			
		}else {
		
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
