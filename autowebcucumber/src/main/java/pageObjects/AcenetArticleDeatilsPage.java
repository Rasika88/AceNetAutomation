package pageObjects;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.appender.db.jdbc.DriverManagerConnectionSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import CommonMethods.AcenetCommonMethods;
import dataProvider.ConfigFileReader;
import selenium.Wait;
import utils.ScreenshotCapture;

public class AcenetArticleDeatilsPage {
	WebDriver driver;
	Logger log=LogManager.getLogger("LogsTest");

	ConfigFileReader configOb =new ConfigFileReader();
	AcenetCommonMethods Cm =new AcenetCommonMethods();

	// Initializing driver
	public AcenetArticleDeatilsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//div[@id='leftRscInfo']")
	WebElement RSCLocation;
	@FindBy(xpath="//span[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_sellingData_lblStoreLocation']")
	WebElement storeLocation;
	@FindBy(xpath = "//input[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_shipMethod_txtQty']")
	WebElement orderQty;
	@FindBy(xpath = "//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_shipMethod_btnAddtoBasket']")
	WebElement AddtoBasket_ItemDetailPage;
	@FindBy(xpath = "//*[@id='rdNewBasket']")
	WebElement newBasket_Option;
	@FindBy(xpath = "//*[@id='txtBasketRefID']")
	WebElement basketName_text;
	@FindBy(xpath = "//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_shipMethod_txtCustomerName']")
	WebElement customerName;
	@FindBy(xpath = "//*[@id='divBasketPopUp']/div[2]/input")
	WebElement addToBasket_button;

	@FindBy(xpath ="//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_shipMethod_divComments']/span")
	WebElement comment;
	@FindBy(xpath = "//*[@id='divErrorInfo']/div[1]/div[1]/div[2]/a/img")
	WebElement popUPcloseBttn;
	@FindBy(xpath = "//*[@id='dd']")
	WebElement dropDown;

	@FindBy(xpath = "//div[@id='dd']")
	WebElement selectShipingMethod;
	@FindBy(xpath = "//a[contains(text(),'Ship to Retailer')]")
	WebElement shipToRetailer;
	@FindBy(xpath = "//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_shipMethod_lstShipMethod']/li[3]/a")
	WebElement shipToCustomer;
	@FindBy(xpath = "//a[contains(text(),'RSC Pickup')]")
	WebElement rSCPickup; 
	@FindBy(xpath = "//a[contains(text(),'Stock Reserve')]")
	WebElement stockReserve_dropdown;
	@FindBy(xpath = "//a[contains(text(),'Dropship')]")
	WebElement dropShip_dropdown;
	@FindBy(xpath = "//a[contains(text(),'Stock Reserve Backorder')]")
	WebElement stockReserveBackorder_dropdown;
	@FindBy(xpath = "//a[contains(text(),'Customer Priority Order')]")
	WebElement customerPriorityorder_dropdown;
	//*[@id="ctl00_ctl00_contentMainPlaceHolder_MainContent_shipMethod_lstShipMethod"]/li[5]/a
	@FindBy(xpath ="//input[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_shipMethod_txtComments']")
	WebElement addComment;
	@FindBy(xpath = "//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_shipMethod_btnExpressCheckout']")	
	WebElement expressCheckout;
	@FindBy(xpath = "//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_sellingData_lblRscQty']")
	WebElement rscQtycheck_ItemDeatilsPage;
	@FindBy(xpath = "//*[contains(text(),'Item Already Exist')]")
	WebElement	ItemAlreadyExist;
	@FindBy(xpath = "//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_shipMethod_txtCustomerName']")
	WebElement customerName_CustPriority;
	@FindBy(xpath = "//input[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_btnCheckout']")
	WebElement checkOut;
	@FindBy(xpath = "//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_sellingData_divRscQty']/span[1]")
	WebElement RSCquantity;
	@FindBy(xpath = "//div[@id='MainContentPlaceHolder_divRscQtyCheckBox']")
	WebElement showRSCcheckBox;
	@FindBy(xpath="//input[@id='btnOrdExpress']")
	WebElement confirmExpressCheckOut;
	@FindBy(xpath="//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_sellingData_lblRscQty']")
	WebElement rscQty;
	@FindBy(xpath="//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_shipMethod_btnExpressCheckout']")
	WebElement click;
	@FindBy(xpath="//*[@id='Description_id']/span")
	WebElement productSpecificationTab;
	@FindBy(xpath="//*[@id='dtlDescriptionList']/tbody/tr[1]/td/li")
	WebElement productSpecificationText;
	@FindBy(xpath="//*[@id='ProdOverview_id']/span")
	WebElement prodOverviewTab;
	@FindBy(xpath="//*[@id='dtlFeatureList']/tbody/tr[1]/td/li)")
	WebElement prodOverviewText;
	@FindBy(xpath="//*[@id='vendorAndDSInfo']")
	WebElement vendorAndDSInfoTab;
	@FindBy(xpath="//*[@id='frmviewVendorInfo']/tbody/tr/td/div[1]/div[1]/span")
	WebElement vendorAndDSInfoText;
	@FindBy(xpath="//*[@id='imgHazmat']/img")
	WebElement imgHazmat;
	@FindBy(xpath="//*[@id='tooltip_IconCnt_3']/div[2]/div[1]")
	WebElement imgHazmatTooltip;
	@FindBy(xpath="//*[@id='MainContentPlaceHolder_divRscQtyCheckBox']")
	WebElement rscQtyCheckBox;
	@FindBy(xpath="//*[@id='MainContentPlaceHolder_lvSearchResults_tbxAddtoBasket_0']")
	WebElement AddQty;
	@FindBy(xpath="//*[@id='MainContentPlaceHolder_lvSearchResults_btnAddtoBasket_0']")
	WebElement AddToBasket_Btn;
	@FindBy(xpath="//*[@id='MainContentPlaceHolder_rBtnCreateBasket']")
	WebElement CreateNewBasket;
	@FindBy(xpath="//*[@id='MainContentPlaceHolder_tbNewBasketName']")
	WebElement BasketName_txt;
	@FindBy(xpath="//*[@id='MainContentPlaceHolder_ddlBasketShippingMethod']")
	WebElement shipingMethod;
	@FindBy(xpath="//*[@id='MainContentPlaceHolder_btnAddToBasket']")
	WebElement AddToBasket_popBtn;
	@FindBy(xpath="//*[@id='MainContentPlaceHolder_btnAlertOKClose']")
	WebElement alertOKClose_popup;
	@FindBy(xpath="/html/body/header/div[1]/div[7]/div/div/div/header/div[2]/div/a/img")
	WebElement addToCart;
	@FindBy(xpath="//*[@id='btnAddSkus']")
	WebElement addSkusBasket;
	@FindBy(xpath="//*[@id='divSku_0']")
	WebElement addSku1;
	@FindBy(xpath="//*[@id='divQty_0']")
	WebElement addQty1;
	@FindBy(xpath="//*[@id='divSku_1']")
	WebElement addSku2;
	@FindBy(xpath="//*[@id='divQty_1']")
	WebElement addQty2;
	@FindBy(xpath="//*[@id='btnClose']")
	WebElement closeBasket_popup;	
	@FindBy(xpath="//*[@id='btnCheckout']")
	WebElement checkout;

