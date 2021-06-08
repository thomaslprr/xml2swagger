package global;

import java.util.List;

import security.SecurityDefinition;

public class SecurityDefinitions {
	
	private List<SecurityDefinition> securityDefinition;

	public SecurityDefinitions() {
		super();
	}

	public SecurityDefinitions(List<SecurityDefinition> securityDefinition) {
		super();
		this.securityDefinition = securityDefinition;
	}

	public List<SecurityDefinition> getSecurityDefinition() {
		return securityDefinition;
	}

	public void setSecurityDefinition(List<SecurityDefinition> securityDefinition) {
		this.securityDefinition = securityDefinition;
	}
	
	

}
