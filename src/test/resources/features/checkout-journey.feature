@regression
Feature: User checkout journey

  @checkout
  Scenario Outline: Verify that a user should be able to complete the checkout journey
    Given the user logs in with valid credentials "<username>", "<password>"
    When user add high priced item to the cart
    Then user checks out the order "<firstName>", "<lastName>", "<zip>"
    And user logs out of the application
    Examples:
      | username      | password     | firstName | lastName | zip   |
      | standard_user | secret_sauce | Test   | User     | 12345 |
