package e_CommerceApp.test;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MainTestFileTest_backup {

	public static void main(String[] args) {
		// setup the driver
		String searchName = "ADIDAS ORIGINAL";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		// get the Landing Page
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("hemanth@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Hemanth@18");
		driver.findElement(By.id("login")).click();

		// add wait state
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		// get Product Catalogue page
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3")); // 3 containers
		WebElement productName = products.stream().filter(
				prod -> prod.findElement(By.xpath(".//div[@class='card-body']/h5/b")).getText().equals(searchName))
				.findFirst().orElse(null);
		// System.out.println(productName.getText());
		// click the Add to cart button
		productName.findElement(By.cssSelector("div[class='card-body'] button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container"))); // confirmation message
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating")))); // loading
																											// animation
		// go to cart button to my cart page
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();

		// wait for text to be visible in MyCart page
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("div[class='cartSection'] h3"),
				searchName));
		// list the My cart items names
		List<WebElement> cartProducts = driver.findElements(By.cssSelector("div[class='cartSection'] h3"));
		// check any match items as "Adidas" item then click to checkout button
		Boolean cartItem = cartProducts.stream().anyMatch(cartProd -> cartProd.getText().equalsIgnoreCase(searchName));
		Assert.assertTrue(cartItem);
		driver.findElement(By.cssSelector(".totalRow button")).click();

		// in Checkout page select the country and place the order
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "ind").build().perform();
		// wait for countries list appear
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results button")));
		List<WebElement> selectCountry = driver.findElements(By.cssSelector(".ta-results button"));
		// loop through select coutry
		for (WebElement country : selectCountry) {
			if (country.getText().equalsIgnoreCase("India")) {
				country.click();
				break;
			}
		}

		// click the place order button for confimation page
		driver.findElement(By.cssSelector(".action__submit")).click();

		// in confirmation page check the thank you messsage presernt or not
//		String confirmText = driver.findElement(By.cssSelector("h1.hero-primary")).getText();
//		Assert.assertTrue(confirmText.equalsIgnoreCase("Thankyou for the order."));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.hero-primary")));
		String text = driver.findElement(By.cssSelector("h1.hero-primary")).getText();
		Assert.assertTrue(text.equalsIgnoreCase("Thankyou for the order."));
		driver.close();
	}
}
