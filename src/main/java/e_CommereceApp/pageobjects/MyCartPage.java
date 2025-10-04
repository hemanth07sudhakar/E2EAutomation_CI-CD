package e_CommereceApp.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import e_CommerceApp.AbstractComponents.AbstractComponent;

public class MyCartPage extends AbstractComponent {

	WebDriver driver;

	public MyCartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "div[class='cartSection'] h3")
	List<WebElement> myCartProducts;// My cart products list

	@FindBy(css = ".totalRow button")
	WebElement checkOutButton;// checkout products

	By textWait = By.cssSelector("div[class='cartSection'] h3");

	public List<WebElement> myCartProductsList() {
		return myCartProducts;
	}

	public Boolean myCartPageProducts(String searchName) {

		textToBePresentInElementLocated(textWait, searchName);// wait for text to be visible in MyCart page
		List<WebElement> cartProducts = myCartProductsList();// list the My cart items names
		// check any match items as "Adidas" item then click to checkout button
		Boolean cartItem = cartProducts.stream().anyMatch(cartProd -> cartProd.getText().equalsIgnoreCase(searchName));
		return cartItem;
//		checkOutButton.click(); // checkout products
	}

	public CheckOutPage goToCheckOut() {
		checkOutButton.click();
		return new CheckOutPage(driver);

	}
}