	@FindBy(xpath="//*[@id='btnCheckOut']")
	WebElement stockReservedBackorder_checkout;
	@FindBy(xpath="//*[@id='lblExtDesc']")
	WebElement checkoutPageVerification;
	@FindBy(xpath="//*[@id='Qty_55755_021244-024']")
	WebElement eventPlannerSkuSelection;
	@FindBy(xpath="//*[@id='divGridToolbarContent']/div[1]/div/div[4]/span/span")
	WebElement eventPlannerView_Dropdown;
	@FindBy(xpath="//*[@id='btnOrdExpress']")
	WebElement expressCheckout_basket;
	@FindBy(xpath="//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_radGridShoppingBasket_ctl00_ctl02_ctl01_SelectRowSelectCheckBox']")
	WebElement selectAll_checkbox;
	@FindBy(xpath="//*[contains(text(),'Automate8')]")
	WebElement selectBasket;	
	@FindBy(xpath="//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_btnCheckout']")
	WebElement checkout_ShipToRetailer;
	@FindBy(xpath="//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_lblSuccessMsg']")
	WebElement OrderPlacedConfirmation;
	@FindBy(xpath="//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_txtName']")
	WebElement shippingAddressName;
	@FindBy(xpath="//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_txtAddr1']")
	WebElement shippingAddressLine;
	@FindBy(xpath="//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_txtCity']")
	WebElement shippingAddressCity;
	@FindBy(xpath="//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_txtState']")
	WebElement shippingAddressSte;
	@FindBy(xpath="//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_txtZip']")
	WebElement shippingAddressZip;
	@FindBy(xpath="//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_txtPhone']")
	WebElement shippingAddressPhone;
	@FindBy(xpath="//*[@id='divErrorInfoClose']")
	WebElement phone_popup;
	@FindBy(xpath="//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_txtLookUp']")
	WebElement AceRewards;
	@FindBy(xpath="//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_btnValidate']")
	WebElement validate;
	@FindBy(xpath="//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_btnContinue']")
	WebElement continue_Button;

	@FindBy(xpath="//*[@id='txtCustName']")
	WebElement rSCPickupName;
	@FindBy(xpath="//*[@id='txtPickUpPerson']")
	WebElement rSCPickupPickupPerson;
	@FindBy(xpath="//*[@id='txtPickUpDate']")
	WebElement rSCPickupDate;
	@FindBy(xpath="//*[@id='txtPickTime']")
	WebElement rSCPickupTime;
	@FindBy(xpath="//*[@id='btnPickOrder']")
	WebElement rSCPickupOrder;
	@FindBy(xpath="//*[@id='btnExpCheckOut1']")
	WebElement expCheckOut;
	@FindBy(xpath="//*[@id='btnExpCheckOut1']")//*[@id='divConfirmFreight']/div[2]/input[2]")
	WebElement dropship_continueChkout;//*[@id="btnExpCheckOut2"]
	@FindBy(xpath="//*[@id='btnCreateBasket']")
	WebElement createNewBasket_Button;
	@FindBy(xpath="//*[@id='txtBasketRefID']")
	WebElement basketName;
	@FindBy(xpath="//*[@id='ddlShipMethod']")
	WebElement shipMethod;
	@FindBy(xpath="//*[@id='ddlShipMethod']/option[4]")
	WebElement shipMethod_RSCSelection;
	@FindBy(xpath="//*[@id='ddlShipMethod']/option[2]")
	WebElement shipMethod_ShipToRetailer;
	@FindBy(xpath="//*[@id='ddlShipMethod']/option[3]")
	WebElement shipMethod_ShipToCustomer;
	@FindBy(xpath="//*[@id='txtNameBasket']")
	WebElement custName;
	@FindBy(xpath="//*[@id='txtPhoneBasket']")
	WebElement custPh;
	@FindBy(xpath="//*[@id='txtMailBasket']")
	WebElement custEmail;
	@FindBy(xpath="//*[@id='ctl00_ctl00_ctl00_contentMainPlaceHolder_MainContent_MainContent_btnCreateNewBasket']")
	WebElement basketCreate;
	@FindBy(xpath="//*[@id='btnOK']")
	WebElement dropshipOK_Button;
	@FindBy(xpath="//*[@id='divConfirmFreight']/div[2]/input[2]")
	WebElement dropshipContinue;
	@FindBy(xpath="//*[@id='divErrContent']")
	WebElement pastDateCheck;
	@FindBy(xpath="//*[@id='divErrorPopUpClose']")
	WebElement closePopup;
	@FindBy(xpath="//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_otherRSCs']")
	WebElement differentRSCLink;
	@FindBy(xpath="//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_rptCheckout_ctl00_datalstContainer_ctl00_txtOrderQty']")
	WebElement removevalue;
	@FindBy(xpath="//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_btnRecalculate1']")
	WebElement recalculate_btn;
	@FindBy(xpath="//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_rptCheckout_ctl00_datalstContainer_ctl01_txtOrderQty']")
	WebElement newRSCvalue;
	@FindBy(xpath="//*[@id='divSearchPageHeader']/span[1]")
	WebElement verifySearch;
	// Need to move to Product management class
	@FindBy(xpath="(//*[contains(text(),'Manage Product')])[2]")
	WebElement manageProduct_Tab;
	@FindBy(xpath="(//*[contains(text(),'Browse Product & Vendor')])[2]")
	WebElement browseProductVendor_Tab;
	@FindBy(xpath="(//*[contains(text(),'Event Planner')])[2]")
	WebElement EventPlanner_Subtab;
	@FindBy(xpath="//*[@id='contentMainPlaceHolder_quickListPages_lstOther_spanOther_5']/a")
	WebElement vendorCatagorySelection;
	@FindBy(xpath="//*[@id='divEvt']/span[2]/span/span[1]")
	WebElement eventCatogory;
	//@FindBy(xpath="(//span[contains(text(),'PROMO/RETAIL EXEC')])[1]")
	@FindBy(xpath="//*[@id='ddlEvents_listbox']/li[4]")
	WebElement eventCatry;
	@FindBy(xpath="//*[@id='btnGo']")
	WebElement eventPlanner_gobutton;
	@FindBy(xpath="//*[@id='MainContentPlaceHolder_VendorFilterOptions_rdnBtnsVenType_0']")
	WebElement vendorTypeFilter;
	@FindBy(xpath="//*[@id='divCharOption_14']")
	WebElement vendorFilter;//*[@id="divCharOption_14"]
	@FindBy(xpath="//*[contains(text(),'1C - CLEANING SUPPLIES')]")
	WebElement vendorDeptFilter;
	@FindBy(xpath="//*[@id='htmlBtnApplyFilters']")
	WebElement vedorApplyFilter;
	@FindBy(xpath="//*[@id='hyp_SrchDsAceNetDir_68134']")
	WebElement vendorOrderingOption_click;
	@FindBy(xpath="(//*[contains(text(),'1182237')])[2]")
	WebElement select_vendorSku;
	@FindBy(xpath="(//*[contains(text(),'Discovery Assortment Planner')])[2]")
	WebElement discoveryAssortmentPlanner_SubTab;
	@FindBy(xpath="//*[contains(text(),'by Merch Class')]")
	WebElement bymerchClass;
	@FindBy(xpath="//*[@id='contentMainPlaceHolder_DiscoveryMaster_lstDepartment_lnkDepartment_0']")
	WebElement merchDept;	
	@FindBy(xpath="(//*[contains(text(),'100 - Floor Care')])[1]")
	WebElement merchClass;
	@FindBy(xpath="//*[@id='contentMainPlaceHolder_DiscoveryMaster_lstPG_lnkPG_0']")
	WebElement merchPG;
	@FindBy(xpath="//*[@id='btnApplyPONumber']")
	WebElement applyPO;
	@FindBy(xpath="//*[@id='ctl00_ctl00_contentMainPlaceHolder_DiscoveryMaster_DiscoTelerikGrid_telerikDiscoRadGrid_ctl00_ctl04_TXT_OrderQty']")
	WebElement merchOrderQty;
	@FindBy(xpath="//*[@id='ctl00_ctl00_ctl00_contentMainPlaceHolder_MainContent_spnDiscoveryCart']")
	WebElement discoveryCart;
	@FindBy(xpath="//*[@id='ctl00_ctl00_ctl00_contentMainPlaceHolder_MainContent_spnEPCart']")
	WebElement eventPlannerCart;
	@FindBy(xpath="//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_rgShoppingCartDetails_ctl00_ctl06_SOASelectCartSelectCheckBox']")
	WebElement DiscoverySelectedSku_Checkout;
	@FindBy(xpath="//*[@id='contentMainPlaceHolder_MainContent_btnCancel']")
	WebElement Discovery_Checkout_button;
	@FindBy(xpath="//*[@id='ctl00_MainContent_radGridShoppingCart_ctl00']/tbody/tr[2]/td[4]/a")
	WebElement discoveryBasketSelection;
	@FindBy(xpath="//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_btnCheckOut']")
	WebElement eventPlannerCheckut;
	@FindBy(xpath="//*[contains(text(),'PROMO/RETAIL EXEC - OUTDOOR DECOR')]")
	WebElement eventPlannerBasketSelection;
	@FindBy(xpath="//*[@id='MainContent_txtSKUSearch']")
	WebElement DiscoveryTab_SkuSearch;
	@FindBy(xpath="//*[contains(text(),'10051')]")
	WebElement discoverySku;
	@FindBy(xpath="//*[contains(text(),'Your order(s) have been successfully submitted')]")
	WebElement verifyOrderSubmitted;
	@FindBy(xpath="//*[contains(text(),'50118')]")
	WebElement eventPlannerSku;
	@FindBy(id= "tbxSearchBox")
	WebElement searchTextBox;
	@FindBy(xpath="//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_updPnlConfirm']/div[5]/div[5]/table/tbody/tr[2]/td[2]/input")
	WebElement cancel;
	@FindBy(xpath="//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_btnGoToShoppingCart']")
	WebElement goToShoppingCart_Button;
	@FindBy(xpath="(//*[contains(text(),'Automat12')])[1]")
	WebElement CreatedBasket;
	@FindBy(xpath="//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_ddlShipMethod']")
	WebElement SelectShippingMethod_CartPage;
	@FindBy(xpath="//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_ddlShipMethod']/option[2]")
	WebElement SelectShiptoRetailer_CartPageShippingmethod;
	@FindBy(xpath="//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_ddlShipMethod']/option[1]")
	WebElement SelectStockReserve_CartPageShippingmethod;
	@FindBy(xpath="//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_btnDelete']")
	WebElement deleteButton;
	@FindBy(xpath="//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_Submit2']")
	WebElement deleteConfirm_popup;
	public void switchToPDPwindow() throws InterruptedException 
	{
		log.debug("inside PDP");
		selenium.Wait.untilPageLoadComplete(driver);
		String parentWindowHandle= driver.getWindowHandle();
		selenium.Wait.untilPageLoadComplete(driver, (long) 5);
		String expectedTitle = "Pages - Home";
		String actualTitle = driver.getTitle();
		log.info("Title : " + actualTitle);
		if (actualTitle.contentEquals(expectedTitle)) 
		{ 
			log.info("AceNet QA is Logged in");
		}
		else
		{
			log.error("AceNet QA is not Logged in");
		}
		// Store and Print the name of the First window on the console
		String handle= driver.getWindowHandle();
		log.info(handle + "1");
		// Store and Print the name of all the windows open	 

		Set handles = driver.getWindowHandles();
		log.info(handles + "2");
		for (String handle1 : driver.getWindowHandles()) 
		{
			driver.switchTo().window(handle1);
		}
	}


