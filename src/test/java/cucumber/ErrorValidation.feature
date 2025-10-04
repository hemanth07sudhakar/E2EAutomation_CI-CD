@tag
Feature: Error Validation 

@ErrorTest
Scenario Outline: check the login credetials

Given I landed on loginPage
When Logged in username <userName> and password <password>
Then "Incorrect email or password." message is displayed

Examples:
	| userName 			| password 	 | 
	|hemanth@gmail.com	| Hemanth@1812 | 