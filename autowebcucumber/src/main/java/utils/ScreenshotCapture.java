package utils;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.cucumber.listener.Reporter; //commented by Siva
import com.epam.reportportal.service.ReportPortal;
//import com.vimalselvam.cucumber.listener.Reporter; //Imported this by Siva

import cucumber.TestContext;

public class ScreenshotCapture {
	TestContext testContext;

	public ScreenshotCapture(TestContext testContext) {
		this.testContext=testContext;
	} 

	public void captureScreenshot(String screenshotName)  {
		try {
		File sourcePath = ((TakesScreenshot) testContext.getWebDriverManager().getDriver()).getScreenshotAs(OutputType.FILE); 
		File destinationPath = new File(System.getProperty("user.dir") + "/target/test-report/extent-report/screenshots/" + screenshotName + ".png"); 
		FileUtils.copyFile(sourcePath, destinationPath);
		Reporter.addScreenCaptureFromPath(destinationPath.toString()); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
	

	public void captureScreenshotForReportPortal(String screenshotName, String msg)  {
		captureScreenshot(screenshotName);
		File destinationPath = new File(System.getProperty("user.dir") + "/target/test-report/extent-report/screenshots/"
		+screenshotName+".png"); 
		 ReportPortal.emitLog(msg, "INFO", Calendar.getInstance().getTime(),destinationPath);
		}
	
	
}