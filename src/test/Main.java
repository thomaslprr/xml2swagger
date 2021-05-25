package test;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import definitions.ObjectsBeans;

public class Main {

	public static void main(String[] args) {
		try {
			File file = new File("definitions.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(ObjectsBeans.class);
			
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			
			ObjectsBeans objects = (ObjectsBeans) unmarshaller.unmarshal(file);
			
			//System.out.println(objects.getObjects().size());
			//System.out.println(objects.getObjects().get(0).getProperties().getProperties().get(2).getItems().getType());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
