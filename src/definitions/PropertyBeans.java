package definitions;

import javax.xml.bind.annotation.XmlElement;

public class PropertyBeans {
	
	private String name;
	private String type;
	
	private String ref;
	
	private Item items;
	
	private Enums enums;


	@XmlElement(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@XmlElement(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@XmlElement(name = "items")
	public Item getItems() {
		return items;
	}

	public void setItems(Item items) {
		this.items = items;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public Enums getEnums() {
		return enums;
	}

	public void setEnums(Enums enums) {
		this.enums = enums;
	}
	
}
