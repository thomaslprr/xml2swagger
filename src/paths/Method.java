package paths;

import javax.xml.bind.annotation.XmlElement;

import exceptions.SummaryException;
import global.Tags;
import security.Securities;

public class Method {
	
	private String type;
	private Tags tags;
	private String operationId;
	private Produces produces;
	private Consumes consumes;
	private Parameters parameters;
	private Responses responses;
	private String summary;
	private boolean deprecated;
	private String description;
	private Securities securities;
	
	public Method() {
		super();
	}

	public Method(String type, Tags tags, String operationId, Produces produces, Consumes consumes,
			Parameters parameters, Responses responses, String summary, boolean deprecated, String description,
			Securities securities) {
		super();
		this.type = type;
		this.tags = tags;
		this.operationId = operationId;
		this.produces = produces;
		this.consumes = consumes;
		this.parameters = parameters;
		this.responses = responses;
		this.summary = summary;
		this.deprecated = deprecated;
		this.description = description;
		this.securities = securities;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Tags getTags() {
		return tags;
	}
	public void setTags(Tags tags) {
		this.tags = tags;
	}
	
	@XmlElement(name="operationId")
	public String getOperationId() {
		return operationId;
	}
	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}
	public Produces getProduces() {
		return produces;
	}
	public void setProduces(Produces produces) {
		this.produces = produces;
	}
	public Parameters getParameters() {
		return parameters;
	}
	public void setParameters(Parameters parameters) {
		this.parameters = parameters;
	}
	public Responses getResponses() {
		return responses;
	}
	public void setResponses(Responses responses) {
		this.responses = responses;
	}
	public boolean isDeprecated() {
		return deprecated;
	}
	public void setDeprecated(boolean deprecated) {
		this.deprecated = deprecated;
	}
	public String getSummary() throws SummaryException {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Consumes getConsumes() {
		return consumes;
	}
	public void setConsumes(Consumes consumes) {
		this.consumes = consumes;
	}
	public Securities getSecurities() {
		return securities;
	}
	public void setSecurities(Securities securities) {
		this.securities = securities;
	}
	
	
	
	
	
	

}
