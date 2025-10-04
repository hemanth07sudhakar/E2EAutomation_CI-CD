package e_CommerceApp.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import e_CommerceApp.TestComponents.BaseTest;
import e_CommerceApp.TestComponents.RetryTest;
import e_CommereceApp.pageobjects.CheckOutPage;
import e_CommereceApp.pageobjects.ConfirmationPage;
import e_CommereceApp.pageobjects.MyCartPage;
import e_CommereceApp.pageobjects.OrderPage;
import e_CommereceApp.pageobjects.ProductCatalogue;

public class MainTestFileTest extends BaseTest {
//	String searchName = "ADIDAS ORIGINAL";
	String searchCountry = "ind";

	@Test(dataProvider = "getDataHashMap", groups = { "purchase" })
	// this is for add products to cart
	public void submitCartProduct(HashMap<String, String> input) throws InterruptedException, IOException {
		// setup the driver

		ProductCatalogue productCatalogue = landingPage.clickLogin(input.get("email"), input.get("password")); // login
		productCatalogue.getProducts(input.get("productName")); // select the product and click add to cart
		MyCartPage myCartpage = productCatalogue.goToCart();// go to cart button to my cart page
		Boolean cartItem = myCartpage.myCartPageProducts(input.get("productName"));
		Assert.assertTrue(cartItem);
		CheckOutPage checkOutPage = myCartpage.goToCheckOut();// checkout page
		checkOutPage.selectCounry(searchCountry);
		ConfirmationPage confirmText = checkOutPage.placeOrder();
		String text = confirmText.getConfirmationMessage();
		Assert.assertTrue(text.equalsIgnoreCase("Thankyou for the order."));

	}

	// check the procuct are present in the OrderPage or not
	@Test(dependsOnMethods = { "submitCartProduct" }, retryAnalyzer = RetryTest.class)
	public void varifyOrderPageProduct() {
		String searchName = "ADIDAS ORIGINAL";
		// ADIDAS ORIGINAL
		landingPage.clickLogin("hemanth@gmail.com", "Hemanth@18");
		OrderPage orderPage = landingPage.goToOrder();
		Assert.assertTrue(orderPage.myOrderPageProducts(searchName));

	}

	// DataProvider parametarize = Drive the data with multiple data sets
	@DataProvider
	public Object[][] getData() {
		return new Object[][] { { "hemanth@gmail.com", "Hemanth@18", "ADIDAS ORIGINAL", },
				{ "hemanths@gmail.com", "Hemanth@123", "ZARA COAT 3" } }; // the data type objects that allows the any
																			// kind of data types

	}

	// Data sets has more multiplre data sets - useing HashMap()

	@DataProvider
	public Object[][] getDataSet() {
		// using HashMap
		HashMap<String, String> map = new HashMap<String, String>();
		// set - 1 the key value pair
		map.put("email", "hemanth@gmail.com");
		map.put("password", "Hemanth@18");
		map.put("productName", "ADIDAS ORIGINAL");

		HashMap<String, String> map1 = new HashMap<String, String>();
		// set - 2 the key value pair
		map1.put("email", "hemanths@gmail.com");
		map1.put("password", "Hemanth@123");
		map1.put("productName", "ZARA COAT 3");

		return new Object[][] { { map }, { map1 } };
	}

	// import the data from extranal file - JSON file
	// JSON file into HashMap
	@DataProvider
	public Object[][] getDataHashMap() throws IOException {
		List<HashMap<String, String>> data = getJsonToHashMap(
				System.getProperty("user.dir") + "//src//test//java//e_CommereceApp//Data//purchase.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

}
