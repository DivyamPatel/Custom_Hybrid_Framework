package managers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import coreclasses.BaseFramework;
import enums.ConfigurationFile;
import io.appium.java_client.AppiumDriver;
import pageobjects.HomePage;
import pageobjects.LeftMenuPage;
import pageobjects.SearchCarPage;

public class PageObjectManager
{	

	private RemoteWebDriver driver;
	private BaseFramework baseFramework;
	private HomePage homePage;
	private SearchCarPage searchCarPage;
	private LeftMenuPage leftMenuPage;
	private String appType = FileReaderManager.getInstance().getConfigReader(ConfigurationFile.GENERAL).getRunOn().toString().toUpperCase();
	
	public PageObjectManager(AppiumDriver<WebElement> appiumDriver, RemoteWebDriver driver) {
		if (appType.startsWith("MOBILE")) {
			this.appiumDriver = appiumDriver;	
		} else {
			this.driver = driver;
		}
	}
	
	public BaseFramework getBaseFrameworkPage() {
		return (baseFramework == null) ? new BaseFramework(appiumDriver, driver) : baseFramework;
	}
	
	public HomePage getHomePage() {
		return (homePage == null) ? new HomePage(appiumDriver, driver) : homePage;
	}
	
	public SearchCarPage getSearchCarPage() {
		return (searchCarPage == null) ? new SearchCarPage(appiumDriver, driver) : searchCarPage;
	}
	
	public LeftMenuPage getLeftMenuPage() {
		return (leftMenuPage == null) ? new LeftMenuPage(appiumDriver, driver) : leftMenuPage;
	}
	
//	public <T extends BaseFramework> getPage(Class page)
//	{
//		
//	}
	
}
