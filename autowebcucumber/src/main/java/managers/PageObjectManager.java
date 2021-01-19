package managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import pageObjects.AceNetHomePage;
import pageObjects.AcenetArticleDeatilsPage;
import pageObjects.AcenetOrdersConfirmationPage;
import pageObjects.LogsTest;
import pageObjects.ProductManagement;

public class PageObjectManager {

	private RemoteWebDriver driver;
	private AceNetHomePage AceHp;
	private AcenetArticleDeatilsPage art;
	private AcenetOrdersConfirmationPage orderconfirm;
	private ProductManagement pmgnmt;
	
	public PageObjectManager(RemoteWebDriver driver) {
		this.driver = driver;
		System.out.println("PageObjectManager");
		System.out.println(this.driver );
	}
	
	
	public AceNetHomePage getAceNetHomePage() {
		return (AceHp==null)?AceHp=new AceNetHomePage(driver):AceHp;
	}

	public AcenetArticleDeatilsPage getAcenetArticleDeatilsPage() {
		return (art==null)?art=new AcenetArticleDeatilsPage(driver):art;
	}
	public AcenetOrdersConfirmationPage getAcenetOrdersConfirmationPage() {
		return (orderconfirm==null)?orderconfirm=new AcenetOrdersConfirmationPage(driver):orderconfirm;
	}
	public ProductManagement getProductManagement() {
		return (pmgnmt==null)?pmgnmt=new ProductManagement(driver):pmgnmt;
	}
}