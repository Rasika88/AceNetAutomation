package stepDefinitions;

import java.io.IOException;
import java.util.Random;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import apiValidation.WebServiceValidator;
import cucumber.TestContext;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import utils.ScreenshotCapture;

public class ImageApiVldnSteps {

	WebServiceValidator apiValidator = new WebServiceValidator();
	int outputResponseCode;
	CloseableHttpResponse response = null;
	String photoId = null;
	ApiVldnSteps apiVldnObj;
	String responseStr;
	HttpEntity entity;
	int randomValue;
	JSONObject updatedBodyContent = null;

	ScreenshotCapture screenshotCaptObj;

	/*public ImageApiVldnSteps(TestContext testContext) {
		apiVldnObj = new ApiVldnSteps();
		homePage=testContext.getPageObjectManager().getMacysHomePage();
		screenshotCaptObj=new ScreenshotCapture(testContext);
	}*/

	@Then("^User randomly selects a photo from the list$")
	public String selectPhotoFromList() {
		this.responseStr = ApiVldnSteps.responseBody;
		System.out.println("Fetching Photo ID!");
		JSONArray photosArray = (JSONArray) JSONValue.parse(responseStr);
		Random rand = new Random();
		randomValue = rand.nextInt(photosArray.size() - 1);
		JSONObject photoJson = (JSONObject) photosArray.get(randomValue);
		photoId = photoJson.getOrDefault("id", null).toString();
		return photoId;
	}

	@Then("^adds it to the existing collection$")
	public void addToExistingCollection() {
		String apiIdentifier = "Add Photo to Existing Collection";
		if (photoId != null) {
			updatedBodyContent = new JSONObject();
			updatedBodyContent.put("photo_id", photoId);
			System.out.println("Updated body content:" + updatedBodyContent);
		}
		response = apiValidator.accessAndValidateApiResponse(apiIdentifier, updatedBodyContent);
		if (response != null) {
			outputResponseCode = response.getStatusLine().getStatusCode();
			System.out.println("Response Code:" + outputResponseCode);
			String responseBody;
			try {
				responseBody = EntityUtils.toString(response.getEntity());
				System.out.println("Response Body:" + responseBody);
			} catch (ParseException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@Given("^user is at https://unsplash\\.com/$")
	public void user_is_at_https_unsplash_com() {
	//	homePage.openUnsplashWebsite();
		screenshotCaptObj.captureScreenshotForReportPortal("APIVldn","Old Image Collection");
	}
	
	@Then("^validates the new image on UI$")
	public void validates_the_new_image_on_UI() {
//		homePage.refreshPage();
		screenshotCaptObj.captureScreenshotForReportPortal("APIVldn","New Image Collection");
	}

}
