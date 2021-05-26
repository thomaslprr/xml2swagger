package paths;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import global.Tags;

public class Method {
	
	private String type;
	private Tags tags;
	private String operationId;
	private Produces produces;
	private Parameters parameters;
	private Responses responses;
	
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
	
	@XmlElement(name="operation-id")
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
	
	
	

}
