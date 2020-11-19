package zephyr.config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.Properties;

public class ReadZephyrConfigurationDetails {

	private Properties properties;
	private final String propertyFilePath= System.getProperty("user.dir")+"/configs/ZephyrConfiguration.properties";
	public static  String zephyrBaseURL=null;
	public static  String zephyrAccessKey=null;
	public static  String zephyrSecretKey=null;
	public static  String jiraUserName=null;
	public static  String jiraPwd=null;
	public static String jiraBaseURL = null;
	
	public ReadZephyrConfigurationDetails() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("ZephyrConfiguration.properties not found at " + propertyFilePath);
		}		
	}
	
	//initializes zephyr configuration details
	public void loadZephyrConfig() {
		try {
			if(properties!=null) {
			jiraBaseURL=properties.getProperty("jiraBaseURL");
			zephyrBaseURL=properties.getProperty("zephyrBaseURL");
			zephyrAccessKey=properties.getProperty("zephyrAccessKey");
			zephyrSecretKey=properties.getProperty("zephyrSecretKey");
			jiraUserName=properties.getProperty("jiraUserName");
			jiraPwd=properties.getProperty("jiraPwd");
			}
		} catch (Exception e) {
		System.out.println("Exception while reading zephyr properties!");
		System.out.println("Exception is:"+e.getMessage());
		}
	}
	

}
