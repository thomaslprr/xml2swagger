package definitions;


import javax.xml.bind.annotation.XmlElement;

public class Object {
	
	private String name;
	
	private PropertiesBeans properties;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@XmlElement(name = "properties")
	public PropertiesBeans getProperties() {
		return properties;
	}

	public void setProperties(PropertiesBeans properties) {
		this.properties = properties;
	}
	
	

}
