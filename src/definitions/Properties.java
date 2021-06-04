package definitions;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Properties {
	
	private List<Property> properties;

	@XmlElement(name = "property")
	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}
	
	

}
