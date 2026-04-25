Feature: Myntra Home Page Functionality

  # ✅ POSITIVE SCENARIOS

  Scenario: Verify user can navigate to Topwear
    Given Browser is opened and home page is launched
    When user navigates to Topwear section
    Then Topwear page should be displayed

  Scenario: Verify user can apply filters
    Given Browser is opened and home page is launched
    When user navigates to Topwear section
    And user applies filters
    Then filtered products should be displayed

  Scenario: Verify user can open product
    Given Browser is opened and home page is launched
    When user navigates to Topwear section
    And user opens first product
    Then product page should be displayed

  Scenario: Verify user can navigate multiple times to Topwear
    Given Browser is opened and home page is launched
    When user navigates to Topwear section
    And user navigates to Topwear section
    Then Topwear page should be displayed

  Scenario: Verify products are visible after navigation
    Given Browser is opened and home page is launched
    When user navigates to Topwear section
    Then products should still be visible

  Scenario: Verify filter does not break navigation
    Given Browser is opened and home page is launched
    When user navigates to Topwear section
    And user applies filters
    And user navigates to Topwear section
    Then Topwear page should be displayed

  # ❌ NEGATIVE SCENARIO (KEPT ONLY UNIQUE ONE)

  Scenario: Verify repeated product click handling
    Given Browser is opened and home page is launched
    When user navigates to Topwear section
    And user opens first product
    And user opens first product
    Then product page should be displayed