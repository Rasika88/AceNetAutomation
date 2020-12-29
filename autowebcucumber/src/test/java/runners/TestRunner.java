
package runners;


import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;



//import com.vimalselvam.cucumber.listener.Reporter; //Imported this by Siva

import com.cucumber.listener.Reporter; //commented by Siva

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import dataProvider.ConfigFileReader;
import managers.FileReaderManager;
import stepDefinitions.Hooks;
import zephyr.config.ReadZephyrConfigurationDetails;
import zephyr.utils.CreateCycleInZephyr;


@RunWith(Cucumber.class)
@CucumberOptions(
	features = "src/test/resources/function"+ "altest/AceNetOrderScenario.feature",
	glue= {"stepDefinitions"},
	//for logging to report portal 
	//plugin = { "pretty","com.epam.reportportal.cucumber.StepReporter"},
	plugin = { "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"},
	monochrome = true
	,tags= {"@PlaceOrderWithShiptoCustomer"}
	//,tags= {"@AritcleDetailsPage,@PlaceStockReserveOrder,@PlaceOrderShipToRetiler,@AcenetHomePageSearchAnArticleTestSteps"}
	
	)
 
public class TestRunner{
	@AfterClass
	public static void writeExtentReport() {
			Reporter.loadXMLConfig(new File(FileReaderManager.getInstance().getConfigReader().getReportConfigPath())); 
		
		 
	}
	
	
	@BeforeClass
	public static void createExecutionCycle() throws Exception {
		System.out.println("in before class");
		ConfigFileReader configReader=new ConfigFileReader();
		if(configReader.isZephyrEnabled()) {
		 FileReaderManager.getInstance().getZephyrConfigReader().loadZephyrConfig();
		 CreateCycleInZephyr obj=new CreateCycleInZephyr(ReadZephyrConfigurationDetails.jiraBaseURL, 
					ReadZephyrConfigurationDetails.zephyrBaseURL, ReadZephyrConfigurationDetails.zephyrAccessKey,
					ReadZephyrConfigurationDetails.zephyrSecretKey, ReadZephyrConfigurationDetails.jiraUserName, 
					ReadZephyrConfigurationDetails.jiraPwd, "QE_1","1.0");
		 System.out.println("in before class2");
		 try {
			Hooks.cycleId=obj.createCycle();
			System.out.println("in before class3");
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	
		System.out.println("Creating Exec Cycle!");
	}}
}