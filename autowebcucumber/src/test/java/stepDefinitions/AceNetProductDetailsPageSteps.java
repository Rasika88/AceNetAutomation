package stepDefinitions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cucumber.TestContext;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import pageObjects.AceNetHomePage;
import pageObjects.AcenetArticleDeatilsPage;
import pageObjects.AcenetOrdersConfirmationPage;
import pageObjects.LogsTest;
import utils.ScreenshotCapture;

public class AceNetProductDetailsPageSteps {

	TestContext testContext;
	//AceNetHomePage page;
	//LogsTest Lgtest;
	AcenetArticleDeatilsPage artical;
	//AcenetOrdersConfirmationPage odrConfirm;
	
	String searchResult;
	List<Map<String,String>> dataMap=new ArrayList<Map<String,String>>();
	ScreenshotCapture screenshotObj;
	//private static final Logger LOGGER = LoggerFactory.getLogger("AceNetProductDetailsPageSteps");
	Logger log=LogManager.getLogger("AceNetProductDetailsPageSteps");
	
	public AceNetProductDetailsPageSteps(TestContext testContext) {
		super();
		this.testContext = testContext;
		artical=testContext.getPageObjectManager().getAcenetArticleDeatilsPage();
		screenshotObj=new ScreenshotCapture(testContext);
	}
	
	

	@Then("^the user is navigated to the Article Details page$")
	public void userNavigatedToAticleDetailsPage() throws  Throwable {

		artical.switchToPDPwindow();
		artical.confirmPdPloaded();
		artical.switchedToiframe();
		screenshotObj.captureScreenshot("Article Details Page");

	}

	@Then("^User enters the details and place order$")
	public void enterArticleDetailsForOrderPlacemet() throws  Throwable {

		artical.checkRSCquantityOfArticle();
		artical.enterOrderQty();
		artical.ifpopUPcloseBttn();
		artical.expressCheckOutClick();

		screenshotObj.captureScreenshot("Article Details Page");

	}

	@Then("^User enters the details and place Ship To Customer order$")
	public void enterArticleDetailsForShipToCustomerorderPlacemet() throws  Throwable {
		//page.switchedToiframe();

		artical.selectShipToCustomerOrder();
		artical.enterOrderQtyShipToRetailer();
		artical.ifpopUPcloseBttn();
		artical.expressCheckOutSTRClick();
		artical.checkOutShipToRetailor();


		screenshotObj.captureScreenshot("Article Details Page");
	}

	@Then("^User enters the shipping method and order qty in product details page \"([^\"]*)\" \"([^\"]*)\"$")
	public void user_enters_the_shipping_method_and_order_qty_in_product_details_page(String shippingMethod, String productQty) throws Throwable {
		artical.selectShippingMethodAndProductQty(shippingMethod,productQty);
		if(shippingMethod.equalsIgnoreCase("RSC Pickup"))
		{
		artical.expressCheckOutSTRClick();
		artical.OrderCheckout(shippingMethod);
	}
		else if(shippingMethod.equalsIgnoreCase("Stock Reserve"))
		{
			artical.expressCheckOutSTRClick();
			artical.OrderCheckout(shippingMethod);
		}
		else if(shippingMethod.equalsIgnoreCase("Ship to Retailer"))
		{
			artical.expressCheckOutSTRClick();		
			artical.checkOutShipToRetailor();
		}
		else{
		log.error("Shipping method not selected");
		}
	}
	@Then("^Checkout to place an order \"([^\"]*)\"$")
	public void Checkout_to_place_an_order(String shipping Method)
	{
		if(shippingMethod.equalsIgnoreCase("RSC Pickup"))
	{
	artical.expressCheckOutSTRClick();
	artical.OrderCheckout(shippingMethod);
	}
	else if(shippingMethod.equalsIgnoreCase("Stock Reserve"))
	{
		artical.expressCheckOutSTRClick();
		artical.OrderCheckout(shippingMethod);
	}
	else if(shippingMethod.equalsIgnoreCase("Ship to Retailer"))
	{
		artical.expressCheckOutSTRClick();		
		artical.checkOutShipToRetailor();
	}
	else{
	log.error("Shipping method not selected");
	}
		
	}

	@Then("^User confirms Article Dettails$")
	public void userConfirmArticleDetails() throws  Throwable {
		//page.switchedToiframe();

		artical.confirmSKUDetails();
		artical.checkRSCquantityOfArticle();
		artical.checkOmQuantity();
		artical.checkStoreLocation();
		artical.checkRSCdetails();	

		artical.checkProductOverview();

		screenshotObj.captureScreenshot("Article Details Page");
	}


	@Then("^User click Select RSC quantity check box")
	public void SelectRSCQuantityCheckbox() throws Exception {

		artical.switchToiframeRetailAppHost();
		artical.clickSelectRSCQuantityCheckbox();

	}


	@And("^Confirm the RSC quantity displayed$")
	public void confirmRSCQuantityDisplayed() throws Exception {
		artical.switchToiframeRetailAppHost();
		//artical.confirmRSCquantity();
		artical.Verify_RSCQtyCheck();

		//page.clickSelectRSCQuantityCheckbox();

	}
	@Then("^Click on productView tab and verify product view$")
	public void click_on_productView_tab_and_verify_product_view() throws Throwable {
	   artical.VerifyProductdetailsTabs();
	    screenshotObj.captureScreenshot("Article Details Page");
	}

	@Then("^Click on specfication tab and verify specification$")
	public void click_on_specfication_tab_and_verify_specification() throws Throwable {
		artical.VerifyProductdetailsTabs();
		   screenshotObj.captureScreenshot("Article Details Page");
	}

	@Then("^Click on Hazard badge and verify HAZMAT text$")
	public void click_on_Hazard_badge_and_verify_HAZMAT_text() throws Throwable {
		artical.VerifyProductdetailsTabs();
		   screenshotObj.captureScreenshot("Article Details Page");
	}
	@Then("^Click Show RSC Qty check box and verify listed items with RSC Qty$")
	public void click_Show_RSC_Qty_check_box_and_verify_listed_items_with_RSC_Qty() throws Throwable {
		artical.Verify_RSCQtyCheck();
		 screenshotObj.captureScreenshot("Article Details Page");
	}

@And("^Add a new basket with shipping Method as \"([^\"]*)\"$")
public void add_a_new_basket_with_shipping_Method_as(String shippingMethod) throws Throwable {
	artical.CreateNewBasket(shippingMethod);
	screenshotObj.captureScreenshot("Article Details Page");
}

@Then("^Add a new basket with shipping Method \"([^\"]*)\"$")
public void add_a_new_basket_with_shipping_Method(String shippingMethod) throws Throwable {
	artical.CreateNewBasket(shippingMethod);
	screenshotObj.captureScreenshot("Article Details Page");
}

@And("^Add multiple sku in the basket \"([^\"]*)\"$")
public void add_multiple_sku_in_the_basket(String sku) throws Throwable {
	artical.AddSkusToBasket(sku);
	screenshotObj.captureScreenshot("Article Details Page");
}
@Then("^Add multiple sku in the basket sku \"([^\"]*)\"$")
public void add_multiple_sku_in_the_basket_sku(String sku) throws Throwable {
	artical.AddSkusToBasket(sku);
	screenshotObj.captureScreenshot("Article Details Page");
}
@Then("^Checkout an order \"([^\"]*)\"$") 
public void checkout_an_order(String shipType)throws Throwable
{
	artical.OrderCheckout(shipType);
}
}
