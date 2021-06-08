package paths;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Produces {
	
	private List<String> produceType;
	
	public Produces() {
		super();
	}

	public Produces(List<String> produceType) {
		super();
		this.produceType = produceType;
	}

	@XmlElement(name="produce")
	public List<String> getProduceType() {
		return produceType;
	}

	public void setProduceType(List<String> produceType) {
		this.produceType = produceType;
	}
	
	

}
