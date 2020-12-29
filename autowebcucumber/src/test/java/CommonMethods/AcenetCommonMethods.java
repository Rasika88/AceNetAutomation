package CommonMethods;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class AcenetCommonMethods {
	static WebDriver driver;
	static Logger log=LogManager.getLogger("LogsTest");
	
	public void switchToiframeRetailAppHost() {

		 driver.switchTo().frame("iframeRetailAppHost"); 
		log.info("switched to iframe");
		
	}
}
