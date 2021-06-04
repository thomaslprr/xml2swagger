package paths;

public class Parameter {
	
	private String in;
	private String name;
	private boolean required ;
	private Schema schema;
	private String type;
	private String description;
	private Items items;
	private String collectionFormat;
	
	
	public String getIn() {
		return in;
	}
	public void setIn(String in) {
		this.in = in;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	public Schema getSchema() {
		return schema;
	}
	public void setSchema(Schema schema) {
		this.schema = schema;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Items getItems() {
		return items;
	}
	public void setItems(Items items) {
		this.items = items;
	}
	public String getCollectionFormat() {
		return collectionFormat;
	}
	public void setCollectionFormat(String collectionFormat) {
		this.collectionFormat = collectionFormat;
	}
	
	
	
	
	
	
	
	

}
