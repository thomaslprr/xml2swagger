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
	private String host;
	
	private TagsDetailled tags;
	private Paths paths;
	
	private Schemes schemes;
	
	

	public Schemes getSchemes() {
		return schemes;
	}

	public void setSchemes(Schemes schemes) {
		this.schemes = schemes;
	}

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

	public TagsDetailled getTags() {
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

	public void setTags(TagsDetailled tags) {
		this.tags = tags;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	
	
}
