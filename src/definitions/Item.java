package definitions;

import javax.xml.bind.annotation.XmlElement;

public class Item {
	
	private String type;
	
	@XmlElement(name = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	
	

}
