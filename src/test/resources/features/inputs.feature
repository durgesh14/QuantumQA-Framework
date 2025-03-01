Feature: Inputs Field Interaction

  Scenario: Interact with the Inputs field
    Given I am on the-internet herokuapp homepage
    When I click on the Inputs link
    And I interact with the number field using random values
    And I interact with the number field using increment arrow
    And I interact with the number field using decrement arrow
    Then the inputs field should behave as expected