	public void confirmPdPloaded() throws InterruptedException {
		selenium.Wait.untilPageLoadComplete(driver);
		String actualTitle1 = driver.getTitle();
		log.info("Verify artical detail page Title : " + actualTitle1);

	}

	public void switchToChildwindow() throws InterruptedException 
	{
		Set<String> windowhandle=driver.getWindowHandles();
		Iterator<String> iter=windowhandle.iterator();
		String mainWindow=iter.next();
		System.out.println("mainWindow"+ mainWindow);
		String childwindow=iter.next();
		System.out.println("childwindow"+ childwindow);
		driver.switchTo().window(childwindow);
		//driver.close();
		//Thread.sleep(3000);
		//driver.switchTo().window(mainWindow);
	}

	public void switchedToiframe() throws InterruptedException {
		selenium.Wait.untilPageLoadComplete(driver);
		driver.switchTo().frame("iframeItemDetail"); 
		log.info("switched to iframe");


	}
	public void confirmSKUDetails() {
		// TODO Auto-generated method stub

		selenium.Wait.untilPageLoadComplete(driver, (long) 17);
		WebElement storeLocation = driver.findElement(By.xpath("//label[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_headerSkuDataBar_lblSkuNr']"));
		String store = storeLocation.getText();		
		log.info("SKU Details of the article is "+ store);

	}

	public void checkRSCdetails() {

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		String rsc = RSCLocation.getText();
		log.info("RSC details "+ rsc);
	}

	public void checkVendorDetails() {

		WebElement VendorDetails = driver.findElement(By.xpath("//span[contains(text(),'44142 - NUPLA CORP')]"));
		String vendor = VendorDetails.getText();
		log.info("RSC details "+ vendor);

	}

	public void checkProductOverview() {

		WebElement ProductOverview  = driver.findElement(By.xpath("//div[@id='Tab_Content_IFs']"));
		// String productOverview = ProductOverview.getText();
		log.info("RSC details Product Overview displayed");

	}
	public void checkRSCquantityOfArticle() {
		selenium.Wait.untilPageLoadComplete(driver, (long) 10);
		//WebElement rscQty = driver.findElement(By.xpath("//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_sellingData_lblRscQty']"));
		String RSCqty = rscQty.getText();
		log.info("RSC Quantity of the article is "+RSCqty);

	}
	public void checkOmQuantity() {
		// TODO Auto-generated method stub

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebElement omQty = driver.findElement(By.xpath("//span[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_sellingData_spnOmRuIdentifier']"));
		String OMqty = omQty.getText();
		log.info("OM Quantity of the article is "+OMqty);
	}
	public void checkStoreLocation() {
		selenium.Wait.untilPageLoadComplete(driver, (long) 15);
		String store = storeLocation.getText();
		log.info("Store Location of the article is "+ store);
	}

	public void enterOrderQty() {
		log.info("Order qty method");
		setOrderQty();
		orderQty.click();


	}
	public void setOrderQty() {
		orderQty.sendKeys("1");
		log.info("Order qty entered");
	}

