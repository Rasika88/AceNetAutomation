package stepDefinitions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cucumber.TestContext;
import io.cucumber.java.en.Then;
import pageObjects.ProductManagement;
import utils.ScreenshotCapture;

public class AcenetProductManagementSteps {
	TestContext testContext;
	ProductManagement pmgnmt;
	List<Map<String,String>> dataMap=new ArrayList<Map<String,String>>();
	ScreenshotCapture screenshotObj;
	Logger log=LogManager.getLogger("AcenetHomePageSteps");	
	
	public AcenetProductManagementSteps(TestContext testContext) {
		super();
		this.testContext = testContext;
		pmgnmt=testContext.getPageObjectManager().getProductManagement();
		screenshotObj=new ScreenshotCapture(testContext);
	}

	@Then("^Navigate to discovery assortment planner page$")
	public void navigate_to_discovery_assortment_planner_page() throws Throwable {
			
		
		pmgnmt.NavigateTomanageProductTab();
		pmgnmt.NavigateTodiscoveryAssortmentPlannerPage();
		screenshotObj.captureScreenshot("NavigateTo discovery Assortment PlannerPage");
	}

	@Then("^Select By Merchandise Class$")
	public void select_By_Merchandise_Class() throws Throwable {
		screenshotObj.captureScreenshot("");
	}

	@Then("^Select any Department and MC enter the quantity \"([^\"]*)\"$")
	public void select_any_Department_and_MC_enter_the_quantity(String arg1) throws Throwable {
		pmgnmt.discoveryAssortmentpageSelections();
	    screenshotObj.captureScreenshot("Discovery Assortment Planner Page selections");
	}

	@Then("^Verify the added item is present in discovery cart$")
	public void verify_the_added_item_is_present_in_discovery_cart() throws Throwable {
		screenshotObj.captureScreenshot("");
	}

	@Then("^Select the added the item and checkout$")
	public void select_the_added_the_item_and_checkout() throws Throwable {
		screenshotObj.captureScreenshot("");
	}

	@Then("^Input PO Number and click Confirm Checkout$")
	public void input_PO_Number_and_click_Confirm_Checkout() throws Throwable {
		screenshotObj.captureScreenshot("Order Confirmation");
	}
}
