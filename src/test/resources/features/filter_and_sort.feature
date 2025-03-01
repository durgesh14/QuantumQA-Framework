Feature: Filter and Sort Products on SauceDemo

  Scenario: Filter products by price and verify sorting
    Given I am logged in to SauceDemo with username "standard_user" and password "secret_sauce"
    When I filter products by price "Price (low to high)"
    Then the products should be sorted by price in ascending order
    When I add the first product to the cart
    And I verify the cart contains the cheapest product
    And I remove the first product from the cart
    Then the cart should be empty
    And I logout from the application