package global;

public class Tag {
	
	private String name;
	private String description;
	private ExternalDocs externalDocs;
	
	public Tag() {
		super();
	}
	
	public Tag(String name, String description, ExternalDocs externalDocs) {
		super();
		this.name = name;
		this.description = description;
		this.externalDocs = externalDocs;
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
	public ExternalDocs getExternalDocs() {
		return externalDocs;
	}
	public void setExternalDocs(ExternalDocs externalDocs) {
		this.externalDocs = externalDocs;
	}
	
	

}
