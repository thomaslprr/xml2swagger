package global;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import header.InfoBeans;
import paths.Path;
import paths.Paths;

public class Rest {

	private String swaggerVersion;
	private InfoBeans info;
	private String basePath;
	
	private Tags tags;
	private Paths paths;

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

	public Tags getTags() {
		return tags;
	}
	
	@XmlElement(name="paths")
	public Paths getPaths() {
		return paths;
	}

	public void setPaths(Paths paths) {
		this.paths = paths;
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

	public void setTags(Tags tags) {
		this.tags = tags;
	}

	
}
