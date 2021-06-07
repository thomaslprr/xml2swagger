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
	private int minimum;
	private int maximum;
	private boolean exclusiveMaximum;
	private boolean exclusiveMinimum;
	private int maxLength;
	private int minLength;
	private int maxItems;	
	private boolean	uniqueItems;
	
	
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
	public int getMinimum() {
		return minimum;
	}
	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}
	public int getMaximum() {
		return maximum;
	}
	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}
	public boolean isExclusiveMaximum() {
		return exclusiveMaximum;
	}
	public void setExclusiveMaximum(boolean exclusiveMaximum) {
		this.exclusiveMaximum = exclusiveMaximum;
	}
	public boolean isExclusiveMinimum() {
		return exclusiveMinimum;
	}
	public void setExclusiveMinimum(boolean exclusiveMinimum) {
		this.exclusiveMinimum = exclusiveMinimum;
	}
	public int getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}
	public int getMinLength() {
		return minLength;
	}
	public void setMinLength(int minLength) {
		this.minLength = minLength;
	}
	public int getMaxItems() {
		return maxItems;
	}
	public void setMaxItems(int maxItems) {
		this.maxItems = maxItems;
	}
	public boolean isUniqueItems() {
		return uniqueItems;
	}
	public void setUniqueItems(boolean uniqueItems) {
		this.uniqueItems = uniqueItems;
	}
	
	
	
	
	
	
	
	
	

}
