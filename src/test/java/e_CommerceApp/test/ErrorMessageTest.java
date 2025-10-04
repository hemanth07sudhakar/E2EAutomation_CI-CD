package e_CommerceApp.test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import e_CommerceApp.TestComponents.BaseTest;
import e_CommerceApp.TestComponents.RetryTest;
import e_CommereceApp.pageobjects.CheckOutPage;
import e_CommereceApp.pageobjects.ConfirmationPage;
import e_CommereceApp.pageobjects.MyCartPage;
import e_CommereceApp.pageobjects.ProductCatalogue;

public class ErrorMessageTest extends BaseTest {
	@Test(groups = { "errorHandling" })
	public void errorLoginValidation() throws InterruptedException, IOException {
		// setup the driver
		String searchName = "ADIDAS ORIGINAL";
		String searchCountry = "ind";

		landingPage.clickLogin("hemanth@gmail.com", "Hemanth@181 ");
		Assert.assertEquals("Incorrect email or password.", landingPage.errorMessage());

	}

	@Test(dependsOnMethods = { "errorLoginValidation" }, retryAnalyzer = RetryTest.class)
	public void errorCartProductValidation() throws InterruptedException, IOException {
		String searchName = "ADIDAS ORIGINAL";
		String searchCountry = "ind";

		ProductCatalogue productCatalogue = landingPage.clickLogin("hemanth@gmail.com", "Hemanth@18");
		productCatalogue.getProducts(searchName);
		MyCartPage myCartpage = productCatalogue.goToCart();// go to cart button to my cart page
		Boolean cartItem = myCartpage.myCartPageProducts("ADIDAS ORIGINAL1");
		Assert.assertTrue(cartItem);

	}

}