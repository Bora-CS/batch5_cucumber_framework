Feature: Bora APIs

  @api
  Scenario: Add Experience
    Given I'm logged in with email "murad@test.com" and password "murad001"
    When I send a requrst to add experience
      | current | title   | company      | location     | from       | to         | description                                  |
      | false   | Barista | Panera Bread | Falls Church | 2014-04-05 | 2015-09-10 | People liked my latte better than starbucks' |
    Then I should get updated profile with the new experience added
