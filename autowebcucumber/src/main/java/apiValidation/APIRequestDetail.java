package apiValidation;

import org.json.simple.JSONObject;

public class APIRequestDetail {
	private String apiUri;
	private String method;
	private JSONObject headerContent;
	private JSONObject bodyContent;
	private String apiIdentifier;
	private String expectedResponseCode;
	
	public String getApiUri() {
		return apiUri;
	}
	public void setApiUri(String apiUri) {
		this.apiUri = apiUri;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getApiIdentifier() {
		return apiIdentifier;
	}
	public void setApiIdentifier(String apiIdentifier) {
		this.apiIdentifier = apiIdentifier;
	}
	public JSONObject getHeaderContent() {
		return headerContent;
	}
	public void setHeaderContent(JSONObject headerContent) {
		this.headerContent = headerContent;
	}
	public JSONObject getBodyContent() {
		return bodyContent;
	}
	public void setBodyContent(JSONObject bodyContent) {
		this.bodyContent = bodyContent;
	}
	public String getExpectedResponseCode() {
		return expectedResponseCode;
	}
	public void setExpectedResponseCode(String expectedResponseCode) {
		this.expectedResponseCode = expectedResponseCode;
	}
	
}