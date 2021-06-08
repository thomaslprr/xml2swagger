package converter;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.json.JSONArray;
import org.json.JSONObject;

import definitions.Object;
import definitions.Property;
import exceptions.DataTypeException;
import exceptions.LicenceException;
import exceptions.NameObjectException;
import exceptions.PathException;
import exceptions.RestException;
import exceptions.SummaryException;
import exceptions.TagException;
import global.Global;
import global.Tag;
import paths.Method;
import paths.Parameter;
import paths.Path;
import paths.Response;
import security.Scope;
import security.Security;
import security.SecurityDefinition;
import validator.Validator;

public class XmlParser {
	
	/**
	 * Convert a Global Java object to JSON Object
	 * @param global Global object
	 * @return Swagger 2.0 Documentation in JSON format
	 * @throws Exception
	 */
	private static JSONObject xmlToSwaggerJson(Global global) throws Exception {
		
		/**
		 * Throw exception if Rest tag does not exist
		 */
		if(global.getRest()==null) {
			throw new RestException();
		}
		
		/**
		 * throw exception if Info tag does not exist
		 */
		if(global.getRest().getInfo()==null) {
			throw new Exception("You must have a <info> tag in the Rest object");
		}
		
		/**
		 * throw exception if Paths tag does not exist
		 */
		if(global.getRest().getPaths()==null) {
			throw new Exception("You must have a <paths> tag in the Rest object");
		}
		
		//Objects definition 
		
		JSONObject swaggerJson = new JSONObject();
		
		if(global.getObjects()!=null && global.getObjects().getObject()!=null) {
		JSONObject definitions = new JSONObject();
		for(Object object : global.getObjects().getObject()) {
			
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
				JSONArray propertyRequired = new JSONArray();
			for(Property property : object.getProperties().getProperties()) {
				if(property.getName()==null) {
					throw new Exception("Property name can't be null. Each property of an object must have a name. \n "
							+ "You must have a <name> tag for each property of an object. \n");
				}
				
				
				JSONObject propertyJson = new JSONObject();
				if(property.getRef()!=null && !property.getRef().equals("")) {
					propertyJson.put("$ref","#/definitions/"+property.getRef());
				}else {
				
				String dataType = dataTypeToTypeAndFormat(property.getType().toLowerCase());
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
				
				//enum management
				if(property.getEnums()!=null&&property.getEnums().getEnum()!=null) {
					JSONArray enumTab = new JSONArray();
					for(String s : property.getEnums().getEnum()) {
						enumTab.put(s);
					}
					propertyJson.put("enum", enumTab);
				}
				
				//example management
				if(property.getExample()!=null) {
					propertyJson.put("example", property.getExample());
				}
				
				//description management
				if(property.getDescription()!=null) {
					propertyJson.put("description", property.getDescription());
				}
				
				//required management
				if(property.isRequired()) {
					propertyRequired.put(property.getName());
				}
				
				
				if(property.getMinimum()!=0) {
					propertyJson.put("minimum", property.getMinimum());
				}
				
				if(property.getMaximum()!=0) {
					propertyJson.put("maximum", property.getMaximum());
				}
				
				if(property.isExclusiveMaximum()) {
					propertyJson.put("exclusiveMaximum", true);
				}
				
				if(property.isExclusiveMinimum()) {
					propertyJson.put("exclusiveMinimum", true);
				}
				
				if(property.isUniqueItems()) {
					propertyJson.put("uniqueItems", true);
				}
				
				if(property.getMaxLength()!=0) {
					propertyJson.put("maxLength", property.getMaxLength());
				}
				
				if(property.getMinLength()!=0) {
					propertyJson.put("minLength", property.getMinLength());
				}
				
				if(property.getMaxItems()!=0) {
					propertyJson.put("maxItems", property.getMaxItems());
				}
				
				
				if(property.getType().equals("array")) {
					JSONObject arrayJson = new JSONObject();
					if(property.getItems()==null) {
						throw new Exception("If property type is equals to \"array\", you should have a <items> tag.");
					}
					
					if(property.getItems().getRef()!=null) {
						arrayJson.put("$ref", "#/definitions/"+property.getItems().getRef());
					}else {
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
					}
					
					
					
					propertyJson.put("items", arrayJson);
				}
				
				}
				properties.put(property.getName(), propertyJson);
				propertiesJson.put("properties", properties);
				
			}
			if(propertyRequired.length()>0)
				propertiesJson.put("required", propertyRequired);
			}
			
			definitions.put(object.getName(), propertiesJson);
			
		}
		swaggerJson.put("definitions", definitions);
		}
		
		
		//Swagger definition 
		
