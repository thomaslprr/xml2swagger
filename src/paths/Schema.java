package paths;

public class Schema {
	
	private String ref;
	
	private String type;
	
	private Items items;
	
	private AdditionalProperties additionalProperties;

	public Schema() {
		super();
	}

	public Schema(String ref, String type, Items items, AdditionalProperties additionalProperties) {
		super();
		this.ref = ref;
		this.type = type;
		this.items = items;
		this.additionalProperties = additionalProperties;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Items getItems() {
		return items;
	}

	public void setItems(Items items) {
		this.items = items;
	}

	public AdditionalProperties getAdditionalProperties() {
		return additionalProperties;
	}

	public void setAdditionalProperties(AdditionalProperties additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
	
	
	

}
