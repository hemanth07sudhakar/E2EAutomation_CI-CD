@tag
Feature:  Purchase the product from Ecommerce WebPage

Background:
Given I landed on loginPage

@RegressionTest
Scenario Outline: Positive test
Given Logged in username <userName> and password <password>
When Add the products <productName> to Cart
And Checkout the <productName> and click submit
Then "Thankyou for the order." Message is displayed on ConfirmPage

Examples:
	| userName 			| password 	 | productName 	   |
	|hemanth@gmail.com	| Hemanth@18 | ADIDAS ORIGINAL |