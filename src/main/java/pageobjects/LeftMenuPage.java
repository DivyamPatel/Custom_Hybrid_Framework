package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import coreclasses.BaseFramework;
import enums.ConfigurationFile;
import enums.MenuOptions;
import io.appium.java_client.AppiumDriver;
import managers.FileReaderManager;
import managers.PageObjectManager;

public class LeftMenuPage {

	private PageObjectManager pageObjectManager;
	private BaseFramework baseFrameworkPage;
	private String appType = FileReaderManager.getInstance().getConfigReader(ConfigurationFile.GENERAL).getRunOn().toString().toUpperCase();

	public LeftMenuPage(AppiumDriver<WebElement> appiumDriver, RemoteWebDriver driver) 
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
		@FindBy(id = "searchcar_workshop_menubutton"),
		@FindBy(id = "workshoplist_menubutton"),
		@FindBy(id = "customerslist_menu_button"),
		@FindBy(xpath = "//*[starts-with(@id, 'tabpanel-t')]//button[@id = 'menu-toggle-button']"),
		@FindBy(id = "menu-toggle-button"),
		@FindBy(id = "serviceplanlist_menu_button"),
		@FindBy(xpath = "//*[@id='app']/div[1]/button")
	})
	private static WebElement btn_menu;

	@FindAll({
		@FindBy(linkText = "Søg bil"),
		@FindBy(xpath = "//a[@title='Search']")
	})
	private static WebElement lnk_searchCar;

	@FindBy(linkText = "Værksted")
	private static WebElement lnk_workshop;

	@FindBy(linkText = "Kunder")
	private static WebElement lnk_customer;

	@FindBy(linkText = "Serviceplan")
	private static WebElement lnk_servicePlan;

	@FindBy(linkText = "Reparations fotos")
	private static WebElement lnk_repairationPhotos;

	@FindBy(linkText = "Notifikation")
	private static WebElement lnk_notifications;

	@FindBy(linkText = "Min profil")
	private static WebElement lnk_myProfile;

	@FindBy(linkText = "Support")
	private static WebElement lnk_support;

	@FindAll({
		@FindBy(linkText = "Logout"),
		@FindBy(linkText = "Log ud")
	})
	private static WebElement lnk_logOut;

	@FindBy(id = "menu_close_btn")
	private static WebElement btn_close;

	public void gotoMainMenu(MenuOptions option) {

		if (baseFrameworkPage.isDisplayed(btn_menu)) {
			baseFrameworkPage.click(btn_menu);
		}
		switch (option) {
		case SEARCH_CAR:
			baseFrameworkPage.click(lnk_searchCar);
			break;

		case WORKSHOP:
			baseFrameworkPage.click(lnk_workshop);
			break;

		case CUSTOMER:
			baseFrameworkPage.click(lnk_customer);
			break;

		case SERVICE_PLAN:
			baseFrameworkPage.click(lnk_servicePlan);
			break;

		case REPAIRATION_PHOTOS:
			baseFrameworkPage.click(lnk_repairationPhotos);
			break;

		case NOTIFICATIONS:
			baseFrameworkPage.click(lnk_notifications);
			break;

		case MY_PROFILE:
			baseFrameworkPage.click(lnk_myProfile);
			break;

		case SUPOORT:
			baseFrameworkPage.click(lnk_support);
			break;

		case LOGOUT:
			baseFrameworkPage.click(lnk_logOut);
			break;

		default:
			break;
		}
	}

	public boolean verifyLeftMenuOption(MenuOptions option) throws InterruptedException {

		if (baseFrameworkPage.isDisplayed(btn_menu)) {
			baseFrameworkPage.click(btn_menu);
		}
		Thread.sleep(1500);
		switch (option) {
		case SEARCH_CAR:
			return baseFrameworkPage.isDisplayed(lnk_searchCar);

		case WORKSHOP:
			return baseFrameworkPage.isDisplayed(lnk_workshop);

		case CUSTOMER:
			return baseFrameworkPage.isDisplayed(lnk_customer);

		case SERVICE_PLAN:
			return baseFrameworkPage.isDisplayed(lnk_servicePlan);

		case REPAIRATION_PHOTOS:
			return baseFrameworkPage.isDisplayed(lnk_repairationPhotos);

		case NOTIFICATIONS:
			return baseFrameworkPage.isDisplayed(lnk_notifications);

		case MY_PROFILE:
			return baseFrameworkPage.isDisplayed(lnk_myProfile);

		case SUPOORT:
			return baseFrameworkPage.isDisplayed(lnk_support);

		case LOGOUT:
			return baseFrameworkPage.isDisplayed(lnk_logOut);

		default:
			break;
		}
		return false;
	}

	public void clickOnClose() {
		if (baseFrameworkPage.isDisplayed(btn_close)) {
			baseFrameworkPage.click(btn_close);	
		}
	}

}