	public void ifpopUPcloseBttn()
	{
		selenium.Wait.untilJqueryIsDone(driver, (long) 10);
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		comment.click();
		if(popUPcloseBttn.isDisplayed()) 
		{
			popUPcloseBttn.click();
			log.info("pop up");

		}

	}
	public void selectShipToCustomerOrder() throws InterruptedException {

		log.info("Select Ship to Customer Option");
		dropDown.click();
		shipToRetailer.click();
		selenium.Wait.untilPageLoadComplete(driver);
		//	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//Thread.sleep(5000);


	}
	public void selectShippingMethodAndProductQty(String shippingMethod, String productQty) throws InterruptedException {
		System.out.println("shippingMeth"+shippingMethod);
		if(shippingMethod.equalsIgnoreCase("Ship to Retailer"))
		{
			dropDown.click();
			shipToRetailer.click();
		}
		else if(shippingMethod.equalsIgnoreCase("RSC Pickup"))
		{
			Thread.sleep(3000);
			dropDown.click(); 
			Thread.sleep(3000);
			rSCPickup.click();
		}
		else if(shippingMethod.equalsIgnoreCase("Stock Reserve"))
		{
			Thread.sleep(3000);
			dropDown.click(); 
			Thread.sleep(3000);
			stockReserve_dropdown.click();
		}
		else if(shippingMethod.equalsIgnoreCase("Dropship"))
		{

			Thread.sleep(5000);	
			dropDown.click(); 
			Thread.sleep(3000);
			dropShip_dropdown.click();
		}
		else
		{
			log.error("Shipping method is wrong");
		}

		orderQty.sendKeys(productQty);
	}

	public void selectShippingMethod(String ShippingMethod) throws InterruptedException
	{

		if(ShippingMethod.equalsIgnoreCase("Ship to Retailer"))
		{
			selenium.Wait.explicit(driver,dropDown);
			dropDown.click();
			shipToRetailer.click();
			log.info("Selected shipping method as shipToRetailer");
		}
		else if(ShippingMethod.equalsIgnoreCase("RSC Pickup"))
		{
			selenium.Wait.explicit(driver,dropDown);
			dropDown.click(); 
			selenium.Wait.explicit(driver,rSCPickup);
			rSCPickup.click();
			log.info("Selected shipping method as RSC Pickup");
		}
		else if(ShippingMethod.equalsIgnoreCase("Stock Reserve"))
		{

			selenium.Wait.explicit(driver,dropDown);
			dropDown.click(); 			
			selenium.Wait.explicit(driver,stockReserve_dropdown);
			stockReserve_dropdown.click();
			log.info("Selected shipping method as Stock Reserve");
		}
		else if(ShippingMethod.equalsIgnoreCase("Ship to Customer"))
		{

			selenium.Wait.explicit(driver,dropDown);
			dropDown.click(); 
			selenium.Wait.explicit(driver,shipToCustomer);
			shipToCustomer.click();
			log.info("Selected shipping method as Ship to Customer");
		}
		else if(ShippingMethod.equalsIgnoreCase("Dropship"))
		{

			selenium.Wait.explicit(driver,dropDown);
			dropDown.click(); 
			selenium.Wait.explicit(driver,dropShip_dropdown);
			dropShip_dropdown.click();
			log.info("Selected shipping method as Dropship");
		}
		else if(ShippingMethod.equalsIgnoreCase("Stock Reserve Backorder"))
		{

			selenium.Wait.explicit(driver,dropDown);
			dropDown.click(); 
			selenium.Wait.explicit(driver,dropShip_dropdown);
			stockReserveBackorder_dropdown.click();
			log.info("Selected shipping method as Stock Reserve Backorder");
		}
		else if(ShippingMethod.equalsIgnoreCase("Customer Priority Order"))
		{
			Thread.sleep(7000);
			selenium.Wait.explicit(driver,dropDown);
			dropDown.click(); 
			selenium.Wait.explicit(driver,customerPriorityorder_dropdown);
			customerPriorityorder_dropdown.click();
			log.info("Selected shipping method as Customer Priority Order");
		}
		else
		{//*[@id="ctl00_ctl00_contentMainPlaceHolder_MainContent_shipMethod_lstShipMethod"]/li[6]/a/text()
			log.error("Please check the Shipping");
		}


	}
	public void selectProduct(String productQty)
	{
		orderQty.sendKeys(productQty);
		log.info("Given product Qty");
	}
	public void clickAddToBasket()
	{
		selenium.Wait.explicit(driver, AddtoBasket_ItemDetailPage);
		AddtoBasket_ItemDetailPage.click();
		log.info("AddtoBasket button clicked");
	}


	public void enterOrderQtyShipToRetailer() {
		log.info("Order qty method");
		setOrderQtyShipToRetailer();
		orderQty.click();

	}
	public void setOrderQtyShipToRetailer() {
		log.info("set order qty method");
		orderQty.sendKeys("1");

	}
	public void createNewBasketItemDetailPage(String BasketName) 	
	{
		newBasket_Option.click();
		basketName_text.sendKeys(BasketName);
		addToBasket_button.click();
		//driver.close();
		//switchToDefault();
		log.info("Basket created from Item detail page");
	}
	public void EnterCustomerName_ItemDetailPage(String CustomerName)
	{

		customerName.sendKeys(CustomerName);
		log.info("Customer name has entered");
	}
	public void expressCheckOutSTRClick() {


		addComment.sendKeys("Place Order");
		expressCheckout.click();
		log.info("clicked Express check out");
		selenium.Wait.untilJqueryIsDone(driver, (long) 10);


	}

	public void checkOutShipToRetailor() {

		selenium.Wait.untilJqueryIsDone(driver, (long) 20);		
		log.info("Checkout Ship To Retailor");
		checkOut.click();


	}
	public void switchToiframeRetailAppHost() {

		driver.switchTo().frame("iframeRetailAppHost"); 
		log.info("switched to iframe");
	}
	public void switchToiframedscIframe1() {

		driver.switchTo().frame("dscIframe1"); 
		log.info("switched to iframe");
	}

	public void switchToDefault()
	{
		driver.switchTo().parentFrame();
	}
	public void switchToiframeIfrmQuickEntry() {

		driver.switchTo().frame("ctl00_ctl00_contentMainPlaceHolder_MainContent_ifrmQuickEntry"); 
		log.info("switched to iframe");
	}


