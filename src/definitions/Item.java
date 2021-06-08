package definitions;

import javax.xml.bind.annotation.XmlElement;

public class Item {
	
	private String type;
	
	private String ref;
	
	public Item(String type, String ref) {
		super();
		this.type = type;
		this.ref = ref;
	}
	
	public Item() {
		
	}
	
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
