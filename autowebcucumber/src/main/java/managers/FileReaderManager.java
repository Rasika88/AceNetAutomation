package managers;

import dataProvider.ConfigFileReader;
import zephyr.config.ReadZephyrConfigurationDetails;

public class FileReaderManager {

	private static FileReaderManager fileReaderManager = new FileReaderManager();
	private static ConfigFileReader configFileReader;
	private static ReadZephyrConfigurationDetails zephyrConfigFileReader;
	private static JsonDataReader jsonDataReader;
	
	private FileReaderManager() {
	
	}

	 public static FileReaderManager getInstance( ) {
	      return fileReaderManager;
	 }
	 public ConfigFileReader getConfigReader() {
		 return (configFileReader == null) ? new ConfigFileReader() : configFileReader;
	 }
	 public ReadZephyrConfigurationDetails getZephyrConfigReader() {
		 return (zephyrConfigFileReader == null) ? new ReadZephyrConfigurationDetails() : zephyrConfigFileReader;
	 }
	 
	 public JsonDataReader getJsonReader(){
		 return (jsonDataReader == null) ? new JsonDataReader() : jsonDataReader;
	}
}