	public void clickSelectRSCQuantityCheckbox() throws InterruptedException {

		selenium.Wait.untilPageLoadComplete(driver);
		showRSCcheckBox.click();
		selenium.Wait.untilPageLoadComplete(driver);


	}
	public void confirmRSCquantity() {
		selenium.Wait.untilJqueryIsDone(driver, (long) 10);
		click.click();
		System.out.println("************************"+RSCquantity.getSize());
		String rscQty=RSCquantity.getText();
		System.out.println("Tesing "+rscQty);
		/*if(rscQty.isBlank())
		{
			log.error("RSC quatity is balnk");
		}
		else
		{
			log.info("RSC quatity is displayed");
		}*/
		//RSCquantity.isDisplayed();
		//log.info("RSC quantity displayed");
	}
	public void expressCheckOutClick() throws InterruptedException
	{

		log.info("Express check out method");
		addComment.sendKeys("Place Order");
		expressCheckout.click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		log.info("Express check button click"); Thread.sleep(1000);
		if(confirmExpressCheckOut.isEnabled())
		{
			confirmExpressCheckOut.click();
		}
		else
		{
			log.error("confirm express checkout is not enabled");
		}
		log.info("Express check out confirmation");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	public void switchedToiframe2() throws InterruptedException {
		selenium.Wait.untilPageLoadComplete(driver);
		driver.switchTo().frame("iframeid"); 
		log.info("switched to iframe");


	}


	public void VerifyProductdetailsTabs() throws InterruptedException
	{
		selenium.Wait.untilPageLoadComplete(driver);
		productSpecificationTab.click();
		prodOverviewTab.click();
		vendorAndDSInfoTab.click();
		imgHazmat.click();
		String toolTip=imgHazmatTooltip.getText();
		if(toolTip.contains("HAZMAT Item"))
			log.info("HAZMAT Item text verified");
		else
			log.error("Tool tip verification failed");





	}


	public void Verify_RSCQtyCheck() {

		switchToiframeRetailAppHost();
		WebDriverWait wait = new WebDriverWait(driver,55);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='MainContentPlaceHolder_divRscQtyCheckBox']")));
		driver.findElement(By.xpath("//*[@id='MainContentPlaceHolder_divRscQtyCheckBox']")).click();
		//selenium.Wait.untilPageLoadComplete(driver, (long) 17);
		/*Boolean CheckboxSelection=rscQtyCheckBox.isEnabled();
		System.out.println(CheckboxSelection);
		if(CheckboxSelection.equals(true))
		{
			rscQtyCheckBox.click();
			log.debug("RSC qty checkbox is Unchecked");}
		else {
			log.debug("RSC qty checkbox is checked");
		}
		 */

		selenium.Wait.untilPageLoadComplete(driver);
		List<WebElement> elements = driver.findElements(By.xpath("//*[contains(text(),'RSC')]"));
		int elementsize=elements.size();
		System.out.println("elementsize "+elementsize);
		if(elementsize>0)
		{
			log.info("list of products available with Show RSC Qty available");
		}
		else 
		{
			log.error("list of products available without Show RSC Qty");
		}

		driver.switchTo().defaultContent();
	}
	public void CreateNewBasket(String shippingMethod) throws InterruptedException {
		String basketName="Automate8";	
		AddQty.sendKeys("1");
		selenium.Wait.explicit(driver,AddToBasket_Btn);
		AddToBasket_Btn.click();
		log.info("Click on add basket");
		log.info("Create New basket popup is opened");
		selenium.Wait.untilPageLoadComplete(driver);
		CreateNewBasket.click();
		BasketName_txt.sendKeys(basketName);
		log.info("Enter basket name");
		Select sel=new Select(shipingMethod);
		sel.selectByVisibleText(shippingMethod);		
		AddToBasket_popBtn.click();
		log.info("New basket has been created");
		selenium.Wait.explicit(driver,alertOKClose_popup);
		//WebDriverWait wait = new WebDriverWait(driver,55);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='MainContentPlaceHolder_btnAlertOKClose']")));
		log.info("Click OK to close popup ");
		alertOKClose_popup.click();


	}


	/*public void AddTocart() throws InterruptedException {
		Thread.sleep(3000);		
		searchTextBox.sendKeys("Test");
		WebDriverWait wait = new WebDriverWait(driver,55);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/header/div[1]/div[7]/div/div/div/header/div[2]/div/a/img")));
		addToCart.click();
		log.info("Clicked on Add to cart");
	}*/
	public void AddSkusToBasket(String sku) throws InterruptedException
	{
		selenium.Wait.untilPageLoadComplete(driver, (long) 20);
		//switchToiframeRetailAppHost();
		selenium.Wait.explicit(driver,selectBasket);
		selectBasket.click();
		log.info("Select the basket which is created");

		selenium.Wait.explicit(driver,addSkusBasket);
		addSkusBasket.click();
		log.info("Click on add sku's to basket to add the list of sku's");

		switchToiframeIfrmQuickEntry();
		log.info("Sku's are ready to add into the basket");
		addSku1.sendKeys(sku);		
		addQty1.sendKeys("1");

		//addSku2.sendKeys("20267");
		//addQty2.sendKeys("1");

		addSkusBasket.click();
		selenium.Wait.explicit(driver,closeBasket_popup);
		closeBasket_popup.click();
		log.info("Sku's added into the basket and close the popup");
		driver.switchTo().defaultContent();
		switchToiframeRetailAppHost();
		selenium.Wait.explicit(driver,checkout);
		checkout.click();


	}



