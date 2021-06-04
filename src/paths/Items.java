package paths;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import definitions.Enums;

public class Items {
	
	private String ref;
	
	private String defaulte;
	
	private Enums enums;
	
	private String type;
	

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	@XmlElement(name="default")
	public String getDefaulte() {
		return defaulte;
	}

	public void setDefaulte(String defaulte) {
		this.defaulte = defaulte;
	}

	public Enums getEnums() {
		return enums;
	}

	public void setEnums(Enums enums) {
		this.enums = enums;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
	

}
