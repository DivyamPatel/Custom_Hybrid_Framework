package runners;

import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import managers.FileReaderManager;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import enums.ConfigurationFile;
import enums.ConfigurationProperties;
import enums.RunOn;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "functionalTests",
		glue= {"stepDefinitions"},
		plugin = {"com.cucumber.listener.ExtentCucumberFormatter:"},
		monochrome = true
		)
public class TestRunner1 extends AbstractTestNGCucumberTests
{	
	private DesiredCapabilities capabilities = new DesiredCapabilities();
	private static AppiumDriver<WebElement> appiumDriver = null;
	private static RemoteWebDriver driver = null;
	private String serverIP = FileReaderManager.getInstance().getConfigReader(ConfigurationFile.GENERAL).getConfProperty(ConfigurationProperties.SERVER_IP);
	private String Port = FileReaderManager.getInstance().getConfigReader(ConfigurationFile.GENERAL).getConfProperty(ConfigurationProperties.PORT);
	private RunOn runOn = FileReaderManager.getInstance().getConfigReader(ConfigurationFile.GENERAL).getRunOn();
	private String baseURL = FileReaderManager.getInstance().getConfigReader(ConfigurationFile.GENERAL).getConfProperty(ConfigurationProperties.BASE_URL);
	int lastRun = Integer.parseInt(FileReaderManager.getInstance().getConfigReader(ConfigurationFile.GENERAL).getConfProperty(ConfigurationProperties.LAST_RUN));

	@BeforeSuite
	private void setup() {
		initDriver();
	}

	public AppiumDriver<WebElement> getAppiumDriver() {
		return appiumDriver;
	}

	public RemoteWebDriver getDriver() {
		return driver;
	}

