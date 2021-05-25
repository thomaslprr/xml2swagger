package definitions;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class ObjectsBeans {
	
	private List<ObjectBeans> object;

	
	@XmlElement(name = "object")
	public List<ObjectBeans> getObject() {
		return object;
	}

	public void setObject(List<ObjectBeans> object) {
		this.object = object;
	}
	
	

	
	
	
	
	
}
