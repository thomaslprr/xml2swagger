package converter;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.json.JSONArray;
import org.json.JSONObject;

import definitions.ObjectBeans;
import definitions.PropertyBeans;
import global.Global;
import global.Tag;
import global.TagsDetailled;
import paths.Method;
import paths.Parameter;
import paths.Path;
import paths.Response;

public class XmlParser {
	
	
	public static JSONObject xmlToSwaggerJson(Global global) {
		
		JSONObject swaggerJson = new JSONObject();
		
		
		JSONObject definitions = new JSONObject();
		for(ObjectBeans object : global.getObjects().getObject()) {
						
			JSONObject properties = new JSONObject();
			JSONObject propertiesJson = new JSONObject();

			
			for(PropertyBeans property : object.getProperties().getProperties()) {
				JSONObject propertyJson = new JSONObject();
				
				propertyJson.put("type", property.getType());
				
				if(property.getFormat()!= null && !property.getFormat().equals("")) {
					propertyJson.put("format", property.getFormat());
				}
				
				if(property.getType().equals("array")) {
					JSONObject arrayJson = new JSONObject();
					arrayJson.put("type", property.getItems().getType());
					if(property.getItems().getFormat()!=null) {
						arrayJson.put("format", property.getItems().getFormat());
					}
					
					propertyJson.put("items", arrayJson);
				}
				properties.put(property.getName(), propertyJson);
				propertiesJson.put("properties", properties);
				propertiesJson.put("type", object.getType());

			}
			definitions.put(object.getName(), propertiesJson);

		}
		swaggerJson.put("definitions", definitions);
		swaggerJson.put("swagger", global.getRest().getSwaggerVersion());
		
		JSONObject infoJson = new JSONObject();
		infoJson.put("description", global.getRest().getInfo().getDescription());
		infoJson.put("version", global.getRest().getInfo().getVersion());
		infoJson.put("title", global.getRest().getInfo().getTitle());
		infoJson.put("termsOfService", global.getRest().getInfo().getTermsOfSerivce());
		
		JSONObject contactJson = new JSONObject();
		contactJson.put("name", global.getRest().getInfo().getContactName());
		if(global.getRest().getInfo().getContactEmail()!=null) {
			contactJson.put("email", global.getRest().getInfo().getContactEmail());
		}
		JSONObject licenseJson = new JSONObject();
		licenseJson.put("name",global.getRest().getInfo().getLicenceName());
		licenseJson.put("url", global.getRest().getInfo().getLicenceUrl());
		
		
		infoJson.put("contact",contactJson);
		infoJson.put("license", licenseJson);
		
		swaggerJson.put("info", infoJson);
		swaggerJson.put("basePath", global.getRest().getBasePath());
		if(global.getRest().getHost()!=null) {
			swaggerJson.put("host", global.getRest().getHost());
		}
		
		JSONArray tagsList = new JSONArray();
		for(Tag tag : global.getRest().getTags().getTag()) {
			JSONObject t = new JSONObject();
			t.put("name", tag.getName());
			if(tag.getDescription()!=null) {
				t.put("description", tag.getDescription());
			}
			
			if(tag.getExternalDocs()!=null) {
				JSONObject externalDocs = new JSONObject();
				if(tag.getExternalDocs().getDescription()!=null) {
					externalDocs.put("description", tag.getExternalDocs().getDescription());
				}
				
				if(tag.getExternalDocs().getUrl()!=null) {
					externalDocs.put("url", tag.getExternalDocs().getUrl());
				}
				t.put("externalDocs", externalDocs);
			}
			tagsList.put(t);
		}
		swaggerJson.put("tags", tagsList);
		
		if(global.getRest().getSchemes()!=null) {
			JSONArray schemesArray = new JSONArray();
			for(String schema : global.getRest().getSchemes().getScheme()) {
				schemesArray.put(schema);
			}
			swaggerJson.put("schemes", schemesArray);
		}
		
		JSONObject pathsJson = new JSONObject();
		
		for(Path p : global.getRest().getPaths().getPath()) {
			
			JSONObject pathJson = new JSONObject();
			
			for(Method m : p.getMethods().getMethod()) {
				JSONObject method = new JSONObject();
				
				JSONArray tagsList2 = new JSONArray();
				for(String s : m.getTags().getTag()) {
					tagsList2.put(s);
				}
				method.put("tags", tagsList2);
				method.put("operationId", m.getOperationId());
				
				JSONArray producesJson = new JSONArray();
				for(String s : m.getProduces().getProduceType()) {
					producesJson.put(s);
				}
				method.put("produces", producesJson);

				JSONArray parametersJson = new JSONArray();
				if(m.getParameters().getParameter() !=null) {
					
				for(Parameter param : m.getParameters().getParameter()) {
					JSONObject paramJson = new JSONObject();
					paramJson.put("in", param.getIn());
					paramJson.put("name", param.getName());
					paramJson.put("required", param.isRequired());
					if(param.getType()!=null) {
						
						paramJson.put("type", param.getType());
					}
					
					if(param.getFormat()!=null) {
						paramJson.put("format", param.getFormat());
					}
					if(param.getSchema()!=null) {
						JSONObject schemaJson= new JSONObject();
						schemaJson.put("$ref","#/definitions/"+param.getSchema().getRef() );
						paramJson.put("schema", schemaJson);
					}
					parametersJson.put(paramJson);
				}
				}
				method.put("parameters", parametersJson);
				
				if(m.isDeprecated()) {
					method.put("deprecated", true);
				}
				if(m.getSummary()!=null) {
					method.put("summary", m.getSummary());
				}
				
				JSONObject response = new JSONObject();
				for(Response r : m.getResponses().getResponse()) {
					JSONObject description = new JSONObject() ;
					description.put("description", r.getDescription());
					
					if(r.getSchema()!=null) {
						JSONObject schemaJson= new JSONObject();
						schemaJson.put("$ref","#/definitions/"+r.getSchema().getRef() );
						description.put("schema", schemaJson);
					}
					response.put(r.getName(), description);
				}

				method.put("responses", response);
				
				pathJson.put(m.getType(), method);
			}
			pathsJson.put(p.getName(), pathJson);
			
		}
		
		swaggerJson.put("paths", pathsJson);
		
		return swaggerJson;

	}

}
