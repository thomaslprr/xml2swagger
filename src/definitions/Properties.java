package definitions;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Properties {
	
	private List<Property> property;

	public Properties(List<Property> property) {
		super();
		this.property = property;
	}

	public Properties() {
		super();
	}

	@XmlElement(name = "property")
	public List<Property> getProperties() {
		return this.property;
	}

	public void setProperties(List<Property> property) {
		this.property = property;
	}
	
	

}
