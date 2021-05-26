package global;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


public class Tags {
	
	private List<String> tagName;

	@XmlElement(name="tag-name")
	public List<String> getTag() {
		return tagName;
	}

	public void setTag(List<String> tagName) {
		this.tagName = tagName;
	}
	
	
	

}
