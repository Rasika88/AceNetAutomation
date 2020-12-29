package pageObjects;

import java.util.concurrent.TimeUnit;
//import java.util.logging.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import CommonMethods.AcenetCommonMethods;

//import org.slf4j.log;
//import org.slf4j.logFactory;
import org.apache.logging.log4j.*;

import dataProvider.ConfigFileReader;
import selenium.Wait;


public class AceNetHomePage {
	WebDriver driver;
	Logger log=LogManager.getLogger("LogsTest");
	ConfigFileReader configOb =new ConfigFileReader();
	
	
	@FindBy(xpath="(//*[contains(text(),'Store')])[2]")
	WebElement confirmLogin;	
	@FindBy(xpath="//*[@id='bs-select-1-0']/span/span[3]")
	WebElement firstStore;	
	@FindBy(xpath="//*[@id='search-form']/button/img")
	WebElement SearchButton;	
	@FindBy(id= "tbxSearchBox")
	WebElement searchTextBox;
	@FindBy(xpath="//*[@class='form-control']")
	WebElement storeSelection;

	//Initialize driver
	public AceNetHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	/*//Initializing driver
		public LogsTest(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}*/
		
		/* Verifying Page load*/ 
		public void loadPage() {
			driver.get(configOb.getApplicationUrl());
			selenium.Wait.untilPageLoadComplete(driver);
			log.info("Login success and user is on AceNet Home Page ");

		}
		public void switchToiFrame() {

			int size = driver.findElements(By.tagName("iframe")).size();
			log.debug(size);
			selenium.Wait.untilPageLoadComplete(driver);
			if(size>1)
			{
				driver.switchTo().frame(2);

			}
		}
		/* Verifying logged into first store*/
		public void loginToFirstStore() {		
			selenium.Wait.untilPageLoadComplete(driver);
			firstStore.click();
			selenium.Wait.untilPageLoadComplete(driver);
			log.info("Logged in to first store");
		}

		public void confirmLogin() {
			// TODO Auto-generated method stub
			String store=confirmLogin.getText();
			if(store != null)
			{
			log.debug("Store selected and confirmed login");
			}
			log.error("Store not selected successfully");
		}
		public void searchAnArticle(String ArticleNo) throws InterruptedException {
			searchTextBox.sendKeys(ArticleNo);
			log.info("Search with Article");
			selenium.Wait.untilPageLoadComplete(driver);
			SearchButton.click();
			log.info("Click on Search button with Article");
			
			
			
			
		}

		public void SelectingStore(String storeNumber) throws InterruptedException {
			
			storeSelection.sendKeys(storeNumber);
			Thread.sleep(2000);
			WebElement webElement = driver.findElement(By.xpath("//*[@class='form-control']"));
			webElement.sendKeys(Keys.ENTER);
//			Actions builder = new Actions(driver);   
//			builder.sendKeys(Keys.ENTER);
			log.info("Select store");
			Thread.sleep(5000);
			
		}

	
	

	}
