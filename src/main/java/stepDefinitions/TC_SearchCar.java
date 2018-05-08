package stepDefinitions;

import org.testng.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import enums.MenuOptions;
import managers.PageObjectManager;
import pageobjects.LeftMenuPage;
import pageobjects.SearchCarPage;
import runners.TestRunner1;

public class TC_SearchCar extends TestRunner1{

	PageObjectManager pageObjectManager = new PageObjectManager(getAppiumDriver(), getDriver());
	SearchCarPage searchCarPage = pageObjectManager.getSearchCarPage();
	LeftMenuPage leftMenuPage = pageObjectManager.getLeftMenuPage();

	@Given("^User is on HomePage$")
	public void user_is_on_HomePage() throws InterruptedException {
		if (leftMenuPage.verifyLeftMenuOption(MenuOptions.LOGOUT)) {
			leftMenuPage.gotoMainMenu(MenuOptions.LOGOUT);
		}
		leftMenuPage.clickOnClose();
	}
	
	@When("^User clicks on Search Car tab$")
	public void user_clicks_on_search_car_tab() {
		leftMenuPage.gotoMainMenu(MenuOptions.SEARCH_CAR);
	}

	@When("^User selects the country \"([^\"]*)\"$")
	public void user_selects_the_country(String country) {
		searchCarPage.selectCountry(country);
	}

	@When("^User enters valid License Number \"([^\"]*)\"$")
	public void user_enters_valid_License_Number(String licenseNo) {
		searchCarPage.enterLicenceNo(licenseNo);
	}
	
	@When("^User clicks on Search button$")
	public void user_clicks_on_Search_button() throws InterruptedException {
		searchCarPage.clickOnSearch();
		Thread.sleep(2000);
	}

	@Then("^Verify car is searched successfully with valid License Number \"([^\"]*)\"$")
	public void verify_car_is_searched_successfully_with_valid_licence_number(String licenceNo) {
		if (!searchCarPage.getLicenceNo().equalsIgnoreCase(licenceNo)) {
			Assert.assertTrue(false, "User is not able to search car with valid license number");
		}
	}

	@When("^User enters valid Vin number \"([^\"]*)\"$")
	public void user_enters_valid_Vin_number(String vinNumber) {
		searchCarPage.enterVinNo(vinNumber);
	}

	@Then("^Verify car is searched successfully with valid VIN Number \"([^\"]*)\"$")
	public void verify_car_is_searched_successfully_with_valid_vin_number(String vinNo) {
		if (!searchCarPage.getVinNo().equalsIgnoreCase(vinNo)) {
			Assert.assertTrue(false, "User is not able to search car with valid VIN number");
		}
	}
}
