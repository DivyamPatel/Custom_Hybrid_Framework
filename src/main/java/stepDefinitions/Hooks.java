package stepDefinitions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.cucumber.listener.Reporter;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import enums.ConfigurationFile;
import enums.ConfigurationProperties;
import enums.RunOn;
import managers.FileReaderManager;
import runners.TestRunner1;

public class Hooks extends TestRunner1{

	@Before
	public void testMethod(Scenario scenario) {
		Reporter.assignAuthor("Divya Patel");
		setReportEnv();
	}

	@After
	public void afterScenario(Scenario scenario) throws IOException {
		System.out.println("Scenario: "+scenario.getName()+" "+scenario.getStatus());
		String screenshotPath = getScreenshotPath(scenario.getName());
		if (FileReaderManager.getInstance().getConfigReader(ConfigurationFile.GENERAL).getRunOn().equals(RunOn.MOBILE_ANDROID) || 
				FileReaderManager.getInstance().getConfigReader(ConfigurationFile.GENERAL).getRunOn().equals(RunOn.MOBILE_IOS)) {
			switchToWebView();
		}
		Reporter.addScreenCaptureFromPath(screenshotPath);
	}

	private String getScreenshotPath(String screenshotName) {
		TakesScreenshot ts; 
		String dateTime = new SimpleDateFormat("dd-MMM-yyyy_HH_mm_ss").format(new Date());
		String destination = getReportPath() + "\\screenshots\\" + screenshotName + "_" +dateTime +".png";
		if (FileReaderManager.getInstance().getConfigReader(ConfigurationFile.GENERAL).getRunOn().toString().startsWith("MOBILE_")) {
			switchToDefaultView();
			ts = (TakesScreenshot) getAppiumDriver();
		} else {
			ts = (TakesScreenshot) getDriver();
		}
		try {	
			File source = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			System.out.println("Exception thrown while capturing screenshot "+e);
			throw new RuntimeException();
		}
		return destination;
	}

	private void setReportEnv() {
		File f = new File(System.getProperty("user.dir") + "/" + FileReaderManager.getInstance().getConfigReader(ConfigurationFile.GENERAL).getConfProperty(ConfigurationProperties.REPORT_CONFIG_PATH));
		Reporter.loadXMLConfig(f);
		Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
		Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
		Reporter.setSystemInfo("Machine", System.getProperty("os.name"));
		Reporter.setSystemInfo("Run On", FileReaderManager.getInstance().getConfigReader(ConfigurationFile.GENERAL).getRunOn().toString());
		if (!(FileReaderManager.getInstance().getConfigReader(ConfigurationFile.GENERAL).getRunOn().equals(RunOn.WEB))) {
			Reporter.setSystemInfo("Platform Name", FileReaderManager.getInstance().getConfigReader(ConfigurationFile.GENERAL).getConfProperty(ConfigurationProperties.PLATFORM_NAME));
		}
		if (FileReaderManager.getInstance().getConfigReader(ConfigurationFile.GENERAL).getRunOn().equals(RunOn.MOBILE_BROWSER) ||
				FileReaderManager.getInstance().getConfigReader(ConfigurationFile.GENERAL).getRunOn().equals(RunOn.WEB)) {
			Reporter.setSystemInfo("Browser", FileReaderManager.getInstance().getConfigReader(ConfigurationFile.GENERAL).getBrowserType().toUpperCase());
		}
		Reporter.setSystemInfo("Java Version", System.getProperty("java.version"));
	}

}
