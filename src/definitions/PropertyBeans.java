package definitions;

import javax.xml.bind.annotation.XmlElement;

public class PropertyBeans {
	
	private String name;
	private String type;
	private String format;
	
	private Item items;

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

	@XmlElement(name = "format")
	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	@XmlElement(name = "items")
	public Item getItems() {
		return items;
	}

	public void setItems(Item items) {
		this.items = items;
	}

	
	
	
}
