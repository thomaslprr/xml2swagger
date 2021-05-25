package definitions;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name= "objects")
public class ObjectsBeans {
	
	private List<ObjectBeans> object;

	@XmlElement(name = "object")
	public List<ObjectBeans> getObjects() {
		return object;
	}

	public void setObjects(List<ObjectBeans> objects) {
		this.object = objects;
	}
	
	
	
	
}
