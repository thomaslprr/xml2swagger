package paths;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Consumes {
	
	private List<String> consumeType;
	
	public Consumes(List<String> consumeType) {
		super();
		this.consumeType = consumeType;
	}

	@XmlElement(name="consume")
	public List<String> getConsumeType() {
		return consumeType;
	}

	public void setConsumeType(List<String> consumeType) {
		this.consumeType = consumeType;
	}

}
