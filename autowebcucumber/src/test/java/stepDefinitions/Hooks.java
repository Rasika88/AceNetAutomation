package stepDefinitions;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import cucumber.TestContext;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import dataProvider.ConfigFileReader;
import sun.font.CreatedFontTracker;
import zephyr.config.ReadZephyrConfigurationDetails;
import zephyr.utils.AddUpdateTestsInZephyr;
import zephyr.utils.CreateAndLinkIssueInJira;
import zephyr.utils.CreateCycleInZephyr;
import java.text.SimpleDateFormat;

public class Hooks {

	TestContext testContext;
	AddUpdateTestsInZephyr addObj=null;
	public static String cycleId;
	static String executionId;
	ConfigFileReader configReader;
	CreateAndLinkIssueInJira addIssueObj;
	String testCaseKey;
	
	public Hooks(TestContext context) {
		testContext = context;
		configReader=new ConfigFileReader();
		if(configReader.isZephyrEnabled()) {
		addObj=new AddUpdateTestsInZephyr(360, ReadZephyrConfigurationDetails.jiraBaseURL, 
				 ReadZephyrConfigurationDetails.zephyrBaseURL, 
				 ReadZephyrConfigurationDetails.zephyrAccessKey, ReadZephyrConfigurationDetails.zephyrSecretKey,
				 ReadZephyrConfigurationDetails.jiraUserName, ReadZephyrConfigurationDetails.jiraPwd, cycleId);
		
		addIssueObj=new CreateAndLinkIssueInJira(ReadZephyrConfigurationDetails.jiraBaseURL,
				ReadZephyrConfigurationDetails.jiraUserName,ReadZephyrConfigurationDetails.jiraPwd);
		}
	}

	@Before
	public void BeforeSteps(Scenario scenario) throws Exception {
		System.out.println("In before steps");
		if(configReader.isZephyrEnabled()) {
		//String testCaseKey;
		try {
			testCaseKey=scenario.getName().split("_")[1];
			System.out.println("Test Execution Started for >> "+scenario.getName());
			addObj.addTestCasesToCycle(testCaseKey);
		} catch (Exception e) {
		throw new Exception("Exception in before steps--"+e.getMessage());
		}
		}
	}

	@After
	public void AfterSteps(Scenario scenario) throws Exception {
		System.out.println("In after steps");
		String issueKey="AX-50";
		try {
			if(configReader.isZephyrEnabled()) {
			addObj.updateExecutionStatus(scenario.getStatus());
			if(scenario.getStatus().equalsIgnoreCase("Failed")) {
				//create an issue/defect
				//link defect to the current execution cycle
				addIssueObj.createJiraIssue(scenario.getName(),scenario.getStatus(),"","Medium",testCaseKey);
			addObj.addTestCasesToCycle(issueKey);
			}}
			testContext.getWebDriverManager().closeDriver();
		} catch (Exception e) {
			e.printStackTrace();
		throw new Exception("Exception in after steps--"+e.getMessage());
	}
		
		finally {
	        Thread.sleep(5000);
	        DateFormat timeStamp = new SimpleDateFormat("ddMMMyyyy_HHmmss");
	        Date date = new Date();
	        String date1 = timeStamp.format(date);
	        String FileName = "Renamed_On_" + date1;
	        File f = new File(
	                "C:\\AceHardware\\OSAF_Acenet\\autowebcucumber\\autowebcucumber\\target\\cucumber-reports\\Report.html");
	        // The following file should not exist while running the program
	        File rF = new File("C:\\AceHardware\\OSAF_Acenet\\autowebcucumber\\autowebcucumber\\target\\cucumber-reports\\Report"+FileName+".html");
	        if (f.renameTo(rF)) {
	            System.out.println("Reports File was successfully renamed");
	        } else {
	            System.out.println("Error: Unable to rename file");
	        }
	        }
	}
}