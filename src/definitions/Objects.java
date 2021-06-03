package definitions;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Objects {
	
	private List<Object> object;

	
	@XmlElement(name = "object")
	public List<Object> getObject() {
		return object;
	}

	public void setObject(List<Object> object) {
		this.object = object;
	}
	
	

	
	
	
	
	
}
