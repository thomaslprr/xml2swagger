package paths;

public class Schema {
	
	private String ref;
	
	private String type;
	
	private Items items;
	
	private AdditionalProperties additionalProperties;

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
