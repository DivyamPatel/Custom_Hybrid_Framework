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

public class SearchCarPage {

	private PageObjectManager pageObjectManager;
	private BaseFramework baseFrameworkPage;
	private String appType = FileReaderManager.getInstance().getConfigReader(ConfigurationFile.GENERAL).getRunOn().toString().toUpperCase();

	public SearchCarPage(AppiumDriver<WebElement> appiumDriver, RemoteWebDriver driver) {
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
		@FindBy(id="search_country"),
		@FindBy(xpath = "//*[@class='hero__input__plate-graphics']")
	})
	private static WebElement btn_country;

	@FindAll({
		@FindBy(xpath = "//li[text()='DK']"),
		@FindBy(id="choose_country_dk")
	})
	private static WebElement lnk_county_dk;

	@FindAll({
		@FindBy(xpath = "//li[text()='SE']"),
		@FindBy(id="choose_country_se")
	})
	private static WebElement lnk_county_se;

	@FindAll({
		@FindBy(xpath = "//li[text()='NO']"),
		@FindBy(id="choose_country_se")
	})
	private static WebElement lnk_county_no;

	@FindAll({
		@FindBy(xpath = "//*[@id='search_country']/span"),
		@FindBy(xpath = "//span[@class='current-country']")
	})
	private static WebElement txt_currentCountry;

	@FindAll({
		@FindBy(id = "registration"),
		@FindBy(id="search_reg")
	})
	private static WebElement txtbx_licenceNumber;

	@FindAll({
		@FindBy(name = "vin"),
		@FindBy(id = "search_vinno")
	})
	private static WebElement txtbx_vinNumber;

	@FindAll({
		@FindBy(xpath = "//*[@value='Søg']"),
		@FindBy(id = "search")
	})
	private static WebElement btn_search;

	@FindAll({
		@FindBy(xpath = "//*[@id='car_info']//strong[text()='Reg. nr.']/../..//td[2]"),
		@FindBy(xpath = "//*[text()=' Reg. nr.']/..//span")
	})
	private static WebElement txt_RegNo;

	@FindAll({
		@FindBy(xpath = "//*[@id='car_info']//strong[text()='Stelnr.']/../..//td[2]"),
		@FindBy(xpath = "//*[text()=' Stelnr.']/..//span")
	})
	private static WebElement txt_EnginNo;

	public void selectCountry(String country) {
		baseFrameworkPage.click(btn_country);
		if (country.equalsIgnoreCase("DK")) {
			baseFrameworkPage.click(lnk_county_dk);
		}
		else if (country.equalsIgnoreCase("SE")) {
			baseFrameworkPage.click(lnk_county_se);
		}
		else if (country.equalsIgnoreCase("NO")) {
			baseFrameworkPage.click(lnk_county_no);
		}
	}

	public void enterLicenceNo(String licenseNo) {
		baseFrameworkPage.sendKeys(txtbx_licenceNumber, licenseNo);
	}

	public void enterVinNo(String vinNo) {
		baseFrameworkPage.sendKeys(txtbx_vinNumber, vinNo);
	}

	public void clickOnSearch() {
		baseFrameworkPage.click(btn_search);
	}

	public String getLicenceNo() {
		return baseFrameworkPage.getText(txt_RegNo);
	}

	public String getVinNo() {
		return baseFrameworkPage.getText(txt_EnginNo);
	}

	public String getCurrentCountry() {
		return baseFrameworkPage.getText(txt_currentCountry);
	}

}
