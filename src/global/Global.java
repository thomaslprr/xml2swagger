package global;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import definitions.ObjectsBeans;

@XmlRootElement(name="global")
public class Global {
	
	private ObjectsBeans objects;

	public ObjectsBeans getObjects() {
		return objects;
	}

	public void setObjects(ObjectsBeans objects) {
		this.objects = objects;
	}
	
	private Rest rest;

	
	@XmlElement(name="rest")
	public Rest getRest() {
		return rest;
	}

	public void setRest(Rest rest) {
		this.rest = rest;
	}
	
	

}