	public void OrderCheckout(String shipType) throws InterruptedException {
		//using it for PlaceDropshipOrder,PlaceOrderWithShiptoCustomer
		System.out.println(shipType);
		if(shipType.equalsIgnoreCase("Stock Reserve"))
		{	
			selenium.Wait.explicit(driver, expressCheckout);
			expressCheckout.click();
			log.info("Express checkout has done PDP");
			selenium.Wait.explicit(driver, expressCheckout_basket);
			expressCheckout_basket.click();		

			log.info("Express checkout has done");
		}
		else if(shipType.contains("Ship to Retailer"))
		{
			selenium.Wait.explicit(driver, expressCheckout);
			expressCheckout.click();
			selenium.Wait.explicit(driver, checkout_ShipToRetailer);
			checkout_ShipToRetailer.click();
			log.info("Express checkout has done for Ship To Retailer");
			/*if(OrderPlacedConfirmation.equals(" Your Order was successfully submitted. "))
			{
				log.info("Order has placed successfully");
			}
			log.error("Order has not placed successfully");*/
		}
		else if(shipType.contains("Ship to Customer"))
		{
			selenium.Wait.explicit(driver, expressCheckout);
			expressCheckout.click();
		}
		else if(shipType.contains("RSC Pickup"))
		{
			selenium.Wait.explicit(driver, expressCheckout);
			expressCheckout.click();
			log.info("Click on Checkout button");
			/*log.info("providing RSC pickup deatils");
			rSCPickupName.sendKeys("John");
			rSCPickupPickupPerson.sendKeys("John");
			rSCPickupDate.sendKeys("12/17/2020");
			rSCPickupTime.sendKeys("12");
			rSCPickupName.click();
			Thread.sleep(2000);
			rSCPickupOrder.click();
			log.info("provided RSC pickup deatils successfully");

			if(OrderPlacedConfirmation.equals(" Your Order was successfully submitted. "))
			{
				log.info("Order has placed successfully");
			}
			log.error("Order has not placed successfully");*/
		}
		else if(shipType.equalsIgnoreCase("DropShip"))
		{
			selenium.Wait.explicit(driver, expressCheckout);
			expressCheckout.click();
			log.info("Clicked on express checkout process");
		}

		else if(shipType.equalsIgnoreCase("Stock Reserve Backorder"))
		{
			selenium.Wait.explicit(driver, expressCheckout);
			expressCheckout.click();
			log.info("Clicked on express checkout process");
			selenium.Wait.explicit(driver, stockReservedBackorder_checkout);

			stockReservedBackorder_checkout.click();
			log.info("Clicked on checkout process");


		}
		else if(shipType.equalsIgnoreCase("Customer Priority Order"))
		{

			String rscQty=rscQtycheck_ItemDeatilsPage.getText();
			System.out.println("RSC quatity"+rscQty);
			log.debug("RSC quatity"+rscQty);

			selenium.Wait.explicit(driver, expressCheckout);
			customerName_CustPriority.sendKeys("John");
			expressCheckout.click();
			log.info("Clicked on express checkout process");
			if(rscQty.equalsIgnoreCase("0"))
			{
				log.error("The order quantity entered is greater than the available RSC quantity.  Please modify your order quantity.");
			}
			else
			{
				log.info("Order has been submitted");
			}
		}
		else
		{
			log.info("Cross-Dock Pick Up shippemnt checkout process");
			checkout.click();
			rSCPickupName.sendKeys("John");
			rSCPickupPickupPerson.sendKeys("John");
			rSCPickupDate.sendKeys("12/17/2020");
			rSCPickupTime.sendKeys("12");
			rSCPickupName.click();
			Thread.sleep(2000);
			rSCPickupOrder.click();
			log.info("Cross-Dock Pick Up shippemnt checkout process completed");

			if(OrderPlacedConfirmation.equals(" Your Order was successfully submitted. "))
			{
				log.info("Order has placed successfully");
			}
			log.error("Order has not placed successfully");
		}
	}
	public void DropshipContinueCheckout()
	{
		dropship_continueChkout.click();
		log.info("Clicked on continue checkout process");
		dropshipContinue.click();
		log.info("Clicked on continue in the popup");
		dropshipOK_Button.click();
		log.info("Order has been placed");
	}
	public void shipToCustomerinfo(String addressName, String addressLine, String city, String state, String Zip, String phNumber)
	{
		log.info("Enter required info for Ship to Customer");
		shippingAddressName.sendKeys(addressName);
		shippingAddressLine.sendKeys(addressLine);
		shippingAddressCity.sendKeys(city);
		shippingAddressSte.sendKeys(state);
		shippingAddressZip.sendKeys(Zip);
		JavascriptExecutor jse = (JavascriptExecutor)driver;			
		jse.executeScript("document.getElementById('ctl00_ctl00_contentMainPlaceHolder_MainContent_txtPhone').value='1234567654'");
		log.info("All required info has provided for Ship to Customer");			
		validate.click();
		continue_Button.click();
		checkout_ShipToRetailer.click();
		if(OrderPlacedConfirmation.equals(" Your Order was successfully submitted. "))

			log.info("Order has placed successfully");
		else
			log.error("Order has not placed successfully");
	}
	public void MultipleSkuOrderCheckout(String shipType) throws InterruptedException 
	{
		System.out.println("shipType"+ shipType);
		if(shipType.contains("Stock Reserve"))
		{	
			switchToiframeRetailAppHost();
			expressCheckout_basket.click();
			log.info("Express checkout has done PDP");			
			switchToDefault();
		}
		else if(shipType.contains("Ship to Retailer"))
		{
			switchToiframeRetailAppHost();
			log.info("enter into an expected methods");
			checkout_ShipToRetailer.click();

			log.info("Express checkout has done for Ship To Retailer");
			if(OrderPlacedConfirmation.equals(" Your Order was successfully submitted. "))
			{
				log.info("Order has placed successfully");
			}
			else
			{
				log.error("Order has not placed successfully");
			}
			switchToDefault();
		}
		else if(shipType.contains("Ship to Customer"))
		{
			switchToiframeRetailAppHost();
			log.info("Enter required info for Ship to Customer");
			//AceRewards.sendKeys("3126752312");
			shippingAddressName.sendKeys("John");
			shippingAddressLine.sendKeys("1211 s Quebec");
			shippingAddressCity.sendKeys("Denver");
			shippingAddressSte.sendKeys("CO");
			shippingAddressZip.sendKeys("80231"); 
			Thread.sleep(3000);
			shippingAddressPhone.sendKeys("3126752312");
			selenium.Wait.explicit(driver, validate);
			validate.click();
			selenium.Wait.explicit(driver, continue_Button);
			continue_Button.click();
			selenium.Wait.explicit(driver, recalculate_btn);
			recalculate_btn.click();
			selenium.Wait.explicit(driver, checkout_ShipToRetailer);
			checkout_ShipToRetailer.click();

			if(OrderPlacedConfirmation.equals(" Your Order was successfully submitted. "))
			{
				log.info("Order has placed successfully");}
			else
			{
				log.error("Order has not placed successfully");
			}
			switchToDefault();
		}
		else if(shipType.contains("RSC Pick Up"))
		{
			//expressCheckout.click();
			switchToiframeRetailAppHost();
			log.info("providing RSC pickup deatils");
			rSCPickupName.sendKeys("John");
			rSCPickupPickupPerson.sendKeys("John");
			rSCPickupDate.sendKeys("12/17/2020");
			rSCPickupTime.sendKeys("12");
			rSCPickupName.click();
			Thread.sleep(2000);
			rSCPickupOrder.click();
			log.info("provided RSC pickup details successfully");

			if(OrderPlacedConfirmation.equals(" Your Order was successfully submitted. "))
			{
				log.info("Order has placed successfully");
			}
			else
			{
				log.error("Order has not placed successfully");
			}
			switchToDefault();
		}
		else if(shipType.equalsIgnoreCase("DropShip"))
		{

		}
		else if(shipType.equalsIgnoreCase("Cross-Dock Pick Up"))
		{
			switchToiframeRetailAppHost();
			log.info("Cross-Dock Pick Up shippement checkout process");
			//checkout.click();
			rSCPickupName.sendKeys("John");
			rSCPickupPickupPerson.sendKeys("John");
			rSCPickupDate.sendKeys("12/17/2020");
			rSCPickupTime.sendKeys("12");
			rSCPickupName.click();
			Thread.sleep(2000);
			rSCPickupOrder.click();
			log.info("Cross-Dock Pick Up shippement checkout process completed");

			if(OrderPlacedConfirmation.equals(" Your Order was successfully submitted. "))
			{
				log.info("Order has placed successfully");
			}
			else
			{
				log.error("Order has not placed successfully");
			}
			switchToDefault();
		}
		else
		{

		}
	}

	public void Click_ChangeRSCCheckout(String shippingMethod)
	{
		selenium.Wait.explicit(driver,expressCheckout);
		expressCheckout.click();
		log.info("Clicked on express checkout");

	}
	public void SelectDifferentRSC()
	{
		selenium.Wait.explicit(driver,newRSCvalue);
		newRSCvalue.click();
		newRSCvalue.sendKeys("2");
		log.info("New RSC value entered");
		selenium.Wait.explicit(driver,recalculate_btn);
		recalculate_btn.click();
		log.info("Clicked on Recalculate button");
		selenium.Wait.explicit(driver,checkout_ShipToRetailer);
		checkout_ShipToRetailer.click();
		log.info("Clicked on checkout button");
	}
	public void RemoveOldRsc()
	{
		selenium.Wait.explicit(driver,differentRSCLink);

		differentRSCLink.click();
		log.info("Different RSC link is clicked");
		removevalue.clear();
		log.info("Removed existing RSC value");
		selenium.Wait.explicit(driver,recalculate_btn);
		recalculate_btn.click();
		log.info("Clicked on Recalculate button");
	}

	//This should be move to product management class 
	public void NavigateTomanageProductTab() throws InterruptedException {

		selenium.Wait.explicit(driver,manageProduct_Tab);
		manageProduct_Tab.click();
		log.info("Navigated to Management Product tab");

	}
	//This should be move to product management class 
	public void NavigateTodiscoveryAssortmentPlannerPage() {


		selenium.Wait.explicit(driver,discoveryAssortmentPlanner_SubTab);
		discoveryAssortmentPlanner_SubTab.click();
		log.info("Navigated to discovery Assortment Planner page");

	}
	//This should be move to product management class 

