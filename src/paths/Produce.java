package paths;

import javax.xml.bind.annotation.XmlElement;

public class Produce {

	private String produceType ;

	@XmlElement(name="produce-type")
	public String getProduceType() {
		return produceType;
	}

	public void setProduceType(String produceType) {
		this.produceType = produceType;
	} 
	
	
	
}
