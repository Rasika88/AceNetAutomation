package stepDefinitions;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GridTest {

	public static void main(String args[]) throws MalformedURLException{
	DesiredCapabilities dc=new DesiredCapabilities();
	dc.setBrowserName("chrome");
	dc.setPlatform(Platform.WINDOWS);
	WebDriver driver= new RemoteWebDriver(new URL("http://172.24.36.33:4444/wd/hub"),dc);
	
	driver.get("http://www.yahoo.com");
	}
}
