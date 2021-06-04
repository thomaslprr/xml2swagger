package definitions;

import javax.xml.bind.annotation.XmlElement;

public class Item {
	
	private String type;
	
	private String ref;
	
	@XmlElement(name = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	
	

	
	

}
