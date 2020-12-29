package stepDefinitions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

	AcenetArticleDeatilsPage artical;


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
		artical.switchToDefault();

	}
	@And("^continue checkout$")
	public void continue_checkout() throws InterruptedException
	{
		artical.switchedToiframe();
		artical.DropshipContinueCheckout();
		screenshotObj.captureScreenshot("Continue Checkout has done");
		artical.switchToDefault();

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
		artical.switchedToiframe();
		artical.selectShippingMethodAndProductQty(shippingMethod,productQty);
		artical.switchToDefault();
		if(shippingMethod.equalsIgnoreCase("RSC Pickup"))
		{
			artical.switchedToiframe();
			artical.expressCheckOutSTRClick();
			artical.OrderCheckout(shippingMethod);
			artical.switchToDefault();
		}
		else if(shippingMethod.equalsIgnoreCase("Stock Reserve"))
		{
			artical.switchedToiframe();
			artical.expressCheckOutSTRClick();			
			artical.OrderCheckout(shippingMethod);
			artical.switchToDefault();
		}
		else if(shippingMethod.equalsIgnoreCase("Ship to Retailer"))
		{
			artical.switchedToiframe();
			artical.expressCheckOutSTRClick();		
			artical.checkOutShipToRetailor();
		}
		else if(shippingMethod.equalsIgnoreCase("Dropship"))
		{
			artical.expressCheckOutSTRClick();
		}
		else{
			log.error("Shipping method not selected");
		}
		artical.switchToDefault();
	}
	@Then("^User enters the shipping method \"([^\"]*)\"$")
	public void User_enters_the_shipping_method(String shippingMethod) throws InterruptedException
	{

		artical.switchedToiframe();
		artical.selectShippingMethod(shippingMethod);
		screenshotObj.captureScreenshot("Shipping method selected");
		artical.switchToDefault();
	}

	@Then("^User select the order qty in product details page \"([^\"]*)\"$")
	public void User_select_the_order_qty_in_product_details_page(String productQty)throws InterruptedException
	{

		artical.switchedToiframe();
		artical.selectProduct(productQty);
		screenshotObj.captureScreenshot("Provided productQty");
		artical.switchToDefault();
	}

	@Then("^Checkout to place an order \"([^\"]*)\"$")
	public void Checkout_to_place_an_order(String shippingMethod)throws InterruptedException
	{
		artical.switchedToiframe();
		artical.Click_ChangeRSCCheckout(shippingMethod);
		screenshotObj.captureScreenshot("Clicking on checkout");
		artical.switchToDefault();
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
		
		screenshotObj.captureScreenshot("Article Details Page");
	}

	@Then("^Click on Hazard badge and verify HAZMAT text$")
	public void click_on_Hazard_badge_and_verify_HAZMAT_text() throws Throwable {
		
		screenshotObj.captureScreenshot("Article Details Page");
	}


	@And("^Add a new basket with shipping Method as \"([^\"]*)\"$")
	public void add_a_new_basket_with_shipping_Method_as(String shippingMethod) throws Throwable {
		artical.CreateNewBasket(shippingMethod);
		screenshotObj.captureScreenshot("Article Details Page");
	}

	@Then("^Add a new basket with shipping Method \"([^\"]*)\"$")
	public void add_a_new_basket_with_shipping_Method(String shippingMethod) throws Throwable {
		artical.switchToiframeRetailAppHost();
		artical.CreateNewBasket(shippingMethod);
		screenshotObj.captureScreenshot("Article Details Page");
		artical.switchToDefault();
	}


	@Then("^Add multiple sku in the basket sku \"([^\"]*)\"$")
	public void add_multiple_sku_in_the_basket_sku(String sku) throws Throwable {

		artical.switchToiframeRetailAppHost();
		artical.AddSkusToBasket(sku);
		screenshotObj.captureScreenshot("Article Details Page");
		artical.switchToDefault();
	}
	@And("^Checkout an order \"([^\"]*)\"$") 
	public void checkout_an_order(String ShipType)throws Throwable
	{

		artical.switchedToiframe();
		artical.OrderCheckout(ShipType);
		screenshotObj.captureScreenshot("Checkout has done");
		artical.switchToDefault();
	}
	@And("^Checkout multiple Skus order \"([^\"]*)\"$")
	public void Checkout_multiple_Skus_order(String shipType)throws Throwable
	{

		artical.MultipleSkuOrderCheckout(shipType);
		screenshotObj.captureScreenshot("Checking out multiple skus");

	}

	@Then("^Remove the selected RSC and select different RSC using recalculate approach \"([^\"]*)\"$")
	public void remove_the_selected_RSC_and_select_different_RSC_using_recalculate_approach(String arg1) throws Throwable {
		artical.switchedToiframe();
		artical.RemoveOldRsc(); 
		artical.SelectDifferentRSC();
		screenshotObj.captureScreenshot("Existing RSC replaced with new value");
		artical.switchToDefault();
	}

	//Need to move to AcenetProductManagementSteps class
	@Then("^Navigate to discovery assortment planner page$")
	public void navigate_to_discovery_assortment_planner_page() throws Throwable {
		artical.NavigateTodiscoveryAssortmentPlannerPage();
		screenshotObj.captureScreenshot("NavigateTo discovery Assortment PlannerPage");
	}
	//Need to move to AcenetProductManagementSteps class
	@Then("^Select By Merchandise Class$")
	public void select_By_Merchandise_Class() throws Throwable {
		artical.switchToiframeRetailAppHost();
		artical.selectMerchandise();		
		screenshotObj.captureScreenshot("Selecedt By Merchandise Class");
		artical.switchToDefault();

	}
	//Need to move to AcenetProductManagementSteps class
	@Then("^Select any Department and MC enter the quantity \"([^\"]*)\"$")
	public void select_any_Department_and_MC_enter_the_quantity(String qty) throws Throwable {
		artical.switchToiframeRetailAppHost();
		artical.discoveryAssortmentpageSelections(qty);
		screenshotObj.captureScreenshot("Discovery Assortment Planner Page selections");
		artical.switchToDefault();
	}

	@Then("^Click addToCart$")
	public void Click_addToCart()
	{
		artical.ClickAddToCart();
		screenshotObj.captureScreenshot("Click on AddToCart");
	}
	//Need to move to AcenetProductManagementSteps class
	@Then("^Verify the added item is present in discovery cart$")
	public void verify_the_added_item_is_present_in_discovery_cart() throws Throwable {
		artical.switchToiframeRetailAppHost();
		artical.VerifyProductInDiscoverCart();
		screenshotObj.captureScreenshot("navigated to Discovery shopping cart");
		artical.switchToDefault();
	}
	@Then("^Verify the added item is present in EventPlanner cart$")
	public void Verify_the_added_item_is_present_in_EventPlanner_cart()
	{
		artical.switchToiframeRetailAppHost();
		artical.VerifyProductInEventPlannerCart();
		screenshotObj.captureScreenshot("navigated to Event planner shopping cart");
		artical.switchToDefault();
	}
	@And("^Click on Discovery shopping cart$")
	public void Click_on_Discovery_shopping_cart()
	{
		artical.switchToiframeRetailAppHost();
		artical.ClickDiscoverCart();
		screenshotObj.captureScreenshot("navigated to Discovery shopping cart");
		artical.switchToDefault();
	}
	@And("^Click on EventPlanner shopping cart$")
	public void Click_on_EventPlanner_shopping_cart()
	{
		artical.switchToiframeRetailAppHost();
		artical.ClickEventPlannerCart();
		screenshotObj.captureScreenshot("navigated to Event planner shopping cart");
		artical.switchToDefault();
	}

	@And("^Select Discovery basket$")
	public void Select_Discovery_basket() throws InterruptedException
	{

		artical.switchToiframeRetailAppHost();
		artical.SelectDiscoveryBasket();
		screenshotObj.captureScreenshot("navigated to Discovery shopping cart");
		artical.switchToDefault();
	}
	@And("^Select EventPlanner basket$")
	public void Select_EventPlanner_basket()
	{
		artical.switchToiframeRetailAppHost();
		artical.SelectEventPlannerBasket();
		screenshotObj.captureScreenshot("navigated to Discovery shopping cart");
		artical.switchToDefault();
	}
	//Need to move to AcenetProductManagementSteps class
	@Then("^Select the added the item and checkout$")
	public void select_the_added_the_item_and_checkout() throws Throwable {
		artical.switchToiframeRetailAppHost();
		artical.DiscoverySelectedSkuToCheckout();
		screenshotObj.captureScreenshot("Check the selected sku  and checkout");
		artical.switchToDefault();
	}
	@Then("^Select the item added and checkout$")
	public void Select_the_item_added_and_checkout()
	{
		artical.switchToiframeRetailAppHost();
		artical.EventPlannerCheckout();
		screenshotObj.captureScreenshot("selected sku  and checkout");
		artical.switchToDefault();
	}
	@Then("^Verify Order submitted$")
	public void Verify_Order_submitted()
	{
		artical.switchToiframeRetailAppHost();
		artical.VerifyOrderSubmitted();
		screenshotObj.captureScreenshot("Checkout verified");
		artical.switchToDefault();
	}
	@Then("^Input PO Number and click Confirm Checkout$")
	public void input_PO_Number_and_click_Confirm_Checkout() throws Throwable {
		screenshotObj.captureScreenshot("Order Confirmation");
	}
	@Then("^Click on manage product tab$")
	public void click_on_manage_product_tab() throws Throwable {
		artical.NavigateTomanageProductTab();
		screenshotObj.captureScreenshot("Navigated to manage product tab");
	}

	@Then("^Select Browse Product and Vendors$")
	public void select_Browse_Product_and_Vendors() throws Throwable {
		artical.SelectManageProduct_SubTabs();
		screenshotObj.captureScreenshot("Navigated to Browse Product and Vendors");
	}

	@Then("^Select Event planner$")
	public void Select_Event_planner() throws Throwable {
		artical.SelectEventPlanner_SubTabs();
		screenshotObj.captureScreenshot("Navigated to Browse Product and Vendors");
	}
	@Then("^Select vendors catagory$")
	public void select_vendors_catagory() throws Throwable {
		artical.switchToiframeRetailAppHost();

		artical.SelectVendorCatagory();
		artical.switchToDefault();
		screenshotObj.captureScreenshot("Select Vendors catagory");
	}
	@Then("^Select Event catagory$")
	public void select_Event_catagory() throws Throwable {
		artical.switchToiframeRetailAppHost();
		artical.SelectEventCatagory();
		artical.switchToDefault();
		screenshotObj.captureScreenshot("Select Vendors catagory");
	}
	@Then("^Apply vendors filter$")
	public void apply_vendors_filter() throws Throwable {
		artical.switchToiframeRetailAppHost();
		artical.AppyVendorsFilter();
		artical.switchToDefault();
		screenshotObj.captureScreenshot("Applied Vendors filters");
	}

	@Then("^Choose the dropship serach in the listed vendor$")
	public void choose_the_dropship_serach_in_the_listed_vendor() throws Throwable {
		artical.switchToiframeRetailAppHost();
		artical.VendorOrderingOption();
		artical.switchToDefault();
		screenshotObj.captureScreenshot("Select Vendors ordering options");
	}

	@Then("^Verify you are landing in to search page$")
	public void verify_you_are_landing_in_to_search_page() throws Throwable {
		artical.switchToiframeRetailAppHost();
		artical.VerifyPage();
		artical.switchToDefault();
		screenshotObj.captureScreenshot("Landed into search page");
	}

	@Then("^Click any sku and verify you are landing into item detail page \"([^\"]*)\"$")
	public void click_any_sku_and_verify_you_are_landing_into_item_detail_page(String sku) throws Throwable {
		artical.switchToiframeRetailAppHost();
		artical.selectVendorSku(sku);
		artical.switchToDefault();
		screenshotObj.captureScreenshot("Select Sku verfy Item deatil page");

	}
	@Then("^provide sku qty \"([^\"]*)\"$")
	public void provide_sku_qty(String skuQty)
	{
		artical.switchToiframeRetailAppHost();
		artical.ProvideSkuQty(skuQty);
		artical.switchToDefault();
		screenshotObj.captureScreenshot("provided Sku qty");

	}
	//***********************
	@Then("^Click createNewBasket button from Cart page$")
	public void click_createNewBasket_button_from_Cart_page() throws Throwable {

		artical.switchToiframeRetailAppHost();
		artical.CreateNewBasket();
		screenshotObj.captureScreenshot("Clicked on createNewBasket from AddToCart page");
		artical.switchToDefault();
	}

	@Then("^create a new basket with all the details \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void create_a_new_basket_with_all_the_details(String BasketName, String ShippingMethod, String CustName, String CustPh, String CustEmail) throws Throwable {
		artical.switchToiframeRetailAppHost();
		artical.CreateNewBasketDetails(BasketName,ShippingMethod,CustName,CustPh,CustEmail);
		screenshotObj.captureScreenshot("createNewBasket with all the fields");
		artical.switchToDefault();

	}

	@And("^provide RSC pickup details \"([^\"]*)\" \"([^\"]*)\"$")
	public void provide_RSC_pickup_details(String rSCPickupName, String rSCPickupDate) throws InterruptedException
	{

		artical.switchedToiframe();
		artical.rscPickupDetails(rSCPickupName,rSCPickupDate);
		screenshotObj.captureScreenshot("RSC pickup details has provided");
		artical.switchToDefault();
	}

	@And("^provide RSC pickup detail \"([^\"]*)\" \"([^\"]*)\"$")
	public void provide_RSC_pickup_detail(String rSCPickupName, String rSCPickupDate) throws InterruptedException
	{
		artical.switchToiframeRetailAppHost();
		//artical.switchedToiframe();
		artical.rscPickupDetails(rSCPickupName,rSCPickupDate);
		screenshotObj.captureScreenshot("RSC pickup details has provided");
		artical.switchToDefault();
	}
	@Then("^Click AddSkuToBasket \"([^\"]*)\"$")
	public void click_AddSkuToBasket(String Sku) throws Throwable {
		artical.switchToiframeRetailAppHost();
		artical.AddSkusToBasket2(Sku);
		screenshotObj.captureScreenshot("Added list of sku in the created basket");
		artical.switchToDefault();
	}

	@Then("^Add multiples sku in the basket$")
	public void add_multiples_sku_in_the_basket() throws Throwable {

	}


}