		swaggerJson.put("swagger","2.0");
		
		if(global.getRest().getBasePath()!=null) {
			swaggerJson.put("basePath", global.getRest().getBasePath());
		}
		if(global.getRest().getHost()!=null) {
			swaggerJson.put("host", global.getRest().getHost());
		}
		
		//throw exception if no version tag in the info tag
		if(global.getRest().getInfo().getVersion()==null) {
			throw new Exception("You must have a <version> tag in the Info object");
		}
		//throw exception if no title tag in the info tag
		if(global.getRest().getInfo().getTitle()==null) {
			throw new Exception("You must have a <title> tag in the Info object");
		}
		
		/**
		 * Rest info management
		 */
		
		//general informations
		JSONObject infoJson = new JSONObject();
		
		if(global.getRest().getInfo().getDescription()!=null) {
			infoJson.put("description", global.getRest().getInfo().getDescription());
		}
		infoJson.put("version", global.getRest().getInfo().getVersion());
		infoJson.put("title", global.getRest().getInfo().getTitle());
		if(global.getRest().getInfo().getTermsOfService()!=null) {
			infoJson.put("termsOfService", global.getRest().getInfo().getTermsOfService());
		}
		
		//contact informations
		JSONObject contactJson = new JSONObject();
		
		if(global.getRest().getInfo().getContactName()!=null) {
			contactJson.put("name", global.getRest().getInfo().getContactName());
		}
		
		if(global.getRest().getInfo().getContactEmail()!=null) {
			contactJson.put("email", global.getRest().getInfo().getContactEmail());
		}
		if(global.getRest().getInfo().getContactUrl()!=null) {
			contactJson.put("url",global.getRest().getInfo().getContactUrl());
		}
		
		//license informations
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
		
