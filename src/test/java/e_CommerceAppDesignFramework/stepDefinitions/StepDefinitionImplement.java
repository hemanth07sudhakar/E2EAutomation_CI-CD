package e_CommerceAppDesignFramework.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import e_CommerceApp.TestComponents.BaseTest;
import e_CommereceApp.pageobjects.CheckOutPage;
import e_CommereceApp.pageobjects.ConfirmationPage;
import e_CommereceApp.pageobjects.LandingPage;
import e_CommereceApp.pageobjects.MyCartPage;
import e_CommereceApp.pageobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImplement extends BaseTest {
	String searchCountry = "ind";

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmText;

	@Given("I landed on loginPage")
	public void i_landed_on_loginPage() throws IOException {
		landingPage = launchApplocation();
	}

	@Given("^Logged in username (.+) and password (.+)$")
	public void logged_in_username_and_password(String userName, String password) {

		productCatalogue = landingPage.clickLogin(userName, password);

	}

	@When("^Add the products (.+) to Cart$")
	public void add_the_products_to_Cart(String productName) throws InterruptedException {

		productCatalogue.getProducts(productName);

	}

	@When("^Checkout the (.+) and click submit$")
	public void checkout_the_product_and_click_submit(String productName) {

		MyCartPage myCartpage = productCatalogue.goToCart();
		Boolean cartItem = myCartpage.myCartPageProducts(productName);
		Assert.assertTrue(cartItem);
		CheckOutPage checkOutPage = myCartpage.goToCheckOut();// checkout page
		checkOutPage.selectCounry(searchCountry);
		confirmText = checkOutPage.placeOrder();

	}

	@Then("{string} Message is displayed on ConfirmPage")
	public void message_is_displayed_on_ConfirmPage(String string) {

		String text = confirmText.getConfirmationMessage();
		Assert.assertTrue(text.equalsIgnoreCase(string));
		closeDriver();

	}

	@Then("{string} message is displayed")
	public void incorrect_email_or_password_message_is_displayed(String string) {
		Assert.assertEquals(string, landingPage.errorMessage());
		closeDriver();
	}

}
