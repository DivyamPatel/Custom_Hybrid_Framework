package coreclasses;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import enums.ConfigurationFile;
import io.appium.java_client.AppiumDriver;
import managers.FileReaderManager;

public class BaseFramework {

	private AppiumDriver<WebElement> appiumDriver;
	private RemoteWebDriver driver;
	private JavascriptExecutor jse;
	private String appType = FileReaderManager.getInstance().getConfigReader(ConfigurationFile.GENERAL).getRunOn().toString().toUpperCase();
	
	

	public void sendKeys(WebElement element, String keysToSend)
	{
		if (element.isDisplayed() && element.isEnabled()) {
			try {

				highlightElement(element);
				System.out.println("");
				element.clear();
				element.sendKeys(keysToSend);
				if (appType.startsWith("MOBILE")) {
					appiumDriver.hideKeyboard();
				}
			} catch (Exception e) {
				System.out.println("Exception: " + e.getMessage() +" raised while locating element");
			}
		}
	}

	public void click(WebElement element) 
	{
		if (element.isDisplayed() && element.isEnabled()) {
			try {

				highlightElement(element);
				element.click();

			} catch (Exception e) {
				System.out.println("Exception: " + e.getMessage() +" raised while locating element");
			}
		}
	}

	public void clear(WebElement element) 
	{
		if (element.isDisplayed() && element.isEnabled()) {
			try {

				highlightElement(element);
				element.clear();

			} catch (Exception e) {
				System.out.println("Exception: " + e.getMessage() +" raised while locating element");
			}
		}
	}

	public String getText(WebElement element) 
	{	
		if (element.isDisplayed()) {
			highlightElement(element);
			return element.getText();
		}
		return null;
	}

	public boolean isDisplayed(WebElement element)
	{
		try {
			if (element.isDisplayed()) {
				highlightElement(element);
				return true;
			}
		} catch (NoSuchElementException e) {
			return false;
		}
		return false;
	}
	
	public String getAttribute(WebElement element, String attribute)
	{
		String value = null;
		if (element.isDisplayed() && element.isEnabled()) {
			try {
				highlightElement(element);
				value = element.getAttribute(attribute);

			} catch (Exception e) {
				System.out.println("Exception: " + e.getMessage() +" raised while locating element");
			}
		}
		return value;
	}

	

}
