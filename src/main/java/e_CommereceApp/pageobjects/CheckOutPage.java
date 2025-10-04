package e_CommereceApp.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import e_CommerceApp.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement searchCountry;

	@FindBy(css = ".action__submit")
	WebElement checkOutButton;

	@FindBy(css = ".ta-results button")
	List<WebElement> selectCountryList;

	By results = By.cssSelector(".ta-results button"); // list of countries

	public void selectCounry(String countryName) {

		Actions a = new Actions(driver);
		a.sendKeys(searchCountry, countryName).build().perform();
		visibilityOfElementLocated(results);// wait for countries list appear
		List<WebElement> selectCountry = selectCountryList;
		// loop through select coutry
		for (WebElement country : selectCountry) {
			if (country.getText().equalsIgnoreCase("India")) {
				country.click();
				break;
			}
		}
	}

	public ConfirmationPage placeOrder() {
		checkOutButton.click();
		return new ConfirmationPage(driver);
	}
}
