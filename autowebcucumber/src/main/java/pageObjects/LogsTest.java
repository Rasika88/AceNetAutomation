package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import dataProvider.ConfigFileReader;


public class LogsTest {
	WebDriver driver;
	Logger log=LogManager.getLogger("LogsTest");
	ConfigFileReader configOb =new ConfigFileReader();

	@FindBy(id = "headerDiv")
	WebElement confirmLogin;
	//Siva added and parameterized
	@FindBy(xpath="//*[@id=\"dataTable\"]/tbody/tr[1]/td[1]/a")
	WebElement firstStore;
	@FindBy(xpath = "//img[@class='searchBoxBtn']")
	WebElement SearchButton;
	@FindBy(id= "tbxSearchBox")
	WebElement searchTextBox;
	
	//Initializing driver
	public LogsTest(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/* Verifying Page load*/ 
	public void loadPage() {
		driver.get(configOb.getApplicationUrl());
		selenium.Wait.untilPageLoadComplete(driver);
		log.info("Login success and user is on AceNet Home Page ");

	}
	public void switchToiFrame() {

		int size = driver.findElements(By.tagName("iframe")).size();
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
		confirmLogin.isDisplayed();
		log.debug("Confirmed login");
		
	}
	public void searchAnArticle(String ArticleNo) throws InterruptedException {
		searchTextBox.sendKeys(ArticleNo);
		SearchButton.click();
		selenium.Wait.untilPageLoadComplete(driver, (long) 17);
		
		
	}
	
}





