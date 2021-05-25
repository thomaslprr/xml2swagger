package test;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import global.Global;

public class Main {

	public static void main(String[] args) {
		try {
			File file = new File("definitions.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Global.class);
			
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			
			Global global = (Global) unmarshaller.unmarshal(file);
			
			//Test for definitions part
			System.out.println(global.getObjects().getObject().get(0).getName());
			System.out.println(global.getObjects().getObject().get(0).getProperties().getProperties().get(2).getItems().getType());

			//Test for header part 
			
			System.out.println(global.getRest().getInfo().getTitle()+" "+global.getRest().getInfo().getDescription());
			
			//Test for path part
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