	private void initDriver()
	{
		System.out.println("Inside initDriver method");
		String serverUrl = "http://" + serverIP + ":" + Port + "/wd/hub";
		System.out.println("Argument to driver object : " + serverUrl);

		switch (runOn) {
		case MOBILE_ANDROID:
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, FileReaderManager.getInstance().getConfigReader(ConfigurationFile.GENERAL).getConfProperty(ConfigurationProperties.DEVICE_NAME));
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, FileReaderManager.getInstance().getConfigReader(ConfigurationFile.GENERAL).getConfProperty(ConfigurationProperties.PLATFORM_NAME));
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, FileReaderManager.getInstance().getConfigReader(ConfigurationFile.GENERAL).getConfProperty(ConfigurationProperties.PLATFORM_VERSION));
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
			capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") +"/"+ FileReaderManager.getInstance().getConfigReader(ConfigurationFile.GENERAL).getConfProperty(ConfigurationProperties.APP_PATH));
			capabilities.setCapability("appPackage", FileReaderManager.getInstance().getConfigReader(ConfigurationFile.GENERAL).getConfProperty(ConfigurationProperties.APP_PACKAGE));
			capabilities.setCapability("appActivity", FileReaderManager.getInstance().getConfigReader(ConfigurationFile.GENERAL).getConfProperty(ConfigurationProperties.APP_ACTIVITY));
			capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "0");
			try {
				appiumDriver = new AppiumDriver<WebElement>(new URL(serverUrl), capabilities);
			}
			catch (Exception ex) {
				throw new RuntimeException("Appium driver could not be initialised for device due to exception "+ex.getMessage());
			}
			System.out.println("Driver in initdriver is : "+appiumDriver);
			appiumDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			switchToWebView();
			break;

		case MOBILE_IOS:
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, FileReaderManager.getInstance().getConfigReader(ConfigurationFile.GENERAL).getConfProperty(ConfigurationProperties.DEVICE_NAME));
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, FileReaderManager.getInstance().getConfigReader(ConfigurationFile.GENERAL).getConfProperty(ConfigurationProperties.PLATFORM_NAME));
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, FileReaderManager.getInstance().getConfigReader(ConfigurationFile.GENERAL).getConfProperty(ConfigurationProperties.PLATFORM_VERSION));
			capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") +"/"+ FileReaderManager.getInstance().getConfigReader(ConfigurationFile.GENERAL).getConfProperty(ConfigurationProperties.APP_PATH));
			capabilities.setCapability("appPackage", FileReaderManager.getInstance().getConfigReader(ConfigurationFile.GENERAL).getConfProperty(ConfigurationProperties.APP_PACKAGE));
			capabilities.setCapability("appActivity", FileReaderManager.getInstance().getConfigReader(ConfigurationFile.GENERAL).getConfProperty(ConfigurationProperties.APP_ACTIVITY));
			capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "0");
			try {
				appiumDriver = new AppiumDriver<WebElement>(new URL(serverUrl), capabilities);
			}
			catch (Exception ex) {
				throw new RuntimeException("IOS driver could not be initialised for device due to exception "+ex.getMessage());
			}
			System.out.println("Driver in initdriver is : "+appiumDriver);
			appiumDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			switchToWebView();
			break;

		case MOBILE_BROWSER:
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, FileReaderManager.getInstance().getConfigReader(ConfigurationFile.GENERAL).getConfProperty(ConfigurationProperties.DEVICE_NAME));
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, FileReaderManager.getInstance().getConfigReader(ConfigurationFile.GENERAL).getConfProperty(ConfigurationProperties.PLATFORM_NAME));
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, FileReaderManager.getInstance().getConfigReader(ConfigurationFile.GENERAL).getConfProperty(ConfigurationProperties.PLATFORM_VERSION));
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
			capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, FileReaderManager.getInstance().getConfigReader(ConfigurationFile.GENERAL).getConfProperty(ConfigurationProperties.BROWSER_NAME));
			try {
				appiumDriver = new AppiumDriver<WebElement>(new URL(serverUrl), capabilities);
				appiumDriver.get(baseURL);
			}
			catch (Exception ex) {
				throw new RuntimeException("Appium driver could not be initialised for device due to exception "+ex.getMessage());
			}
			System.out.println("Driver in initdriver is : "+appiumDriver);
			break;

		case WEB:
			String browser = FileReaderManager.getInstance().getConfigReader(ConfigurationFile.GENERAL).getBrowserType();
			if (browser.equals(BrowserType.CHROME)) {
				String chromeDriverPath = System.getProperty("user.dir") + "/" + FileReaderManager.getInstance().getConfigReader(ConfigurationFile.GENERAL).getConfProperty(ConfigurationProperties.CHROME_DRIVER_PATH);
				System.setProperty("webdriver.chrome.driver", chromeDriverPath);
				ChromeOptions options = new ChromeOptions();
				options.addArguments("disable-infobars");
				options.setCapability("platform", Platform.WINDOWS);
				try{
					driver = new ChromeDriver(options);
				} catch (Exception e) {
					throw new RuntimeException("Webdriver could not be initialized due to exception "+e.getMessage());
				}
				System.out.println("Driver in initdriver is : "+driver);
			}
			else if (browser.equals(BrowserType.FIREFOX)) {
				String firefoxDriverPath = System.getProperty("user.dir") + "/" + FileReaderManager.getInstance().getConfigReader(ConfigurationFile.GENERAL).getConfProperty(ConfigurationProperties.FIREFOX_DRIVER_PATH);
				System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
				FirefoxOptions options = new FirefoxOptions();
				options.setCapability("--marionette", true);
				options.setCapability("platform", Platform.WINDOWS);
				try {
					driver = new FirefoxDriver();
				} catch (Exception e) {
					throw new RuntimeException("Webdriver could not be initialized due to exception "+e.getMessage());
				}
				System.out.println("Driver in initdriver is : "+driver);
			}
			else{
				System.out.println("Invalid browser type");
			}
			driver.manage().window().maximize();
			driver.get(baseURL);
			break;

		default:
			throw new RuntimeException("Invalid Option");
		}
	}

	@AfterSuite	
	private void tearDown() {
		if (runOn.toString().toUpperCase().startsWith("MOBILE")) {
			appiumDriver.quit();
		} else {
			driver.quit();	
		}
	}

	protected void switchToWebView() {
		WebDriverWait wait = new WebDriverWait(appiumDriver, 240);
		By webView = By.className("android.webkit.WebView");
		wait.until(ExpectedConditions.visibilityOfElementLocated(webView));

		Set<String> availableContexts = ((AppiumDriver<WebElement>) appiumDriver).getContextHandles();
		System.out.println("Context is "+availableContexts);
		for(String context : availableContexts) {
			if(context.contains("WEBVIEW")){
				System.out.println("Context Name is " + context);
				try {
					((AppiumDriver<WebElement>) appiumDriver).context(context);
				} catch(Exception e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
	
	protected void switchToDefaultView() {	
		Set<String> availableContexts = ((AppiumDriver<WebElement>) appiumDriver).getContextHandles();
		for(String context : availableContexts) {
			if(context.contains("NATIVE_APP")){
				System.out.println("Context Name is " + context);
				try {
					((AppiumDriver<WebElement>) appiumDriver).context(context);
				} catch(Exception e) {
					e.printStackTrace();
				}
				break;
			}
		}	
	}

	protected String getPageTitle() {
		String title = null;
		if (runOn.toString().toUpperCase().startsWith("MOBILE")) {
			title = appiumDriver.getTitle();
		} else {
			title = driver.getTitle();
		}
		return title;
	}
	
	public String getReportPath() {
		lastRun = lastRun + 1;
		String reportPath = System.getProperty("user.dir") + "/" + FileReaderManager.getInstance().getConfigReader(ConfigurationFile.GENERAL).getConfProperty(ConfigurationProperties.REPORT_PATH) +"/"+ "Run_" +Integer.toString(lastRun);
		return reportPath;
	}
}
