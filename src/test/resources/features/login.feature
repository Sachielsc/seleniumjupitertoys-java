Feature: Login

@login
Scenario: Valid login
	Given A valid user
    When User logs in to the application
    Then User should see the welcome message

@login
Scenario: Invalid login
	Given An invalid user
    When User logs in to the application
    Then User should see the incorrect login error message
