package stepDefinitions;

import org.testng.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import enums.MenuOptions;
import managers.PageObjectManager;
import pageobjects.HomePage;
import pageobjects.LeftMenuPage;
import runners.TestRunner1;

public class TC_Login extends TestRunner1{

	PageObjectManager pageObjectManager = new PageObjectManager(getAppiumDriver(), getDriver());
	HomePage homePage = pageObjectManager.getHomePage();
	LeftMenuPage leftMenuPage = pageObjectManager.getLeftMenuPage();

	@Given("^User is on Homepage$")
	public void user_is_on_Homepage() throws InterruptedException {
		if (leftMenuPage.verifyLeftMenuOption(MenuOptions.LOGOUT)) {
			leftMenuPage.gotoMainMenu(MenuOptions.LOGOUT);
		}
		leftMenuPage.clickOnClose();
	}

	@When("^User clicks on SignIn tab$")
	public void user_clicks_on_SignIn_tab() {
		homePage.clickOnSignInTab();
	}
	
	@When("^User enters valid username \"([^\"]*)\" and valid password \"([^\"]*)\"$")
	public void user_enters_valid_username_and_valid_password(String username, String password) {
		homePage.setUsername(username);
		homePage.setPassword(password);
	}

	@When("^User clicks on SignIn button$")
	public void user_clicks_on_SignIn_button() throws InterruptedException {
		homePage.clickOnLogin();
		Thread.sleep(6000);
	}

	@Then("^Verify user is logged into application$")
	public void verify_user_is_logged_into_application() throws InterruptedException {
		if (!leftMenuPage.verifyLeftMenuOption(MenuOptions.LOGOUT)) {
			Assert.assertTrue(false, "User is not able to login successfully");
		}
		leftMenuPage.clickOnClose();
	}

	@Then("^Verify user is navigated to \"([^\"]*)\" or \"([^\"]*)\" page$")
	public void verify_user_is_navigated_to_or_page(String page1, String page2) {
		if (!(getPageTitle().equalsIgnoreCase(page1) || getPageTitle().equalsIgnoreCase(page2))) {
			Assert.assertTrue(false, "User must be navigated to "+page1+" or "+page2);
		}
	}

	@When("^User enters invalid username \"([^\"]*)\" and invalid password \"([^\"]*)\"$")
	public void user_enters_invalid_username_and_invalid_password(String username, String password) {
		homePage.setUsername(username);
		homePage.setPassword(password);
	}

	@Then("^Verify user is not logged into application$")
	public void verify_user_is_not_logged_into_application() throws InterruptedException {
		if (leftMenuPage.verifyLeftMenuOption(MenuOptions.LOGOUT)) {
			Assert.assertTrue(false, "User must not be logged into application");
		}
		leftMenuPage.clickOnClose();
	}
}
