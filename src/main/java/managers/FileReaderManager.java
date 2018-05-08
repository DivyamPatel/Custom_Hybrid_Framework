package managers;

import dataProviders.ConfigurationFileReader;
import enums.ConfigurationFile;

public class FileReaderManager {
	
	private static FileReaderManager fileReaderManager = new FileReaderManager();
	private static ConfigurationFileReader configFileReader;
 
	 public static FileReaderManager getInstance( ) {
	      return fileReaderManager;
	 }
	 
	 public ConfigurationFileReader getConfigReader(ConfigurationFile file) {
		 return (configFileReader == null) ? new ConfigurationFileReader(file) : configFileReader;
	 }
	
}
