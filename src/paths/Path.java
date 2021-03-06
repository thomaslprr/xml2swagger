package paths;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="path")
public class Path {
	
	private String name;
	
	private Methods methods;
	
	public Path() {
		super();
	}

	public Path(String name, Methods methods) {
		super();
		this.name = name;
		this.methods = methods;
	}

	@XmlElement(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Methods getMethods() {
		return methods;
	}

	public void setMethods(Methods methods) {
		this.methods = methods;
	}
	
	

}
