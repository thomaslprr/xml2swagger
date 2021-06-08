package paths;

public class Response {
	
	private String name;
	private String description;
	private Schema schema;
	
	public Response() {
		super();
	}
	public Response(String name, String description, Schema schema) {
		super();
		this.name = name;
		this.description = description;
		this.schema = schema;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Schema getSchema() {
		return schema;
	}
	public void setSchema(Schema schema) {
		this.schema = schema;
	}	

}
