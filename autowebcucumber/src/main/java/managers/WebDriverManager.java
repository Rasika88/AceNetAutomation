package managers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import enums.DriverType;
import enums.EnvironmentType;

public class WebDriverManager {
	private RemoteWebDriver driver; //commented for grid
	//private RemoteWebDriver driver;
	private static DriverType driverType;
	private static EnvironmentType environmentType;
	private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";

	public WebDriverManager() {
		driverType = FileReaderManager.getInstance().getConfigReader().getBrowser();
		environmentType = FileReaderManager.getInstance().getConfigReader().getEnvironment();
	}

	public RemoteWebDriver getDriver() throws MalformedURLException {
		System.out.println("getDriver method" +driver);
		if(driver == null) 
			driver = createDriver();
		return driver;
	}

	private RemoteWebDriver createDriver() throws MalformedURLException //Grid retrun type Driver to RemoteWebDriver
	{ 
		System.out.println("createDriver method" +environmentType);
		switch (environmentType) {	    
		case LOCAL : driver = createLocalDriver();
		System.out.println("local switch"+driver);
		break;
		case REMOTE : driver = createRemoteDriver();
		System.out.println("remote switch"+driver);
		break;
		}
		return driver;
	}

	private RemoteWebDriver createRemoteDriver() throws MalformedURLException //grid WebDriver to 
	{


		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setBrowserName("chrome");
		dc.setPlatform(Platform.WINDOWS);
		ChromeOptions options  = new ChromeOptions();
		options.merge(dc);
		String hubUrl="http://172.24.36.33:4444/wd/hub";
		RemoteWebDriver driver= new RemoteWebDriver(new URL(hubUrl),options);
		System.out.println("print driver " +driver);


		System.out.println("print driver " +driver);
		return driver;
	}


	private RemoteWebDriver createLocalDriver() throws MalformedURLException { //grid RemoteWebDriver from WebDriver
		switch (driverType) {	    
		case FIREFOX : driver = new FirefoxDriver();
		break;
		case CHROME : 

			ChromeOptions options  = new ChromeOptions();

			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			System.setProperty(CHROME_DRIVER_PROPERTY, System.getProperty("user.dir")
					+FileReaderManager.getInstance().getConfigReader().getDriverPath());

			capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
			driver = new ChromeDriver(capabilities);
			System.out.println("driver check" +driver);
			driver.manage().deleteAllCookies();          

			break;

		case INTERNETEXPLORER : driver = new InternetExplorerDriver();
		break;

		}

		if(FileReaderManager.getInstance().getConfigReader().getBrowserWindowSize()) driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitlyWait(), TimeUnit.SECONDS);
		return driver;
	}	

	public void closeDriver() {
		driver.close();
		driver.quit();
	}

}