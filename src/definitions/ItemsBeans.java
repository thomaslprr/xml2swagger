package definitions;

import javax.xml.bind.annotation.XmlElement;

public class ItemsBeans {
	
	private String type;
	private String format;
	
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
	
	

}
