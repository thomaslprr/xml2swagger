package security;

import java.util.List;

public class Securities {
	
	private List<Security> security;

	public Securities() {
		super();
	}

	public Securities(List<Security> security) {
		super();
		this.security = security;
	}

	public List<Security> getSecurity() {
		return security;
	}

	public void setSecurity(List<Security> security) {
		this.security = security;
	}
	

}
