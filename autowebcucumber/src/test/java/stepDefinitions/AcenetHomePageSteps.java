package stepDefinitions;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;


import com.epam.reportportal.service.ReportPortal;
import com.google.common.io.Files;
import com.google.common.io.Resources;

import cucumber.TestContext;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pageObjects.AceNetHomePage;
import pageObjects.LogsTest;
import utils.ScreenshotCapture;

public class AcenetHomePageSteps {

	TestContext testContext;
	AceNetHomePage page;

	String searchResult;
	List<Map<String,String>> dataMap=new ArrayList<Map<String,String>>();
	ScreenshotCapture screenshotObj;
	//private static final Logger LOGGER = LoggerFactory.getLogger("AceNetHomePageSearchAnArticleTestSteps");
	Logger log=LogManager.getLogger("AcenetHomePageSteps");		


	public AcenetHomePageSteps(TestContext testContext) {
		super();
		this.testContext = testContext;
		page=testContext.getPageObjectManager().getAceNetHomePage();
		screenshotObj=new ScreenshotCapture(testContext);
	}
	

	//commented by Siva
	/*@Then("^User enters the search key$")
	public void searchForAnArticle() throws Exception {
		page.searchAnArticle("10417");
		//10419
		LOGGER.info("Logged in to store");
		screenshotObj.captureScreenshot("Login To Store Page");
		}*/
	@Then("^User enters the search key \"([^\"]*)\"$")
	public void user_enters_the_search_key(String strArg1) throws Throwable {
		page.searchAnArticle(strArg1);
		log.info("Enter search article");


		screenshotObj.captureScreenshot("Enter search article");  
	}


	/*@Then("^User enters the search Category$")
	public void SearchArticleItemDetailsPage() throws Exception {

		lTest.searchAnArticle("hammer");
		log.info("Search an article");
		screenshotObj.captureScreenshot("Login To Store Page");
	} converted to below regular expression*/

	@Then("^User enters the search Category \"([^\"]*)\"$")
	public void user_enters_the_search_category_something(String strArg1) throws Throwable {
		page.searchAnArticle(strArg1);
		log.info("Search an article");
		screenshotObj.captureScreenshot("Login To Store Page");
	}

	@Then("^User enters the searchCategory \"([^\"]*)\"$")
	public void user_enters_the_searchCategory(String searchCategory) throws Throwable {
		page.searchAnArticle(searchCategory);
		screenshotObj.captureScreenshot("Login To Store Page");
	}
	@Given("^user is on AceNet Home Page$")
	public void userIsOnHomePage() throws Exception {

		page.loadPage();
		screenshotObj.captureScreenshot("Home Page");
	}


	@Then("^User Navigates to the store$")
	public void user_Navigates_to_the_store() throws Throwable {
		// Write code here that turns the phrase above into concrete actions

		log.info("Select the Store.");		
		log.debug("Switch to iframe Home Page");
		page.switchToiFrame();
		page.loginToFirstStore();
		page.confirmLogin();
		screenshotObj.captureScreenshot("Navegated To Store");   

	}


	@Then("^User Select the store \"([^\"]*)\"$")
	public void user_Navigates_to_the_store(String storeNumber) throws Throwable 
	{
		log.info("Select the Store.");		
		/*log.debug("Switch to iframe Home Page");
		page.switchToiFrame();
		page.loginToFirstStore(storeNumber);
		page.confirmLogin();*/
		page.SelectingStore(storeNumber);
		screenshotObj.captureScreenshot("Navegated To Store");   

	}






}





