package converter;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import definitions.ObjectBeans;
import definitions.PropertyBeans;
import exceptions.LicenceException;
import exceptions.PathException;
import exceptions.SchemesException;
import exceptions.SummaryException;
import exceptions.TagException;
import global.Global;
import global.Tag;
import paths.Method;
import paths.Parameter;
import paths.Path;
import paths.Response;
import security.Validator;

public class XmlParser {
	
	
	public static JSONObject xmlToSwaggerJson(Global global) throws SchemesException, LicenceException, SummaryException, TagException, PathException {
		
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
		swaggerJson.put("swagger","2.0");
		
		JSONObject infoJson = new JSONObject();
		infoJson.put("description", global.getRest().getInfo().getDescription());
		infoJson.put("version", global.getRest().getInfo().getVersion());
		infoJson.put("title", global.getRest().getInfo().getTitle());
		infoJson.put("termsOfService", global.getRest().getInfo().getTermsOfService());
		
		JSONObject contactJson = new JSONObject();
		contactJson.put("name", global.getRest().getInfo().getContactName());
		if(global.getRest().getInfo().getContactEmail()!=null) {
			contactJson.put("email", global.getRest().getInfo().getContactEmail());
		}
		if(global.getRest().getInfo().getContactUrl()!=null) {
			contactJson.put("url",global.getRest().getInfo().getContactUrl());
		}
		
		JSONObject licenseJson = new JSONObject();
		if(global.getRest().getInfo().getLicenceName()!=null) {
			licenseJson.put("url", global.getRest().getInfo().getLicenceUrl());
		}
		if(global.getRest().getInfo().getLicenceUrl()!=null) {
			if(global.getRest().getInfo().getLicenceName()==null) {
				throw new LicenceException();
			}else {
				licenseJson.put("name",global.getRest().getInfo().getLicenceName());
			}

		}

		
		
		infoJson.put("contact",contactJson);
		infoJson.put("license", licenseJson);
		
		swaggerJson.put("info", infoJson);
		swaggerJson.put("basePath", global.getRest().getBasePath());
		if(global.getRest().getHost()!=null) {
			swaggerJson.put("host", global.getRest().getHost());
		}
		
		JSONArray tagsList = new JSONArray();
		ArrayList<String> tagsName = new ArrayList<>();
		for(Tag tag : global.getRest().getTags().getTag()) {
			if(tagsName.contains(tag.getName())) {
				throw new TagException(tag.getName());
			}
				
			
			tagsName.add(tag.getName());
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
			Validator.SchemesValidator(global.getRest().getSchemes().getScheme());
			for(String schema : global.getRest().getSchemes().getScheme()) {
				schemesArray.put(schema);
			}
			swaggerJson.put("schemes", schemesArray);
		}
		
		JSONObject pathsJson = new JSONObject();
		
		for(Path p : global.getRest().getPaths().getPath()) {
			
			if(p.getName().equals("") || p.getName().charAt(0)!='/') {
				throw new PathException(p.getName());
			}
			
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
					paramJson.put("description", param.getDescription());
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
					if(m.getSummary().length()>=120) {
						throw new SummaryException();
					}
					method.put("summary", m.getSummary());
				}
				if(m.getDescription()!=null) {
					method.put("description", m.getDescription());
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
