Feature: Myntra Cart Functionality

  Scenario: Verify user is able to place order from cart
    Given User is on cart page
    When User clicks on place order button
    Then Order should be placed successfully

  # 🆕 NEW SCENARIOS

  Scenario: Verify user can click place order multiple times
    Given User is on cart page
    When User clicks on place order button
    And User clicks on place order button again
    Then Order should be placed successfully

  Scenario: Verify page loads before placing order
    Given User is on cart page
    Then cart page should be loaded

  Scenario: Verify place order button is clickable
    Given User is on cart page
    Then place order button should be clickable

  Scenario: Verify user refresh and place order
    Given User is on cart page
    When user refreshes the cart page
    And User clicks on place order button
    Then Order should be placed successfully

  Scenario: Verify repeated refresh does not break flow
    Given User is on cart page
    When user refreshes the cart page
    And user refreshes the cart page again
    Then cart page should be loaded

  Scenario: Verify user tries place order without refresh
    Given User is on cart page
    When User clicks on place order button
    Then Order should be placed successfully

  Scenario: Verify navigation back to cart and place order
    Given User is on cart page
    When user navigates again to cart page
    And User clicks on place order button
    Then Order should be placed successfully

  Scenario: Verify place order after multiple navigations
    Given User is on cart page
    When user navigates again to cart page
    And user navigates again to cart page
    And User clicks on place order button
    Then Order should be placed successfully

  Scenario: Verify user performs only navigation
    Given User is on cart page
    When user navigates again to cart page
    Then cart page should be loaded

  Scenario: Verify user performs only refresh
    Given User is on cart page
    When user refreshes the cart page
    Then cart page should be loaded