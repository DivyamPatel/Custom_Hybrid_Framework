Feature: Login/Logout Action

  Scenario Outline: Verify Successful login with valid credentials
    Given User is on Homepage
    When User clicks on SignIn tab 
    And User enters valid username "<username>" and valid password "<password>"
 	And User clicks on SignIn button
  	Then Verify user is logged into application
  	And Verify user is navigated to "Service planer" or "Digital-Test.com" page
  Examples:
  	| username | password |
  	| test@test.com | 123456789 |
  	| makemetek_abc@gmail.com | 123456789 |
  	
  Scenario Outline: Verify Unsuccessful login with invalid credentials
  	Given User is on Homepage
  	When User clicks on SignIn tab
  	And User enters invalid username "<username>" and invalid password "<password>"
 	And User clicks on SignIn button
  	Then Verify user is not logged into application
  Examples:
  	| username | password |
  	| maketek.abc@gmail.com | Digital123 |