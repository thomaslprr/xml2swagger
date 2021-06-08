package security;

public class SecurityDefinition {
	
	private String securityName;
	
	private String type;
	private String description;
	private String headerName;
	private String in;
	private String flow;
	private String authorizationUrl;
	private String tokenUrl;
	private Scopes scopes;

	public SecurityDefinition(String securityName, String type, String description, String headerName, String in,
			String flow, String authorizationUrl, String tokenUrl, Scopes scopes) {
		super();
		this.securityName = securityName;
		this.type = type;
		this.description = description;
		this.headerName = headerName;
		this.in = in;
		this.flow = flow;
		this.authorizationUrl = authorizationUrl;
		this.tokenUrl = tokenUrl;
		this.scopes = scopes;
	}
	public String getSecurityName() {
		return securityName;
	}
	public void setSecurityName(String securityName) {
		this.securityName = securityName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getHeaderName() {
		return headerName;
	}
	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}
	public String getIn() {
		return in;
	}
	public void setIn(String in) {
		this.in = in;
	}
	public String getFlow() {
		return flow;
	}
	public void setFlow(String flow) {
		this.flow = flow;
	}
	public String getAuthorizationUrl() {
		return authorizationUrl;
	}
	public void setAuthorizationUrl(String authorizationUrl) {
		this.authorizationUrl = authorizationUrl;
	}
	public String getTokenUrl() {
		return tokenUrl;
	}
	public void setTokenUrl(String tokenUrl) {
		this.tokenUrl = tokenUrl;
	}
	public Scopes getScopes() {
		return scopes;
	}
	public void setScopes(Scopes scopes) {
		this.scopes = scopes;
	}
	


	

}
