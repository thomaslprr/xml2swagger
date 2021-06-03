package global;


import javax.xml.bind.annotation.XmlElement;

import header.Info;
import paths.Paths;

public class Rest {

	private Info info;
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

	public String getSwaggerVersion() {
		return "2.0";
	}

	@XmlElement(name="info")
	public Info getInfo() {
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

	public void setInfo(Info info) {
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
