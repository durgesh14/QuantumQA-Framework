Feature: SauceDemo E-commerce Workflow

  Scenario: Complete purchase workflow on SauceDemo
    Given I am logged in to SauceDemo with username "standard_user" and password "secret_sauce"
    When I add the following items to the cart:
      | Sauce Labs Backpack |
      | Sauce Labs Bike Light |
    And I proceed to checkout
    And I enter shipping information:
      | firstName | lastName | zipCode |
      | John       | Doe      | 12345   |
    And I complete the purchase
    Then I should see the order confirmation message "Thank you for your order!"