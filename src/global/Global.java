package global;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import definitions.Objects;

@XmlRootElement(name="global")
public class Global {
	
	private Objects objects;

	public Global() {
		super();
	}

	public Global(Objects objects, Rest rest) {
		super();
		this.objects = objects;
		this.rest = rest;
	}

	public Objects getObjects() {
		return objects;
	}

	public void setObjects(Objects objects) {
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
