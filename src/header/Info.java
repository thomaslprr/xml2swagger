package header;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="info")
public class Info {
	
	
	private String description;
	private String version;
	private String title;
	private String contactName;
	private String contactEmail;
	private String contactUrl;
	private String licenceName;
	private String licenceUrl;
	private String termsOfService;

	
	@XmlElement(name="contact-email")	
	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	
	@XmlElement(name="terms-of-service")
	public String getTermsOfService() {
		return termsOfService;
	}

	public void setTermsOfService(String termsOfSerivce) {
		this.termsOfService = termsOfSerivce;
	}

	@XmlElement(name="description")	
	public String getDescription() {
		return description;
	}
	
	@XmlElement(name="version")	
	public String getVersion() {
		return version;
	}
	
	@XmlElement(name="title")	
	public String getTitle() {
		return title;
	}
	
	@XmlElement(name="contact-name")	
	public String getContactName() {
		return contactName;
	}
	
	@XmlElement(name="licence-name")	
	public String getLicenceName() {
		return licenceName;
	}
	
	@XmlElement(name="licence-url")	
	public String getLicenceUrl() {
		return licenceUrl;
	}
	
	
	@XmlElement(name="contact-url")
	public String getContactUrl() {
		return contactUrl;
	}

	public void setContactUrl(String contactUrl) {
		this.contactUrl = contactUrl;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public void setLicenceName(String licenceName) {
		this.licenceName = licenceName;
	}

	public void setLicenceUrl(String licenceUrl) {
		this.licenceUrl = licenceUrl;
	}
	
	
	
	
	

}