package global;

import javax.xml.bind.annotation.XmlElement;

public class Tag {
	
	private String name;
	private String description;
	private ExternalDocs externalDocs;
	
	@XmlElement(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@XmlElement(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@XmlElement(name="external-docs")
	public ExternalDocs getExternalDocs() {
		return externalDocs;
	}
	public void setExternalDocs(ExternalDocs externalDocs) {
		this.externalDocs = externalDocs;
	}
	
	

}
