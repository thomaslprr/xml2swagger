package definitions;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class PropertiesBeans {
	
	private List<PropertyBeans> properties;

	@XmlElement(name = "property")
	public List<PropertyBeans> getProperties() {
		return properties;
	}

	public void setProperties(List<PropertyBeans> properties) {
		this.properties = properties;
	}
	
	

}
