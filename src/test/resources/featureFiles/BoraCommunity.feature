@ui
Feature: Bora Community

  #Scenario: Log in happy path
  #Given I'm on the Bora Community homepage
  #When I click on login button
  #And I log in with email "murad@test.com" and password "murad001"
  #Then I should be on the dashboard page
  
  Scenario Outline: Log in error scenarios
    Given I'm on the Bora Community homepage
    When I click on login button
    And I log in with email "<email>" and password "<password>"
    Then I should still be on the login page
    And I will see an error message that says "Invalid credentials"

    Examples: 
      | email            | password   |
      | murad@test33.com | murad001   |
      | murad@test.com   | murad00333 |
