package definitions;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Enums {

	private List<String> enume;
	
	public Enums(List<String> enume) {
		super();
		this.enume = enume;
	}
	
	@XmlElement(name="enum")
	public List<String> getEnum() {
		return enume;
	}

	public void setEnum(List<String> enume) {
		this.enume = enume;
	}

}
