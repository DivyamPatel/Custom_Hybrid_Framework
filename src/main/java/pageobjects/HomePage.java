package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import coreclasses.BaseFramework;
import enums.ConfigurationFile;
import io.appium.java_client.AppiumDriver;
import managers.FileReaderManager;
import managers.PageObjectManager;

public class HomePage
{
	private PageObjectManager pageObjectManager;
	private BaseFramework baseFrameworkPage;
	private String appType = FileReaderManager.getInstance().getConfigReader(ConfigurationFile.GENERAL).getRunOn().toString().toUpperCase();
			
	public HomePage(AppiumDriver<WebElement> appiumDriver, RemoteWebDriver driver) 
	{
		if (appType.startsWith("MOBILE")) {
			PageFactory.initElements(appiumDriver, this);
			pageObjectManager = new PageObjectManager(appiumDriver, driver);
		} else {
			PageFactory.initElements(driver, this);
			pageObjectManager = new PageObjectManager(appiumDriver, driver);
		}
		baseFrameworkPage = pageObjectManager.getBaseFrameworkPage();
	}

	@FindAll({
		@FindBy(linkText = "Søg bil"),
		@FindBy(id="search_segment_button")
	})
	private static WebElement lnk_searchCarTab;

	@FindAll({
		@FindBy(id = "signin_segment_button"),
		@FindBy(linkText = "Log ind")
	})
	private static WebElement lnk_loginTab;

	@FindAll({
		@FindBy(linkText = "Registrer"),
		@FindBy(id = "signup_segment_button")
	})
	private static WebElement lnk_registerTab;

	@FindAll({
		@FindBy(id = "signin_email"),
		@FindBy(name = "email")
	})
	private static WebElement txt_userName;

	@FindAll({
		@FindBy(id = "signin_password"),
		@FindBy(name = "password")
	})
	private static WebElement txt_password;

	@FindAll({
		@FindBy(id = "signin"),
		@FindBy(xpath = "//*[@value='Log ind']")
	})
	private static WebElement btn_submit;

	public void clickOnSignInTab() {
		baseFrameworkPage.click(lnk_loginTab);
	}
	
	public void clickOnSearchTab() {
		baseFrameworkPage.click(lnk_searchCarTab);
	}
	
	public void clickOnRegisterTab() {
		baseFrameworkPage.click(lnk_registerTab);
	}
	
	public void setUsername(String username) {
		baseFrameworkPage.sendKeys(txt_userName, username);
	}
	
	public void setPassword(String password) {
		baseFrameworkPage.sendKeys(txt_password, password);
	}
	
	public void clickOnLogin() {
		baseFrameworkPage.click(btn_submit);
	}

}
