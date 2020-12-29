package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dataProvider.ConfigFileReader;

public class ProductManagement {
	WebDriver driver;
	Logger log=LogManager.getLogger("ProductManagement");
	ConfigFileReader configOb =new ConfigFileReader();
	
	// Initializing driver
	public ProductManagement(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
		
	
	@FindBy(xpath="(//*[contains(text(),'Manage Product')])[2])")
	WebElement manageProduct_Tab;
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
	
	
	public void NavigateTomanageProductTab() {
		
		manageProduct_Tab.click();
		log.info("Navigated to product management tab");	
	}

	public void NavigateTodiscoveryAssortmentPlannerPage() {
		discoveryAssortmentPlanner_SubTab.click();
		log.info("Navigated to discovery Assortment Planner page");
		
	}

	public void discoveryAssortmentpageSelections() {
		
		bymerchClass.click();
		
		merchDept.click();	
		selenium.Wait.untilPageLoadComplete(driver);
		merchClass.click();
		selenium.Wait.untilPageLoadComplete(driver);
		merchPG.click();
		selenium.Wait.untilPageLoadComplete(driver);
		
	}
}
