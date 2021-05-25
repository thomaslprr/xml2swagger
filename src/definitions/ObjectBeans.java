package definitions;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class ObjectBeans {
	
	private String name;
	private String type;
	
	private PropertiesBeans properties;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@XmlElement(name = "properties")
	public PropertiesBeans getProperties() {
		return properties;
	}

	public void setProperties(PropertiesBeans properties) {
		this.properties = properties;
	}
	
	

}
