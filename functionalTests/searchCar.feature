Feature: Search Car 

	Scenario Outline: Verify Successful search of car with valid License Number
		Given User is on HomePage
		When User clicks on Search Car tab
		And User selects the country "IN"
		And User enters valid License Number "<licence number>" 
		And User clicks on Search button
		Then Verify car is searched successfully with valid License Number "<licence number>"
	Examples:
	| licence number |
	| AT18127 |
	| AT18128 |
	| AT18129 |
	
	Scenario Outline: Verify Successful search of car with valid Chasis Number
		Given User is on HomePage
		When User clicks on Search Car tab
		And User enters valid Chasis number "<Chasis number>" 
		And User clicks on Search button
		Then Verify car is searched successfully with valid Chasis Number "<Chasis number>"
	Examples:
	| Chasis number |
	| VF34J5FV8AP002586 |