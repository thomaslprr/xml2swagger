package converter;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import definitions.ObjectBeans;
import definitions.PropertyBeans;
import exceptions.DataTypeException;
import exceptions.LicenceException;
import exceptions.MethodException;
import exceptions.NameObjectException;
import exceptions.PathException;
import exceptions.RestException;
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
	
	
	public static JSONObject xmlToSwaggerJson(Global global) throws Exception {
		
		if(global.getRest()==null) {
			throw new RestException();
		}
		
		if(global.getRest().getInfo()==null) {
			throw new Exception("You must have a <info> tag in the Rest object");
		}
		
		if(global.getRest().getPaths()==null) {
			throw new Exception("You must have a <paths> tag in the Rest object");
		}
		
		JSONObject swaggerJson = new JSONObject();
		
		if(global.getObjects()!=null && global.getObjects().getObject()!=null) {
		JSONObject definitions = new JSONObject();
		for(ObjectBeans object : global.getObjects().getObject()) {
			
			if(object.getName()==null || object.getName().trim().equals("")) {
				throw new NameObjectException(object.getName());
			}
						
			JSONObject properties = new JSONObject();
			JSONObject propertiesJson = new JSONObject();
			
			propertiesJson.put("type", "object");

			if(object.getProperties()==null) {
				throw new Exception("You must have <properties> tag in your object definition");
			}
			
			if(object.getProperties().getProperties()!=null) {
			for(PropertyBeans property : object.getProperties().getProperties()) {
				if(property.getName()==null) {
					throw new Exception("Property name can't be null. Each property of an object must have a name. \n "
							+ "You must have a <name> tag for each property of an object. \n");
				}
				
				JSONObject propertyJson = new JSONObject();
				
				
				String dataType = dataTypeToTypeAndFormat(property.getType());
				String type = dataType.split("/")[0];
				String format;
				try {
				 format = dataType.split("/")[1];
				}catch(ArrayIndexOutOfBoundsException e) {
					format = "";
				}

				
				propertyJson.put("type", type);
				
				if(!format.equals("")) {
					propertyJson.put("format", format);
				}
				
				if(property.getType().equals("array")) {
					JSONObject arrayJson = new JSONObject();
					if(property.getItems()==null) {
						throw new Exception("If property type is equals to \"array\", you should have a <items> tag.");
					}
					String dataTypeItems = dataTypeToTypeAndFormat(property.getItems().getType());
					String typeItems = dataTypeItems.split("/")[0];
					String formatItems;
					try {
						 formatItems = dataTypeItems.split("/")[1];
						}catch(ArrayIndexOutOfBoundsException e) {
							formatItems = "";
						}
					arrayJson.put("type", typeItems);
					if(!formatItems.equals("")) {
						arrayJson.put("format", property.getItems().getType());
					}
					
					propertyJson.put("items", arrayJson);
				}
				properties.put(property.getName(), propertyJson);
				propertiesJson.put("properties", properties);
				
			}
			}
			
			definitions.put(object.getName(), propertiesJson);
			
		}
		swaggerJson.put("definitions", definitions);
		}
		
		
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
		if(global.getRest().getBasePath()!=null) {
			swaggerJson.put("basePath", global.getRest().getBasePath());
		}
		if(global.getRest().getHost()!=null) {
			swaggerJson.put("host", global.getRest().getHost());
		}
		
		/**
		 * Header tags
		 */
		if(global.getRest().getTags()!=null && global.getRest().getTags().getTag()!=null) {
			
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
			
		}
		
		
		
		if(global.getRest().getSchemes()!=null && global.getRest().getSchemes().getScheme()!=null) {
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
				
				Validator.methodValidator(m.getType());
				
				JSONObject method = new JSONObject();
				
				JSONArray tagsList2 = new JSONArray();
				for(String s : m.getTags().getTag()) {
					tagsList2.put(s);
				}
				method.put("tags", tagsList2);
				method.put("operationId", m.getOperationId());
				
				if(m.getProduces()!=null) {
					JSONArray producesJson = new JSONArray();
					if(m.getProduces().getProduceType()!=null) {
						for(String s : m.getProduces().getProduceType()) {
							producesJson.put(s);
						}
					}
					method.put("produces", producesJson);
				}
				
				if(m.getConsumes()!=null) {
					JSONArray consumesJson = new JSONArray();
					if(m.getConsumes().getConsumeType()!=null) {
						for(String s : m.getConsumes().getConsumeType()) {
							consumesJson.put(s);
						}
					}
					
					method.put("consumes", consumesJson);
				}
				

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
	
	private static String dataTypeToTypeAndFormat(String dataType) throws DataTypeException {
		
		if(dataType==null || dataType.equals("")) {
			throw new DataTypeException(dataType);
		}
		

		switch(dataType) {
		case "integer":
			return "integer/int32";
		case "long":
			return "integer/int64";
		case "float":
			return "number/float";
		case "double":
			return "number/double";
		case "string":
			return "string/";
		case "byte":
			return "string/byte";
		case "binary":
			return "string/binary";
		case "boolean":
			return "boolean";
		case "date":
			return "string/date";
		case "dateTime":
			return "string/date-time";
		case "password":
			return "string/password";
		case "array":
			return "array/";
		default:
			throw new DataTypeException(dataType);
			
		}
	}

}
