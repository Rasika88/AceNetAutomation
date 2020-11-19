package managers;

import org.openqa.selenium.WebDriver;

import pageObjects.AceNetHomePage;
import pageObjects.AcenetArticleDeatilsPage;
import pageObjects.AcenetOrdersConfirmationPage;
import pageObjects.LogsTest;




public class PageObjectManager {

	private WebDriver driver;
	private AceNetHomePage AceHp;
	//private LogsTest lT;
	private AcenetArticleDeatilsPage art;
	private AcenetOrdersConfirmationPage orderconfirm;
	
	

	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
	}
	
	
	
	public AceNetHomePage getAceNetHomePage() {
		return (AceHp==null)?AceHp=new AceNetHomePage(driver):AceHp;
	}

	/*public LogsTest getLogsTest() {
		return (lT==null)?lT=new LogsTest(driver):lT;
	}*/
	public AcenetArticleDeatilsPage getAcenetArticleDeatilsPage() {
		return (art==null)?art=new AcenetArticleDeatilsPage(driver):art;
	}
	public AcenetOrdersConfirmationPage getAcenetOrdersConfirmationPage() {
		return (orderconfirm==null)?orderconfirm=new AcenetOrdersConfirmationPage(driver):orderconfirm;
	}
	
	}