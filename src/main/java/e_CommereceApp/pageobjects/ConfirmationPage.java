package e_CommereceApp.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import e_CommerceApp.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {
	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "h1.hero-primary")
	WebElement confirmationMessage;

	By confirmText = By.cssSelector("h1.hero-primary");

	public String getConfirmationMessage() {
		visibilityOfElementLocated(confirmText);
		return confirmationMessage.getText();
	}

}