		/**
		 * Header tags
		 */
		if(global.getRest().getTags()!=null && global.getRest().getTags().getTag()!=null) {
			
			JSONArray tagsList = new JSONArray();
			ArrayList<String> tagsName = new ArrayList<>();
			for(Tag tag : global.getRest().getTags().getTag()) {
				
				if(tag.getName()==null) {
					throw new Exception("You must have a <name> tag in Tag object");
				}
				
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
					
					if(tag.getExternalDocs().getUrl()==null) {
						throw new Exception("You must have a <url> tag in ExternalDocs object");
					}
					externalDocs.put("url", tag.getExternalDocs().getUrl());
					
					t.put("externalDocs", externalDocs);
				}
				tagsList.put(t);
			}
			swaggerJson.put("tags", tagsList);			
		}
		
		
		/**
		 * Schemes
		 */
		if(global.getRest().getSchemes()!=null && global.getRest().getSchemes().getScheme()!=null) {
			JSONArray schemesArray = new JSONArray();
			Validator.SchemesValidator(global.getRest().getSchemes().getScheme());
			for(String schema : global.getRest().getSchemes().getScheme()) {
				schemesArray.put(schema);
			}
			swaggerJson.put("schemes", schemesArray);
		}
		
		
		/**
		 * Paths
		 */
		JSONObject pathsJson = new JSONObject();
		if(global.getRest().getPaths().getPath()!=null) {
		for(Path p : global.getRest().getPaths().getPath()) {
			
			if(p.getName().equals("") || p.getName().charAt(0)!='/') {
				throw new PathException(p.getName());
			}
			
			if(p.getMethods()==null) {
				throw new Exception("You must have a <methods> tag in Path object");
			}
			
			JSONObject pathJson = new JSONObject();
			
			/**
			 * Methods management for each path
			 */
			if(p.getMethods().getMethod()!=null) {
			
			for(Method m : p.getMethods().getMethod()) {
				
				Validator.methodValidator(m.getType());
				
				JSONObject method = new JSONObject();
				
				/**
				 * Tags
				 */
				if(m.getTags()!=null && m.getTags().getTag()!=null) {
					JSONArray tagsList2 = new JSONArray();
					for(String s : m.getTags().getTag()) {
						tagsList2.put(s);
					}
					method.put("tags", tagsList2);
				}
				
				/**
				 * Operation id
				 */
				if(m.getOperationId()!=null) {
					method.put("operationId", m.getOperationId());
				}
				
				/**
				 * Produces management
				 */
				if(m.getProduces()!=null) {
					JSONArray producesJson = new JSONArray();
					if(m.getProduces().getProduceType()!=null) {
						for(String s : m.getProduces().getProduceType()) {
							producesJson.put(s);
						}
					}
					method.put("produces", producesJson);
				}
				
				/**
				 * Consumes management
				 */
				if(m.getConsumes()!=null) {
					JSONArray consumesJson = new JSONArray();
					if(m.getConsumes().getConsumeType()!=null) {
						for(String s : m.getConsumes().getConsumeType()) {
							consumesJson.put(s);
						}
					}
					
					method.put("consumes", consumesJson);
				}
				
				/**
				 * Parameters management
				 */
				JSONArray parametersJson = new JSONArray();
				if(m.getParameters()!=null && m.getParameters().getParameter() !=null) {
					
				for(Parameter param : m.getParameters().getParameter()) {
					
					JSONObject paramJson = new JSONObject();
					
					/**
					 * verification of the presence of all mandatory tags 
					 * In, Name, Schema if In = "body", Type if In != "body"
					 */
					
					if(param.getIn()==null) {
						throw new Exception("You must have a <in> tag in Parameter object");
					}
					if(param.getName()==null) {
						throw new Exception("You must have a <name> tag in Parameter object");
					}
					
					if(param.getIn().equals("body")&&param.getSchema()==null) {
						throw new Exception("You must have a <schema> tag in Parameter object if <in> = \"body\" ");
					}
					if(!param.getIn().equals("body")&&param.getType()==null) {
						throw new Exception("You must have a <type> tag in Parameter object if <in> != \"body\" ");
					}
					
					if(param.getIn().equals("path")) {
						paramJson.put("required", true);
					}else {
						paramJson.put("required", param.isRequired());
					}
					
					paramJson.put("in", param.getIn());
					paramJson.put("name", param.getName());
					
					if(param.getDescription()!=null) {
						paramJson.put("description", param.getDescription());
					}
					
					//parameter type and format gestion
					if(!param.getIn().equals("body")) {
						String type="";
						String format="";
						if(param.getType().equals("file")) {
							type="file";
						}else {
							String dataType = dataTypeToTypeAndFormat(param.getType());
							type = dataType.split("/")[0];
							try {
							 format = dataType.split("/")[1];
							}catch(ArrayIndexOutOfBoundsException e) {
								format = "";
							}
						}
							
						paramJson.put("type", type);
						if(!format.equals(""))
							paramJson.put("format", format);
									
					}
					//schema parameter gestion
					if(param.getSchema()!=null) {
						JSONObject schemaJson= new JSONObject();
						if(param.getSchema().getRef()!=null) {
							schemaJson.put("$ref","#/definitions/"+param.getSchema().getRef() );
						}
						
						if(param.getSchema().getType()!=null && param.getSchema().getType().equals("array")) {
							schemaJson.put("type", param.getSchema().getType());
							JSONObject itemsObject = new JSONObject();
							if(param.getSchema().getItems().getRef()!=null) {
								itemsObject.put("$ref", "#/definitions/"+param.getSchema().getItems().getRef());
							}
							
							if(param.getSchema().getItems().getDefault()!=null) {
								itemsObject.put("default", param.getSchema().getItems().getDefault());
							}
							
							if(param.getSchema().getItems().getType()!=null) {
								itemsObject.put("type",param.getSchema().getItems().getType());
							}
							
							if(param.getSchema().getItems().getEnums()!=null && param.getSchema().getItems().getEnums().getEnum()!=null) {
								JSONArray enumArray = new JSONArray();
								for(String s : param.getSchema().getItems().getEnums().getEnum()) {
									enumArray.put(s);
								}
								itemsObject.put("enum", enumArray);
							}
							schemaJson.put("items", itemsObject);							
						}
						paramJson.put("schema", schemaJson);

					}
					
					if(param.getCollectionFormat()!=null) {
						paramJson.put("collectionFormat", param.getCollectionFormat());
					}
					
					if(param.getMinimum()!=0) {
						paramJson.put("minimum", param.getMinimum());
					}
					
					if(param.getMaximum()!=0) {
						paramJson.put("maximum", param.getMaximum());
					}
					
					if(param.isExclusiveMaximum()) {
						paramJson.put("exclusiveMaximum", true);
					}
					
					if(param.isExclusiveMinimum()) {
						paramJson.put("exclusiveMinimum", true);
					}
					
					if(param.isUniqueItems()) {
						paramJson.put("uniqueItems", true);
					}
					
					if(param.getMaxLength()!=0) {
						paramJson.put("maxLength", param.getMaxLength());
					}
					
					if(param.getMinLength()!=0) {
						paramJson.put("minLength", param.getMinLength());
					}
					
					if(param.getMaxItems()!=0) {
						paramJson.put("maxItems", param.getMaxItems());
					}

					/**
					 * Todo: securise input and null value (required or not)
					 */
					if(param.getType()!=null && param.getType().equals("array")) {
						
						if(param.getItems()==null) {
							throw new Exception("You must have a <items> tag in the parameter object if parameter type is equal to \"array\" ");
						}
						
						JSONObject itemsObject = new JSONObject();
						itemsObject.put("type", param.getItems().getType());
						if(param.getItems().getEnums()!=null && param.getItems().getEnums().getEnum()!=null) {
							JSONArray enumArray = new JSONArray();
							for(String s : param.getItems().getEnums().getEnum()) {
								enumArray.put(s);
							}
							itemsObject.put("enum", enumArray);
						}
						if(param.getItems().getDefault()!=null) {
							itemsObject.put("default", param.getItems().getDefault());
						}
						
						paramJson.put("items", itemsObject);
						
					}
					
					
					parametersJson.put(paramJson);
				}
				}
				method.put("parameters", parametersJson);
				
				
				if(m.isDeprecated()) {
					method.put("deprecated", true);
				}
				
				//method summary management
				if(m.getSummary()!=null) {
					if(m.getSummary().length()>=120) {
						throw new SummaryException();
					}
					method.put("summary", m.getSummary());
				}
				//method description management
				if(m.getDescription()!=null) {
					method.put("description", m.getDescription());
				}else {
					method.put("description", "");
				}
				
				/**
				 * Response management
				 */
				if(m.getResponses()==null) {
					throw new Exception("You must have a <responses> tag in the Method object");
				}
				
				if(m.getResponses().getResponse()!=null) {
				JSONObject response = new JSONObject();
				for(Response r : m.getResponses().getResponse()) {
					//checking the correct format of the response
					Validator.isResponseValid(r.getName());
					
					//throw exception if name tag of the response does not exist
					if(r.getName()==null) {
						throw new Exception("You must have a <name> tag in Response Object");
					}
					//throw exception if description tag of the response does not exist
					if(r.getDescription()==null) {
						throw new Exception("You must have a <description> tag in Response Object");
					}
					
					JSONObject description = new JSONObject() ;
					description.put("description", r.getDescription());
					
					//schema response management
					if(r.getSchema()!=null) {
						JSONObject schemaJson = new JSONObject();

						if(r.getSchema().getType()!=null && r.getSchema().getType().equals("array")){
							if(r.getSchema().getItems().getRef()==null) {
								throw new Exception("You must have a <ref> tag in Items object");
							}
							schemaJson.put("type", "array");
							JSONObject refObject = new JSONObject();
							refObject.put("$ref", "#/definitions/"+r.getSchema().getItems().getRef());
							schemaJson.put("items", refObject);
							description.put("schema", schemaJson);
							
							
						}else if(r.getSchema().getType()==null) {
							if(r.getSchema().getRef()==null) {
								throw new Exception("You must have a <ref> tag in Schema object");
							}
							schemaJson.put("$ref","#/definitions/"+r.getSchema().getRef() );
							description.put("schema", schemaJson);
						}else if(r.getSchema().getType()!=null) {
							
							String typeSchema="";
							String formatSchema="";
							
							if(r.getSchema().getType().equals("object")) {
								typeSchema="object";
							}else {
								String dataTypeSchema = dataTypeToTypeAndFormat(r.getSchema().getType());
								typeSchema = dataTypeSchema.split("/")[0];
								try {
									formatSchema = dataTypeSchema.split("/")[1];
								}catch(ArrayIndexOutOfBoundsException e) {
									formatSchema = "";
								}
							}	
							
							schemaJson.put("type", typeSchema);
							if(!formatSchema.equals("")) {
								schemaJson.put("format", formatSchema);
							}
							
							if(r.getSchema().getType().equals("object")) {
								
								if(r.getSchema().getAdditionalProperties()!=null && r.getSchema().getAdditionalProperties().getType()!=null) {
									JSONObject additionalProperties = new JSONObject();
									String type="";
									String format="";

									String dataType = dataTypeToTypeAndFormat(r.getSchema().getAdditionalProperties().getType());
									type = dataType.split("/")[0];
									try {
										format = dataType.split("/")[1];
									}catch(ArrayIndexOutOfBoundsException e) {
										format = "";
									}
													
									additionalProperties.put("type", type);
									additionalProperties.put("format", format);
									schemaJson.put("additionalProperties",additionalProperties);
									
								}
								
								
							}
							
							description.put("schema", schemaJson);
						}					
					}
					
					response.put(r.getName(), description);
				}

				method.put("responses", response);
				}
				
				
				//security management
				if(m.getSecurities()!=null&&m.getSecurities().getSecurity()!=null) {
					JSONArray securities = new JSONArray();
					for(Security security : m.getSecurities().getSecurity()) {
						JSONObject securityJson = new JSONObject();
						if(security.getName()==null) {
							throw new Exception();
						}
						
						
						JSONArray valuesArray = new JSONArray();
						if(security.getValues()!=null && security.getValues().getValue()!=null) {
							for(String s : security.getValues().getValue()) {
								valuesArray.put(s);
							}
						}
						securityJson.put(security.getName(), valuesArray);
						
						securities.put(securityJson);
					}
					method.put("security", securities);
					
				}
				
				
				pathJson.put(m.getType(), method);
			}
			}
			pathsJson.put(p.getName(), pathJson);
			
		}
		
		swaggerJson.put("paths", pathsJson);
		
		}
		
		
		//security definitions 
		if(global.getRest().getSecurityDefinitions()!=null && global.getRest().getSecurityDefinitions().getSecurityDefinition()!=null) {
			JSONObject securityDefinitionsJson = new JSONObject();
			
			for(SecurityDefinition s : global.getRest().getSecurityDefinitions().getSecurityDefinition()) {
				
				JSONObject securityDefinitionJson = new JSONObject();
				
				if(s.getAuthorizationUrl()!=null) {
					securityDefinitionJson.put("authorizationUrl", s.getAuthorizationUrl());
				}
				
				if(s.getDescription()!=null) {
					securityDefinitionJson.put("description", s.getDescription());
				}
				
				if(s.getFlow()!=null) {
					securityDefinitionJson.put("flow", s.getFlow());
				}
				
				if(s.getHeaderName()!=null) {
					securityDefinitionJson.put("name", s.getHeaderName());
				}
				
				if(s.getIn()!=null) {
					securityDefinitionJson.put("in", s.getIn());
				}
				
				if(s.getTokenUrl()!=null) {
					securityDefinitionJson.put("tokenUrl", s.getTokenUrl());
				}
				
				if(s.getType()!=null) {
					securityDefinitionJson.put("type", s.getType());
				}
				
				if(s.getScopes()!=null&& s.getScopes().getScope()!=null) {
					JSONObject scopesJson = new JSONObject();
					for(Scope scope : s.getScopes().getScope() ) {
						if(scope.getDescription()==null) {
							throw new Exception("You must have a <description> tag in Scope object.");
						}
						
						if(scope.getName()==null) {
							throw new Exception("You must have a <name> tag in Scope object.");
						}
						
						scopesJson.put(scope.getName(), scope.getDescription());
					}
					
					securityDefinitionJson.put("scopes", scopesJson);
					
				}
				
				if(s.getSecurityName()==null) {
					throw new Exception("You must have a <securityName> tag in SecurityDefinition object !");
				}
				
				securityDefinitionsJson.put(s.getSecurityName(), securityDefinitionJson);
				
				
			}
			
			swaggerJson.put("securityDefinitions", securityDefinitionsJson);
			
			
			
		}
		
		
		return swaggerJson;

	}
	
	/**
	 * Convert a common data type String into Swagger Type and Format
	 * @param dataType
	 * @return String with the Swagger Type and Format corresponding to the dataType input
	 * @throws DataTypeException
	 */
	private static String dataTypeToTypeAndFormat(String dataType) throws DataTypeException {
		
		if(dataType==null || dataType.equals("")) {
			throw new DataTypeException(dataType);
		}
		

		switch(dataType.toLowerCase()) {
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
		case "datetime":
			return "string/date-time";
		case "password":
			return "string/password";
		case "array":
			return "array/";
		default:
			throw new DataTypeException(dataType);
			
		}
	}
	
	/**
	 * Convert the content of a file into a Swagger Json documentation
	 * @param file path of the file with its name and .xml extension at the end
	 * @return Global object
	 */
	public static String xmlFileToSwaggerJson(File file) {
		try {
			
			JAXBContext jaxbContext = JAXBContext.newInstance(Global.class);
			
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			
			Global global = (Global) unmarshaller.unmarshal(file);
			
			String result = XmlParser.xmlToSwaggerJson(global).toString(2);
			System.out.println(result);
			return result;

			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Convert a XML String into a Swagger Json documentation
	 * @param xml XML input
	 * @return Global object
	 */
	public static String xmlStringToSwaggerJson(String xml) {
		try {
			
			JAXBContext jaxbContext = JAXBContext.newInstance(Global.class);
			
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			
			InputStream stream = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
			
			Global global = (Global) unmarshaller.unmarshal(stream);
			
			String result = XmlParser.xmlToSwaggerJson(global).toString(2);
			System.out.println(result);
			return result;

			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Convert the content of a file into a java class
	 * @param file path of the file with its name and .xml extension at the end
	 * @return Global object
	 */
	public static Global xmlFileToJavaClass(File file) {
		try {
			
			JAXBContext jaxbContext = JAXBContext.newInstance(Global.class);
			
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			
			Global global = (Global) unmarshaller.unmarshal(file);
			
			return global;

			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}	

	/**
	 * Convert a XML String into a Global Java class
	 * @param xml XML input
	 * @return Global object
	 */
	public static Global xmlStringToJavaClass(String xml) {
		try {
			
			JAXBContext jaxbContext = JAXBContext.newInstance(Global.class);
			
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			
			InputStream stream = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
			
			Global global = (Global) unmarshaller.unmarshal(stream);
			
			return global;

			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
