package header;

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

	
	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	
	public String getTermsOfService() {
		return termsOfService;
	}

	public void setTermsOfService(String termsOfSerivce) {
		this.termsOfService = termsOfSerivce;
	}

	public String getDescription() {
		return description;
	}
	
	public String getVersion() {
		return version;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getContactName() {
		return contactName;
	}
	
	public String getLicenceName() {
		return licenceName;
	}
	
	public String getLicenceUrl() {
		return licenceUrl;
	}
	
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
