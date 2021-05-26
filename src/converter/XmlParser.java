package converter;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.json.JSONObject;

import definitions.ObjectBeans;
import definitions.PropertyBeans;
import global.Global;
import paths.Method;
import paths.Parameter;
import paths.Path;

public class XmlParser {
	

	
	public static void printXml(Global global) {
		System.out.println("---------- xml2java ----------\n");
		
		System.out.println("          -Objects- \n");
		for(ObjectBeans object : global.getObjects().getObject()) {
			System.out.println(" -Object- \n");
			System.out.println("Name: "+object.getName());
			System.out.println("Type: "+object.getType());
			for(PropertyBeans property : object.getProperties().getProperties()) {
				System.out.println("\n  -Property-");
				System.out.println("  Name: "+property.getName());
				System.out.println("  Type: "+property.getType());
				System.out.println("  Format: "+property.getFormat());
				if(property.getType().equals("array")) {
					System.out.println("  Items:"+property.getItems().getType());
				}
				System.out.println("\n");
			}	
		}
		
		System.out.println("\n          -Rest-\n");
		System.out.println("Swagger version: "+global.getRest().getSwaggerVersion());
		System.out.println("Description: "+global.getRest().getInfo().getDescription());
		System.out.println("Version: "+global.getRest().getInfo().getVersion());
		System.out.println("Title: "+global.getRest().getInfo().getTitle());
		System.out.println("Contact: "+global.getRest().getInfo().getContactName());
		System.out.println("Lience Name: "+global.getRest().getInfo().getLicenceName());
		System.out.println("Licence URL: "+global.getRest().getInfo().getLicenceUrl());
		System.out.println("Base path: "+global.getRest().getBasePath());
		System.out.println("\nTags:");
		String tagStr0 = "";
		for(String tag : global.getRest().getTags().getTag()) {
			tagStr0 += tag+" | ";
		}
		System.out.println(tagStr0+"\n");
		System.out.println("-Paths-");
		for(Path p : global.getRest().getPaths().getPath()) {
			System.out.println("   -Path-");
			System.out.println("    Name: "+p.getName());
			System.out.println("      -Methods");
			for(Method m : p.getMethods().getMethod()) {
				System.out.println("\n      Type: "+m.getType());
				String tagStr = "" ;
				for(String s : m.getTags().getTag()) {
					tagStr+=s+" | ";
				}
				System.out.println("      Tags: "+tagStr);				
				System.out.println("      Operation Id: "+m.getOperationId());
				String strProducesTypes="";
				for(String s : m.getProduces().getProduceType()) {
					strProducesTypes+=s+" | ";
				}
				System.out.println("      Produces types : "+strProducesTypes);
				
				if(m.getParameters().getParameter() !=null) {
					System.out.println("      Parameters:");
				for(Parameter param : m.getParameters().getParameter()) {
					System.out.println("          Parameter:");
					System.out.println("             In: "+param.getIn());
					System.out.println("             Name: "+param.getName());
					System.out.println("             Required: "+param.isRequired());
					if(param.getType()!=null) {
						System.out.println("             Type: "+param.getType());
					}
					
					if(param.getFormat()!=null) {
						System.out.println("             Format: "+param.getFormat());
					}
					if(param.getSchema()!=null) {
						System.out.println("             Schema ref: "+param.getSchema().getRef());
					}
					
				}
				}
				
				System.out.println("      Responses");
				System.out.println("          Response");
				System.out.println("             Default: "+m.getResponses().getDefaultR().getDescription());
				
				System.out.println("\n \n");
				
			}
			
			System.out.println("\n \n");
			
		}
		System.out.println("--------------END--------------");

	}
	
	public static JSONObject xmlToSwaggerJson(Global global) {
		
		JSONObject swaggerJson = new JSONObject();
		/**
		System.out.println("---------- xml2java ----------\n");
		
		System.out.println("          -Objects- \n");
		for(ObjectBeans object : global.getObjects().getObject()) {
			System.out.println(" -Object- \n");
			System.out.println("Name: "+object.getName());
			System.out.println("Type: "+object.getType());
			for(PropertyBeans property : object.getProperties().getProperties()) {
				System.out.println("\n  -Property-");
				System.out.println("  Name: "+property.getName());
				System.out.println("  Type: "+property.getType());
				System.out.println("  Format: "+property.getFormat());
				if(property.getType().equals("array")) {
					System.out.println("  Items:"+property.getItems().getType());
				}
				System.out.println("\n");
			}	
		}
		
		System.out.println("\n          -Rest-\n");*/
		swaggerJson.put("swagger", global.getRest().getSwaggerVersion());
		
		JSONObject infoJson = new JSONObject();
		infoJson.put("description", global.getRest().getInfo().getDescription());
		infoJson.put("version", global.getRest().getInfo().getVersion());
		System.out.println("Description: "+global.getRest().getInfo().getDescription());
		System.out.println("Title: "+global.getRest().getInfo().getTitle());
		System.out.println("Contact: "+global.getRest().getInfo().getContactName());
		System.out.println("Lience Name: "+global.getRest().getInfo().getLicenceName());
		System.out.println("Licence URL: "+global.getRest().getInfo().getLicenceUrl());
		System.out.println("Base path: "+global.getRest().getBasePath());
		System.out.println("\nTags:");
		String tagStr0 = "";
		for(String tag : global.getRest().getTags().getTag()) {
			tagStr0 += tag+" | ";
		}
		System.out.println(tagStr0+"\n");
		System.out.println("-Paths-");
		for(Path p : global.getRest().getPaths().getPath()) {
			System.out.println("   -Path-");
			System.out.println("    Name: "+p.getName());
			System.out.println("      -Methods");
			for(Method m : p.getMethods().getMethod()) {
				System.out.println("\n      Type: "+m.getType());
				String tagStr = "" ;
				for(String s : m.getTags().getTag()) {
					tagStr+=s+" | ";
				}
				System.out.println("      Tags: "+tagStr);				
				System.out.println("      Operation Id: "+m.getOperationId());
				String strProducesTypes="";
				for(String s : m.getProduces().getProduceType()) {
					strProducesTypes+=s+" | ";
				}
				System.out.println("      Produces types : "+strProducesTypes);
				
				if(m.getParameters().getParameter() !=null) {
					System.out.println("      Parameters:");
				for(Parameter param : m.getParameters().getParameter()) {
					System.out.println("          Parameter:");
					System.out.println("             In: "+param.getIn());
					System.out.println("             Name: "+param.getName());
					System.out.println("             Required: "+param.isRequired());
					if(param.getType()!=null) {
						System.out.println("             Type: "+param.getType());
					}
					
					if(param.getFormat()!=null) {
						System.out.println("             Format: "+param.getFormat());
					}
					if(param.getSchema()!=null) {
						System.out.println("             Schema ref: "+param.getSchema().getRef());
					}
					
				}
				}
				
				System.out.println("      Responses");
				System.out.println("          Response");
				System.out.println("             Default: "+m.getResponses().getDefaultR().getDescription());
				
				System.out.println("\n \n");
				
			}
			
			System.out.println("\n \n");
			
		}
		System.out.println("--------------END--------------");
		
		return swaggerJson;

	}

}
