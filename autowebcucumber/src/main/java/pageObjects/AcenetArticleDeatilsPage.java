package pageObjects;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.appender.db.jdbc.DriverManagerConnectionSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import dataProvider.ConfigFileReader;

public class AcenetArticleDeatilsPage {
	WebDriver driver;
	Logger log=LogManager.getLogger("LogsTest");
	ConfigFileReader configOb =new ConfigFileReader();


	@FindBy(xpath="//div[@id='leftRscInfo']")
	WebElement RSCLocation;
	@FindBy(xpath="//span[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_sellingData_lblStoreLocation']")
	WebElement storeLocation;
	@FindBy(xpath = "//input[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_shipMethod_txtQty']")
	WebElement orderQty;
	@FindBy(xpath ="//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_shipMethod_divComments']/span")
	WebElement comment;
	@FindBy(xpath = "//*[@id='divErrorInfo']/div[1]/div[1]/div[2]/a/img")
	WebElement popUPcloseBttn;
	@FindBy(xpath = "//div[@id='dd']")
	WebElement dropDown;
	@FindBy(xpath = "//div[@id='dd']")
	WebElement selectShipingMethod;
	@FindBy(xpath = "//a[contains(text(),'Ship to Retailer')]")
	WebElement shipToRetailer;
	@FindBy(xpath = "//a[contains(text(),'RSC Pickup')]")
	WebElement rSCPickup; 
	@FindBy(xpath = "//a[contains(text(),'Stock Reserve')]")
	WebElement stockReserve_dropdown;
	@FindBy(xpath ="//input[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_shipMethod_txtComments']")
	WebElement addComment;
	@FindBy(xpath = "//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_shipMethod_btnExpressCheckout']")

	WebElement expressCheckout;
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
	@FindBy(xpath="//*[@id='btnOrdExpress']")
	WebElement expressCheckout_basket;
	@FindBy(xpath="//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_radGridShoppingBasket_ctl00_ctl02_ctl01_SelectRowSelectCheckBox']")
	WebElement selectAll_checkbox;
	@FindBy(xpath="//*[contains(text(),'Automate8')]")
	WebElement selectBasket;
	@FindBy(id= "tbxSearchBox")
	WebElement searchTextBox;
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
	@FindBy(xpath="//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_txtLookUp']")
	WebElement AceRewards;
	@FindBy(xpath="//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_btnValidate']")
	WebElement validate;
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
	@FindBy(xpath="//*[@id='divErrContent']")
	WebElement pastDateCheck;
	@FindBy(xpath="//*[@id='divErrorPopUpClose']")
	WebElement closePopup;



