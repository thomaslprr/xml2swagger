package test;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import converter.XmlParser;
import global.Global;

public class Main {

	public static void main(String[] args) {
		try {
			File file = new File("definitions.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Global.class);
			
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			
			Global global = (Global) unmarshaller.unmarshal(file);
			
			
			//Print all the XML 
			//XmlParser.printXml(global);
			
			System.out.println(XmlParser.xmlToSwaggerJson(global).toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
