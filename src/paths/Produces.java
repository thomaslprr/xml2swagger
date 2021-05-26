package paths;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Produces {
	
	private List<String> produceType;
	
	@XmlElement(name="produce-type")
	public List<String> getProduceType() {
		return produceType;
	}

	public void setProduceType(List<String> produceType) {
		this.produceType = produceType;
	}
	
	

}
