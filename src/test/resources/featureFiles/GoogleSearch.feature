Feature: Google Search
	
	@ui
  Scenario Outline: 
    Given I'm on the google.com homepage
    When I search for "<itemToSearch>"
    Then I should be navigated to the search result page
    And I should see some results related to "<itemToSearch>"

    Examples: 
      | itemToSearch |
      | Shampoo      |
      | VaCcIn       |
      | puppy        |
