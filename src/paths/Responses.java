package paths;

import javax.xml.bind.annotation.XmlElement;

public class Responses {
	
	private Default defaultR;

	@XmlElement(name="default")
	public Default getDefaultR() {
		return defaultR;
	}

	public void setDefaultR(Default defaultR) {
		this.defaultR = defaultR;
	}
	
	
	
	

}
