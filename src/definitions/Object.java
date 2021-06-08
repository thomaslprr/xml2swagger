package definitions;


import javax.xml.bind.annotation.XmlElement;

public class Object {
	
	private String name;
	
	private Properties properties;
	
	public Object(String name, Properties properties) {
		super();
		this.name = name;
		this.properties = properties;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@XmlElement(name = "properties")
	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}	

}
