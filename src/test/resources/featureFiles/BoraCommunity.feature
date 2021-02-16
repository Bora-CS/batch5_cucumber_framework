@ui
Feature: Bora Community

  Scenario: Verify create profile page
    Given I'm on the Bora Community homepage
    When I click on login button
    And I log in with email "murad@test.com" and password "murad001"
    And I click on edit profile link
    Then The edit profile page should display

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

      
