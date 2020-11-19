package zephyr.utils;

import zephyr.config.ReadZephyrConfigurationDetails;

public class PerformOperationsInZephyr {

	private static String cycleId;
	
	public static void main(String[] args) {
		ReadZephyrConfigurationDetails readZephyrConfig=new ReadZephyrConfigurationDetails();
		readZephyrConfig.loadZephyrConfig();
	CreateCycleInZephyr obj=new CreateCycleInZephyr(ReadZephyrConfigurationDetails.jiraBaseURL, 
				ReadZephyrConfigurationDetails.zephyrBaseURL, ReadZephyrConfigurationDetails.zephyrAccessKey,
				ReadZephyrConfigurationDetails.zephyrSecretKey, ReadZephyrConfigurationDetails.jiraUserName, 
				ReadZephyrConfigurationDetails.jiraPwd, "ITS","V_1.0");
		
		try {
			cycleId=obj.createCycle();
		} catch (Exception e) {//3d406bc2-dab3-458a-ae94-d9649d55eef1
			// TODO Auto-generated catch block
			e.printStackTrace();
	//	}
		 AddUpdateTestsInZephyr addObj=new AddUpdateTestsInZephyr(360, ReadZephyrConfigurationDetails.jiraBaseURL, 
				 ReadZephyrConfigurationDetails.zephyrBaseURL, 
				 ReadZephyrConfigurationDetails.zephyrAccessKey, ReadZephyrConfigurationDetails.zephyrSecretKey,
				 ReadZephyrConfigurationDetails.jiraUserName, ReadZephyrConfigurationDetails.jiraPwd,cycleId);
		 
		 addObj.addTestCasesToCycle("TES-2");
		 addObj.updateExecutionStatus("Passed");
		
	}
}
}