	// Initializing driver
	public AcenetArticleDeatilsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public void switchToPDPwindow() throws InterruptedException {
		log.debug("inside PDP");
		selenium.Wait.untilPageLoadComplete(driver);
		String parentWindowHandle= driver.getWindowHandle();
		//log.info("Parent window's handle -> " + parentWindowHandle);
		//Check th2 quantity of the article is not zero

		selenium.Wait.untilPageLoadComplete(driver, (long) 5);
		String expectedTitle = "Pages - Home";
		String actualTitle = driver.getTitle();
		log.info("Title : " + actualTitle);
		if (actualTitle.contentEquals(expectedTitle)) { 
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
		log.info("Title : " + actualTitle1);

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
		else
		{
			log.error("Shipping method is wrong");
		}
		//Select sel=new Select(driver.findElement(By.xpath("//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_shipMethod_lstShipMethod']/li[2]/a")));
		//sel.selectByVisibleText("Ship to Retailer");
		orderQty.sendKeys(productQty);
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


	public void expressCheckOutSTRClick() {

		log.info("Express check out method");
		addComment.sendKeys("Place Order");
		expressCheckout.click();
		selenium.Wait.untilJqueryIsDone(driver, (long) 10);
		log.info("Express check button click");
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

		//Boolean CheckboxSelection=rscQtyCheckBox.isEnabled();
		//System.out.println(CheckboxSelection);

		/*if(CheckboxSelection.equals(true))
		{
			rscQtyCheckBox.click();
			log.debug("RSC qty checkbox is Unchecked");}
		else {
			log.debug("RSC qty checkbox is checked");
		}*/

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
		//String shippingMet=shippingMethod;

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		//String basketName="Automate8"+ "\\"  + timestamp;
		String basketName="Automate8";
		System.out.println("basketName"+basketName);
		System.out.println("shippingMethod"+shippingMethod);
		switchToiframeRetailAppHost();
		Thread.sleep(10000);
		selenium.Wait.untilPageLoadComplete(driver, (long) 10);
		AddQty.sendKeys("1");
		AddToBasket_Btn.click();
		selenium.Wait.untilPageLoadComplete(driver);
		CreateNewBasket.click();
		BasketName_txt.sendKeys(basketName);
		Select sel=new Select(shipingMethod);
		sel.selectByVisibleText(shippingMethod);
		Thread.sleep(5000);
		AddToBasket_popBtn.click();
		WebDriverWait wait = new WebDriverWait(driver,65);		 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='MainContentPlaceHolder_btnAlertOKClose']")));
		driver.findElement(By.xpath("//*[@id='MainContentPlaceHolder_btnAlertOKClose']")).click();

		switchToDefault();
	}


	public void AddSkusToBasket(String sku) throws InterruptedException {
		Thread.sleep(3000);					
		searchTextBox.sendKeys("Test");
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/header/div[1]/div[7]/div/div/div/header/div[2]/div/a/img")));
		driver.findElement(By.xpath("/html/body/header/div[1]/div[7]/div/div/div/header/div[2]/div/a/img")).click();

		//addToCart.click();		
		selenium.Wait.untilPageLoadComplete(driver, (long) 20);
		switchToiframeRetailAppHost();
		selectBasket.click();
		Thread.sleep(3000);
		addSkusBasket.click();
		switchToiframeIfrmQuickEntry();
		addSku1.sendKeys(sku);		
		addQty1.sendKeys("1");
		//addSku2.sendKeys("20267");
		//addQty2.sendKeys("1");
		addSkusBasket.click();
		closeBasket_popup.click();
		driver.switchTo().defaultContent();
		switchToiframeRetailAppHost();
		checkout.click();
		Thread.sleep(5000);	

	}
	public void OrderCheckout(String shipType) throws InterruptedException {
				
		if(shipType.contains("Stock Reserve"))
		{				

			Thread.sleep(3000);	
			expressCheckout_basket.click();
		}
		else if(shipType.contains("Ship to Retailer"))
		{
			//checkout.click();
			checkout_ShipToRetailer.click();
			if(OrderPlacedConfirmation.equals(" Your Order was successfully submitted. "))
			{
				log.info("Order has placed successfully");
			}
			log.error("Order has not placed successfully");
		}
		else if(shipType.contains("Ship to Customer"))
		{
			//checkout.click();

			AceRewards.sendKeys("3126752312");
			shippingAddressName.sendKeys("John");
			shippingAddressLine.sendKeys("1211 s Quebec");
			shippingAddressCity.sendKeys("Denver");
			shippingAddressSte.sendKeys("CO");
			shippingAddressZip.sendKeys("80231");
			shippingAddressPhone.sendKeys("3126752312");
			validate.click();
		}
		else if(shipType.contains("RSC Pickup"))
		{
			//checkout.click();

			rSCPickupName.sendKeys("John");
			rSCPickupPickupPerson.sendKeys("John");
			rSCPickupDate.sendKeys("12/17/2020");
			rSCPickupTime.sendKeys("12");
			rSCPickupName.click();
			Thread.sleep(2000);
			rSCPickupOrder.click();


			if(OrderPlacedConfirmation.equals(" Your Order was successfully submitted. "))
			{
				log.info("Order has placed successfully");
			}
			log.error("Order has not placed successfully");
		}
		else
		{
			System.out.println("Cross-Dock Pick Up");
			checkout.click();

			rSCPickupName.sendKeys("John");
			rSCPickupPickupPerson.sendKeys("John");
			rSCPickupDate.sendKeys("12/17/2020");
			rSCPickupTime.sendKeys("12");
			rSCPickupName.click();
			Thread.sleep(2000);
			rSCPickupOrder.click();


			if(OrderPlacedConfirmation.equals(" Your Order was successfully submitted. "))
			{
				log.info("Order has placed successfully");
			}
			log.error("Order has not placed successfully");
		}
	}





}


