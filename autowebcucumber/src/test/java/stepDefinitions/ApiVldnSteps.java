package stepDefinitions;

import java.io.IOException;

import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import apiValidation.WebServiceValidator;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class ApiVldnSteps {
	WebServiceValidator apiValidator=new WebServiceValidator();
	int outputResponseCode;
	protected CloseableHttpResponse response=null; 
	static String responseBody;
	
	public String getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}

	@Given("^user has all the required details$")
	public boolean userHasDetails()
	{
	 return true;
	}
	
	@Then("^user validates the \"([^\"]*)\" API$")
	public void userValidatesApi(String apiIdentifier)
	{
		//outputResponseCode=
		response=apiValidator.accessAndValidateApiResponse(apiIdentifier,null);
		if(response!=null) {
			outputResponseCode=response.getStatusLine().getStatusCode();
		//	System.out.println("Response Code:"+outputResponseCode);
			try {
				this.responseBody = EntityUtils.toString(response.getEntity());
				setResponseBody(responseBody);
				System.out.println("Response Body:"+responseBody);
			} catch (ParseException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}
	}
	
	@And("^prints the status code of API response$")
	public void printResponseStatusCode()
	{
		System.out.println("Response Code from Step Definition Output:"+outputResponseCode);
	}
	
	@Then("^user validates the responses of all APIs$")
	public void userValidatesAllApis()
	{
		String apiIdentifier="";
		apiValidator.accessAndValidateApiResponse(apiIdentifier,null);
	}
}
