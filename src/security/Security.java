package security;

public class Security {
	
	private String name;
	private Values values;
	
	public Security() {
		super();
	}

	public Security(String name, Values values) {
		super();
		this.name = name;
		this.values = values;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Values getValues() {
		return values;
	}
	
	public void setValues(Values values) {
		this.values = values;
	}
}
