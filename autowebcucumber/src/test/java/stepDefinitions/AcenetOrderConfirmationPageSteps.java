package stepDefinitions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cucumber.TestContext;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.AceNetHomePage;
import pageObjects.AcenetOrdersConfirmationPage;
import pageObjects.LogsTest;
import utils.ScreenshotCapture;

public class AcenetOrderConfirmationPageSteps {
	
	TestContext testContext;
	AcenetOrdersConfirmationPage odrconfirm;
		
	List<Map<String,String>> dataMap=new ArrayList<Map<String,String>>();
	ScreenshotCapture screenshotObj;
	
	
	
	Logger log=LogManager.getLogger("AcenetOrderConfirmationPageSteps");
	
	
	
	public AcenetOrderConfirmationPageSteps(TestContext testContext) {
		super();
		this.testContext = testContext;
		odrconfirm=testContext.getPageObjectManager().getAcenetOrdersConfirmationPage();
		screenshotObj=new ScreenshotCapture(testContext);
	}
	
	@And("^Confirm Orders has been placed$")
	public void confirmOrder() throws InterruptedException {
		odrconfirm.ConfirmOrder();
		log.info("Confirm Order");
		screenshotObj.captureScreenshot("Order Confirmation");
	}
	
	@Then("^Verify placed order is present in stock reserve list \"([^\"]*)\"$")
	public void verify_placed_order_is_present_in_stock_reserve_list(String verifySearchCategory) throws Throwable {
		odrconfirm.openStockReserveList(verifySearchCategory);
	}
	
}
