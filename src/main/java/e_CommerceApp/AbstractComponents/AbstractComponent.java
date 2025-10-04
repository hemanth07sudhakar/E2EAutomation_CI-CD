package e_CommerceApp.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import e_CommereceApp.pageobjects.MyCartPage;
import e_CommereceApp.pageobjects.OrderPage;

public class AbstractComponent {

	WebDriver driver;

	// constructor of class
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "button[routerlink*='cart']")
	WebElement cartButton;

	@FindBy(css = "button[routerlink*='myorders']")
	WebElement orderButton;

	// method for wating state
	public void visibilityOfElementLocated(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void invisibilityOfElementLocated(By locator) throws InterruptedException {
		Thread.sleep(1000);
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// wait.until(ExpectedConditions.invisibilityOf(driver.findElement(locator)));
	}

	public void textToBePresentInElementLocated(By locator, String searchName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, searchName));
	}

	// wait for WebElement loading
	public void visibilityOfWebElementLocated(WebElement locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(locator));
	}

	public MyCartPage goToCart() {
		cartButton.click();
		// inherits the CartPge
		return new MyCartPage(driver);
	}

	public OrderPage goToOrder() {
		orderButton.click();
		// inherits the CartPge
		return new OrderPage(driver);
	}
}