	public void discoveryAssortmentpageSelections(String orderQty) throws InterruptedException {


		selenium.Wait.explicit(driver,merchDept);
		merchDept.click();	
		log.info("Merchandise dept selected");

		selenium.Wait.explicit(driver,merchClass);
		merchClass.click();
		log.info("Merchandise class selected");

		selenium.Wait.explicit(driver,merchPG);
		merchPG.click();
		log.info("Merchandise PG selected ");
		selenium.Wait.untilPageLoadComplete(driver);
		//selenium.Wait.explicit(driver,merchOrderQty);
		merchOrderQty.clear(); Thread.sleep(3000);
		merchOrderQty.sendKeys(orderQty);
		String sku="10051";	
		log.info("Order Qty provided ");

	}
	//This should be move to product management class
	public void selectMerchandise() {
		//switchToiframeRetailAppHost();
		selenium.Wait.explicit(driver,bymerchClass);
		//WebDriverWait wait = new WebDriverWait(driver,45);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='form1']/div[3]/div[1]/div[3]/div[3]/div[2]/div[5]/a")));
		bymerchClass.click();
		log.info("Selected Merchandise option");

	}
	//This should be move to product management class
	public void VerifyProductInDiscoverCart() {
		String sku="10051";
		String discoverSku= discoverySku.getText();
		if(discoverSku.equalsIgnoreCase(sku))
		{
			log.debug("Placed an item has been displayed in the discovery basket");
		}
		else
		{
			log.debug("Placed an item has not displayed in the discovery basket");
		}
	}
	public void VerifyProductInEventPlannerCart() {
		String sku="50118";
		String eventplannerSku= eventPlannerSku.getText();
		if(eventPlannerSku.equals(sku))
		{
			log.debug("Placed an item has been displayed in the Event planner basket");
		}
		else
		{
			log.debug("Placed an item has not displayed in the Event planner basket");
		}
	}
	public void SelectDiscoveryBasket() throws InterruptedException
	{
		switchToiframedscIframe1();

		selenium.Wait.explicit(driver,discoveryBasketSelection);
		discoveryBasketSelection.click();
		log.info("Discovery basket has selected");
		switchToDefault();
	}
	public void SelectEventPlannerBasket()
	{
		//switchToiframedscIframe1();
		selenium.Wait.explicit(driver,eventPlannerBasketSelection);
		eventPlannerBasketSelection.click();
		log.info("Event planner basket has selected");
		//switchToDefault();
	}

	public void SelectManageProduct_SubTabs() {
		selenium.Wait.explicit(driver,browseProductVendor_Tab);
		browseProductVendor_Tab.click();
		log.info("Manage product sub tab selected");
	}
	public void SelectEventPlanner_SubTabs()
	{
		selenium.Wait.explicit(driver,EventPlanner_Subtab);
		EventPlanner_Subtab.click();
		log.info("Manage produc sub tab selected");
	}

	public void SelectVendorCatagory() {
		selenium.Wait.explicit(driver,vendorCatagorySelection);
		vendorCatagorySelection.click();
		log.info("Vendor catagory selected");

	}
	public void SelectEventCatagory()
	{
		selenium.Wait.explicit(driver,eventCatogory);
		//selenium.Wait.explicit(driver,eventCatry);
		//eventCatry.sendKeys("PROMO/RETAIL EXEC");
		eventCatry.click();
		log.info("Event catagory selected");
		selenium.Wait.explicit(driver,eventPlanner_gobutton);
		eventPlanner_gobutton.click();
	}
	public void AppyVendorsFilter() {

		selenium.Wait.explicit(driver,vendorFilter);
		vendorFilter.click();
		selenium.Wait.explicit(driver,vendorDeptFilter);
		vendorDeptFilter.click();
		selenium.Wait.explicit(driver,vedorApplyFilter);
		vedorApplyFilter.click();
		log.info("Vendor fileters selected");

	}

	public void VendorOrderingOption() {
		selenium.Wait.explicit(driver,vendorOrderingOption_click);
		vendorOrderingOption_click.click();


	}

