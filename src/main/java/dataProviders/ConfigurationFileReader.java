package dataProviders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.remote.BrowserType;
import enums.ConfigurationFile;
import enums.ConfigurationProperties;
import enums.RunOn;

public class ConfigurationFileReader {

	private Properties properties;
	private String propertyFilePath;

	public ConfigurationFileReader(ConfigurationFile file)
	{
		if (file.equals(ConfigurationFile.GENERAL)) {
			propertyFilePath= System.getProperty("user.dir") + "/configurations/configurations.properties";
		}
		else if (file.equals(ConfigurationFile.EMAIL)) {
			propertyFilePath = System.getProperty("user.dir") + "/configurations/email-configurations.properties";
		} 
		else if (file.equals(ConfigurationFile.DEFAULT)) {
			propertyFilePath = System.getProperty("user.dir") + "/configurations/default-configurations.properties";
		}
		loadPropertyFile(propertyFilePath);
	}
	
	private void loadPropertyFile(String propertyFilePath) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("configurations.properties not found at " + propertyFilePath);
		}		
	}

	public RunOn getRunOn() {
		String runOn = getConfProperty(ConfigurationProperties.RUN_ON);
		if (runOn.equalsIgnoreCase(RunOn.MOBILE_ANDROID.toString())) 
			return RunOn.MOBILE_ANDROID;
		else if(runOn.equalsIgnoreCase(RunOn.MOBILE_IOS.toString()))
			return RunOn.MOBILE_IOS;
		else if (runOn.equalsIgnoreCase(RunOn.MOBILE_BROWSER.toString())) 
			return RunOn.MOBILE_BROWSER;
		else if(runOn.equalsIgnoreCase(RunOn.WEB.toString())) 
			return RunOn.WEB;
		else throw new RuntimeException("RunOn not specified in the configurations.properties file");
	}

	/*------------------------------------------------------------- FOR MOBILE ---------------------------------------------------------------------------*/

	public String getBrowserType() {
		String browser = getConfProperty(ConfigurationProperties.BROWSER_NAME);
		if (browser.equalsIgnoreCase(BrowserType.CHROME)) {
			return BrowserType.CHROME;
		}
		else if (browser.equalsIgnoreCase(BrowserType.FIREFOX)) {
			return BrowserType.FIREFOX;
		}
		return browser;
	}

	/*------------------------------------------------------------- GENERAL CONFIGURATIONS ---------------------------------------------------------------*/

	public String getConfProperty(ConfigurationProperties property) {
		String prop = properties.getProperty(property.toString());
		if (StringUtils.isBlank(prop)) {
			propertyFilePath = System.getProperty("user.dir") + "/configurations/default-configurations.properties";
			loadPropertyFile(propertyFilePath);
			prop = properties.getProperty(property.toString()); 
		}
		return prop;
	}

	public void setConfProperty(ConfigurationProperties property, String value) {
		PropertiesConfiguration config;
		try {
			config = new PropertiesConfiguration(propertyFilePath);
			try {
				config.setProperty(property.toString(), value);
				config.save();
			} catch (Exception e) {
				e.printStackTrace();
			}		
		} catch (ConfigurationException e1) {
			e1.printStackTrace();
		}
	}

}