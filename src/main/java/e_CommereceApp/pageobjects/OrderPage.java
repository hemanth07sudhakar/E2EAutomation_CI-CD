package e_CommereceApp.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import e_CommerceApp.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {

	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> myOrderProducts;// Order products list

	@FindBy(css = ".totalRow button")
	WebElement checkOutButton;// checkout products

	By textWait = By.cssSelector("tr td:nth-child(3)");

	public List<WebElement> myOrderProductsList() {
		return myOrderProducts;
	}

	public Boolean myOrderPageProducts(String searchName) {

		textToBePresentInElementLocated(textWait, searchName);// wait for text to be visible in MyCart page
		List<WebElement> orderProducts = myOrderProductsList();// list the My cart items names
		// check any match items as "Adidas" item then click to checkout button
		Boolean cartItem = orderProducts.stream().anyMatch(cartProd -> cartProd.getText().equalsIgnoreCase(searchName));
		return cartItem;
//		checkOutButton.click(); // checkout products
	}

}