	public void VerifyPage() {

		WebDriverWait wait = new WebDriverWait(driver,45);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='divSearchPageHeader']/span[1]")));
		String expectedPage=verifySearch.getText();
		log.info("Expected page "+expectedPage);
		if(expectedPage.equalsIgnoreCase("Showing Results for"))
		{
			log.info("Expected page");
		}
		else{
			log.info("landed into wrong page");
		}

	}

	public void selectVendorSku(String sku) {

		if(sku.equalsIgnoreCase("1182237"))
		{
			log.info("Expected sku is displayed in the search page "+sku);
		}
		log.info("Expected sku is not in the search page "+sku);

		select_vendorSku.click();

	}

	public void ClickAddToCart() {

		selenium.Wait.untilPageLoadComplete(driver);
		searchTextBox.sendKeys(".");
		selenium.Wait.explicit(driver,addToCart);
		addToCart.click();
		log.info("Add to cart clicked");

	}

	public void ClickDiscoverCart() {
		selenium.Wait.explicit(driver,discoveryCart);

		discoveryCart.click();
		log.info("Discovery cart is selected");

	}
	public void ClickEventPlannerCart()
	{
		selenium.Wait.explicit(driver,eventPlannerCart);
		eventPlannerCart.click();
		log.info("event Planner cart is selected");	}

	public void DiscoverySelectedSkuToCheckout() {
		DiscoverySelectedSku_Checkout.click();
		log.info("Discovery Sku has selected for checkout");
		Discovery_Checkout_button.click();
		log.info("Discovery Sku has selected and Checkout also done");
	}

	public void EventPlannerCheckout()
	{
		eventPlannerCheckut.click();
		log.info("Event planner checkout done");
	}
	public void VerifyOrderSubmitted()
	{
		selenium.Wait.untilPageLoadComplete(driver);
		System.out.println(""+verifyOrderSubmitted.getText());
		log.info("Order submmited and verified");

	}
	public void CreateNewBasket() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,55);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='btnCreateBasket']")));
		createNewBasket_Button.click();
		log.info("create New Basket Button is clicked");
	}
	public void ClickAddSkusBasket(String Sku)
	{
		selenium.Wait.explicit(driver, addSkusBasket);
		addSkusBasket.click();
		log.info("Clicked on add sku's to basket to add the list of sku's");	
		switchToiframeIfrmQuickEntry();
		log.info("Sku's are ready to add into the basket");
		addSku1.sendKeys(Sku);		
		addQty1.sendKeys("1");
		//addSku2.sendKeys("20267");
		//addQty2.sendKeys("1");		
		addSkusBasket.click();
		String expectedText= ItemAlreadyExist.getText();
		if(expectedText.equalsIgnoreCase("Item Already Exist"))
		{
			log.info("Item Already Exist");
		}
		else
		{
			log.error("Item Already Exist is not present");
		}
	}
	public void CreateNewBasketDetails(String basketName2, String shippingMethod, String custName2, String custPh2, String custEmail2) throws InterruptedException {

		log.info("Enter all the values to create new basket");
		basketName.sendKeys(basketName2);
		System.out.println("shippingMethod"+shippingMethod);
		shipMethod.click(); Thread.sleep(3000);
		if(shippingMethod.equalsIgnoreCase("RSC Pick Up"))
		{
			shipMethod_RSCSelection.click();
			log.info("Selected shipment type as RSC Pickup ");
		}
		else if(shippingMethod.equalsIgnoreCase("Ship to Retailer"))
		{
			shipMethod_ShipToRetailer.click();
			log.info("Selected shipment type as Ship to Retailer");
		}
		else if(shippingMethod.equalsIgnoreCase("Ship to Customer"))
		{
			shipMethod_ShipToCustomer.click();
			log.info("Selected shipment type as Ship to Customer");
		}
		custName.sendKeys(custName2);
		custPh.sendKeys(custPh2);
		custEmail.sendKeys(custEmail2);
		basketCreate.click();
		log.info("New basket has been created with "+basketName2);
	}

	public void AddSkusToBasket2(String Sku) throws InterruptedException {
		System.out.println("Sku"+Sku);
		selenium.Wait.explicit(driver, addSkusBasket);
		addSkusBasket.click();
		log.info("Click on add sku's to basket to add the list of sku's");	
		switchToiframeIfrmQuickEntry();
		log.info("Sku's are ready to add into the basket");
		addSku1.sendKeys(Sku);		
		addQty1.sendKeys("1");
		//addSku2.sendKeys("20267");
		//addQty2.sendKeys("1");		
		addSkusBasket.click();
		closeBasket_popup.click(); 
		Thread.sleep(3000);
		log.info("Sku's added into the basket and close the popup");
		/*driver.switchTo().defaultContent();
		switchToiframeRetailAppHost();
		selenium.Wait.explicit(driver,checkout);
		checkout.click();
		log.info("Click on Checkout button after added Sku");*/
	}

	public void CheckoutOnceSkusAdded()
	{
		//driver.switchTo().defaultContent();
		//switchToiframeRetailAppHost();
		selenium.Wait.explicit(driver,checkout);
		checkout.click();
		log.info("Click on Checkout button after added Sku");
	}

	public void ProvideSkuQty(String skuQty) {
		// TODO Auto-generated method stub
		System.out.println("skuQty"+ skuQty);
		eventPlannerSkuSelection.sendKeys(skuQty);
		log.info("Sku's qty provided"); 
		selenium.Wait.explicit(driver,eventPlannerView_Dropdown);
		eventPlannerView_Dropdown.click();
		//*[@id="imgShowHideNavFilters"]
		/*for (int i = 001; i < 007; i++) 
		{
			System.out.println("i"+ i);			
			WebElement qtySize=driver.findElement(By.xpath("//*[@id='Qty_50118_021244-003']"));
			System.out.println("qtySize"+qtySize);

			if(qtySize==null)
			{
				driver.findElement(By.xpath("//*[@id='Qty_50118_021244-["+(i)+"]']")).sendKeys("3");
			}
			else
			{
			driver.findElement(By.xpath("//*[@id='Qty_50118_021244-["+(i+1)+"]']"));

		}

		}*/
	}

	public void rscPickupDetails(String rSCPickupName2, String rSCPickupDate2) throws InterruptedException {
		// TODO Auto-generated method stub
		log.info("providing RSC pickup details");
		rSCPickupName.sendKeys(rSCPickupName2);
		rSCPickupPickupPerson.sendKeys("John");
		rSCPickupDate.sendKeys(rSCPickupDate2);
		rSCPickupTime.sendKeys("12");
		rSCPickupName.click();
		Thread.sleep(2000);
		rSCPickupOrder.click();
		log.info("provided RSC pickup deatils successfully");
		if(OrderPlacedConfirmation.equals(" Your Order was successfully submitted. "))
		{
			log.info("Order has placed successfully");
		}
		log.error("Order has not placed successfully");
	}

	public void ClickOnGoToShoppingCart() throws InterruptedException {


		Thread.sleep(5000);
		String button=goToShoppingCart_Button.getText();
		goToShoppingCart_Button.isEnabled();
		System.out.println("button"+ button);
		goToShoppingCart_Button.click();
		log.info("GoToShoppingCart  Button is clicked");
		CreatedBasket.getText();
	}

	public void VerifyShipingMethod(String changeShippingMethod) {
		// TODO Auto-generated method stub
		String beforeXpath="//*[contains(@class,'rgMasterTable')]/tbody/tr[";
		String afterXpath="]/td[2]";		
		String createdBasketName="Automate12";
		for(int i=2;i<5;i++)
		{
			String actualXpath=beforeXpath+i+afterXpath;
			WebElement element=driver.findElement(By.xpath(actualXpath));			
			if(element.getText().contains(createdBasketName))
			{
				log.info("Created basket is present");	
				String ActualShippingMethod= driver.findElement(By.xpath("//*[contains(@class,'rgMasterTable')]/tbody/tr["+i+"]/td[6]")).getText();			
				System.out.println("ActualSkuCount "+ActualShippingMethod);
				if(ActualShippingMethod.equalsIgnoreCase(changeShippingMethod))
				{
					log.info("ShippingMethod in the basket is verified");
				}
				else
				{
					log.error("ShippingMethod in the basket is not show as expected");
				}

				break;
			}
			else
			{

				log.error("Created basket is not present");
			}
		}

		selenium.Wait.explicit(driver,CreatedBasket);
		driver.getTitle();
		log.info("Shipping method is verified");
	}

	public void SelectedBasketFromshopingCart(String basketName2) {
		// TODO Auto-generated method stub

		selenium.Wait.explicit(driver,CreatedBasket);
		String createdBasket=CreatedBasket.getText();
		if(basketName2.equalsIgnoreCase(createdBasket))
		CreatedBasket.click();
		log.info("Selected Basket From shopingCart");
	}
	public void DeleteSku()
	{
		selenium.Wait.explicit(driver, deleteButton);
		deleteButton.click();
		log.info("Clicked on delete button");
		selenium.Wait.explicit(driver, deleteConfirm_popup);
		deleteConfirm_popup.click();
		log.info("Clicked on delete confirmation ok button");
		
	}
	
	public void ChangeShippingMethod(String changeShippingMethod) {
		selenium.Wait.explicit(driver, SelectShippingMethod_CartPage);
		SelectShippingMethod_CartPage.click();

		if(changeShippingMethod.equalsIgnoreCase("Ship to Retailer"))
		{
			selenium.Wait.explicit(driver, SelectShiptoRetailer_CartPageShippingmethod);
			SelectShiptoRetailer_CartPageShippingmethod.click();
			log.info("Changed the shipping method" +changeShippingMethod);
		}
		else if(changeShippingMethod.equalsIgnoreCase("Stock Reserve"))
		{	
			selenium.Wait.explicit(driver, SelectStockReserve_CartPageShippingmethod);
			SelectStockReserve_CartPageShippingmethod.click();
			log.info("Changed the shipping method" +changeShippingMethod);
		}
		else
		{
			log.error("correct Shipping method not selected");
		}

	}


	public void switchTomainwindow() throws InterruptedException 
	{
		driver.close();
		Thread.sleep(3000);
		Set<String> windowhandle=driver.getWindowHandles();
		Iterator<String> iter=windowhandle.iterator();
		String mainWindow=iter.next();
		System.out.println("mainWindow"+ mainWindow);
		driver.switchTo().window(mainWindow);
	}


	public void verifyWebTableSkusInBasket(String skuCount) {
		// TODO Auto-generated method stub
		String beforeXpath="//*[contains(@class,'rgMasterTable')]/tbody/tr[";
		String afterXpath="]/td[2]";		
		String createdBasketName="Automate12";
		for(int i=2;i<5;i++)
		{
			String actualXpath=beforeXpath+i+afterXpath;
			WebElement element=driver.findElement(By.xpath(actualXpath));			
			if(element.getText().contains(createdBasketName))
			{
				log.info("Created basket is present");	
				String ActualSkuCount= driver.findElement(By.xpath("//*[contains(@class,'rgMasterTable')]/tbody/tr["+i+"]/td[4]")).getText();			

				if(ActualSkuCount.equalsIgnoreCase(skuCount))
				{
					log.info("Sku added in the basket is verified");
				}
				else
				{
					log.error("Sku added in the basket is not show as expected");
				}

				break;
			}
			else
			{
				log.error("Created basket is not present");
			}
		}


	}
}

