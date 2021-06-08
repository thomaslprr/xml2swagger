package definitions;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Properties {
	
	private List<Property> property;

	@XmlElement(name = "property")
	public List<Property> getProperties() {
		return this.property;
	}

	public void setProperties(List<Property> property) {
		this.property = property;
	}
	
	

}
