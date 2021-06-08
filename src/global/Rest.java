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
	
	private SecurityDefinitions securityDefinitions;
	
	public Rest(Info info, String basePath, String host, TagsDetailled tags, Paths paths, Schemes schemes,
			SecurityDefinitions securityDefinitions) {
		super();
		this.info = info;
		this.basePath = basePath;
		this.host = host;
		this.tags = tags;
		this.paths = paths;
		this.schemes = schemes;
		this.securityDefinitions = securityDefinitions;
	}

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

	public SecurityDefinitions getSecurityDefinitions() {
		return securityDefinitions;
	}

	public void setSecurityDefinitions(SecurityDefinitions securityDefinitions) {
		this.securityDefinitions = securityDefinitions;
	}

	
	
	
}
