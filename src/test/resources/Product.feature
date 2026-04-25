Feature: Myntra Product Page Functionality

  # ❌ NEGATIVE (MAIN)
  Scenario: Verify user cannot add product without selecting size
    Given Browser is opened and product page is launched
    When user switches to product page tab
    And user clicks on Add to Bag without selecting size
    Then product should not be added to bag


  # ✅ POSITIVE SCENARIOS

  Scenario: Verify user can select size
    Given Browser is opened and product page is launched
    When user switches to product page tab
    And user selects size
    Then size should be selected successfully


  Scenario: Verify user can navigate to bag
    Given Browser is opened and product page is launched
    When user switches to product page tab
    And user selects size and adds product to bag
    And user clicks on go to bag
    Then user should be navigated to bag page


  # ❌ EDGE / NEGATIVE

  Scenario: Verify multiple clicks on Add to Bag without size
    Given Browser is opened and product page is launched
    When user switches to product page tab
    And user clicks on Add to Bag without selecting size
    And user clicks on Add to Bag without selecting size
    Then product should not be added to bag


  # ✅ UI VALIDATION

  Scenario: Verify Add to Bag button is visible
    Given Browser is opened and product page is launched
    When user switches to product page tab
    Then add to bag button should be visible

  Scenario: Verify size options are available
    Given Browser is opened and product page is launched
    When user switches to product page tab
    Then sizes should be available