package pageObjects;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dataProvider.ConfigFileReader;

public class AcenetOrdersConfirmationPage {
	WebDriver driver;
	Logger log=LogManager.getLogger("AcenetOrdersConfirmationPage");
	ConfigFileReader configOb =new ConfigFileReader();

	//Initializing driver
		public AcenetOrdersConfirmationPage(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}

	@FindBy(xpath="//div[@id='IDContainer']//section//tr[2]")
	WebElement confirmOrder;
	@FindBy(xpath="//*[@id='ctl00_ctl00_contentMainPlaceHolder_MainContent_repeaterConfirmation_ctl01_gvConfirmation']/tbody/tr[2]/td[2]")
	WebElement orderNumber;
	@FindBy(xpath="//*[@id='bodycontent']/div[3]/input[1]")
	WebElement continue_Button;
	@FindBy(xpath="(//*[contains(text(),'Manage Product')])[2])")
	WebElement manageProduct_Tab;
			
	@FindBy(xpath="(//*[contains(text(),'Stock Reserve Listing')])[2]")
	WebElement stockReserveListing_link;
	@FindBy(xpath="(//*[contains(text(),'Special Order Listing')])[2]")
	WebElement splOrderListing_link;
	
	public void ConfirmOrder()
	{

		/*log.info("Order confirmation method");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		confirmOrder.isDisplayed(); 
		log.info("Confirm the details");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement OrderDetails = driver.findElement(By.cssSelector("#ctl00_ctl00_contentMainPlaceHolder_MainContent_repeaterConfirmation_ctl01_gvConfirmation > tbody > tr:nth-child(2) > td:nth-child(2)"));
		String OrderNo = OrderDetails.getText();
		log.info("Stock Reserve Order No is : " + OrderNo );*/
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		String orderNo=orderNumber.getText();
		log.info("Stock Reserve Order No is : " + orderNo);

	}

	public void switchToOrderConfirmationIframe() {
		driver.switchTo().frame("iframeItemDetail"); 
		log.info("switched to order confirmation iframe");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	public void switchToDefault()
	{
		driver.switchTo().parentFrame();
	}

	public void switchToiframeRetailAppHost() {

		driver.switchTo().frame("iframeRetailAppHost"); 
		log.info("switched to iframe");
	}
	public void openStockReserveList(String verifySearchCategory) throws InterruptedException {
		// TODO Auto-generated method stub
		manageProduct_Tab.click();
		Thread.sleep(3000);
		stockReserveListing_link.click();
		switchToiframeRetailAppHost();
		splOrderListing_link.click();
		
		
		String a=verifySearchCategory;
		
		//*[@id="ctl00_ctl00_contentMainPlaceHolder_ContentPlaceHolder1_gvstockrsvlisting"]/tbody/tr[1]/td[2]/a
		
	}

}
