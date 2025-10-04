package e_CommereceApp.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import e_CommerceApp.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> products; // list of products items

	By productsWait = By.cssSelector(".mb-3");
	By prodElement = By.cssSelector("[class='card-body'] h5 b"); // product container inner element
	By prodElementCart = By.cssSelector("[class='card-body'] button:last-of-type");
	By confirmationMessageWait = By.id("toast-container"); // confirmationMessage
	By spinnerWait = By.cssSelector(".ng-animating"); // loading aniamation

	public List<WebElement> getProductsList() {

		visibilityOfElementLocated(productsWait); // load all products display
		return products;

	}

	public WebElement getProductsName(String searchName) {

		WebElement productName = getProductsList().stream()
				.filter(prod -> prod.findElement(prodElement).getText().equals(searchName)).findFirst().orElse(null);
		return productName;

	}

	public void getProducts(String searchName) throws InterruptedException {
		WebElement prod = getProductsName(searchName);
		prod.findElement(prodElementCart).click(); // click the Add to cart button in container
		visibilityOfElementLocated(confirmationMessageWait); // confirmation message
		invisibilityOfElementLocated(spinnerWait); // loading animation

	}

}