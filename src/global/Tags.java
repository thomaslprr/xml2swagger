package global;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;


public class Tags {
	
	private List<String> tagName;

	public Tags(List<String> tagName) {
		super();
		this.tagName = tagName;
	}

	@XmlElement(name="tag")
	public List<String> getTag() {
		return tagName;
	}

	public void setTag(List<String> tagName) {
		this.tagName = tagName;
	}
	
	
	

}
