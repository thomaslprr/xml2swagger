package global;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import header.InfoBeans;
import header.TagBeans;

public class Rest {

	private String swaggerVersion;
	private InfoBeans info;
	private String basePath;
	
	private List<TagBeans> tags;
	//private List<Path> paths;

	@XmlElement(name="swagger-version")
	public String getSwaggerVersion() {
		return swaggerVersion;
	}

	@XmlElement(name="info")
	public InfoBeans getInfo() {
		return info;
	}

	@XmlElement(name="basePath")
	public String getBasePath() {
		return basePath;
	}

	@XmlElement(name="tags")
	public List<TagBeans> getTags() {
		return tags;
	}

	public void setSwaggerVersion(String swaggerVersion) {
		this.swaggerVersion = swaggerVersion;
	}

	public void setInfo(InfoBeans info) {
		this.info = info;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public void setTags(List<TagBeans> tags) {
		this.tags = tags;
	}
	
	
	
	
	
	
}
