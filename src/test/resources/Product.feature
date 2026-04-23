Feature: Myntra Product Page Functionality

  # ❌ EXISTING NEGATIVE
  Scenario: Verify user cannot add product without selecting size
    Given Browser is opened and product page is launched
    When user switches to product page tab
    And user clicks on Add to Bag without selecting size
    Then product should not be added to bag


  # ✅ NEW POSITIVE SCENARIOS

  #Scenario: Verify user can open product page
    #Given Browser is opened and product page is launched
    #When user switches to product page tab
    #Then product page should be displayed

  Scenario: Verify user can select size
    Given Browser is opened and product page is launched
    When user switches to product page tab
    And user selects size
    Then size should be selected successfully

  Scenario: Verify user can click Add to Bag
    Given Browser is opened and product page is launched
    When user switches to product page tab
    And user selects size
    And user clicks Add to Bag
    Then add to bag action should be triggered

  Scenario: Verify user can navigate to bag
    Given Browser is opened and product page is launched
    When user switches to product page tab
    And user selects size and adds product to bag
    And user clicks on go to bag
    Then user should be navigated to bag page


  # ❌ NEW NEGATIVE SCENARIOS

  Scenario: Verify Add to Bag without switching tab
    Given Browser is opened and product page is launched
    When user clicks on Add to Bag without selecting size
    Then product should not be added to bag

  Scenario: Verify behavior when no size selected
    Given Browser is opened and product page is launched
    When user switches to product page tab
    And user clicks on Add to Bag without selecting size
    Then product should not be added to bag

  Scenario: Verify multiple clicks on Add to Bag without size
    Given Browser is opened and product page is launched
    When user switches to product page tab
    And user clicks on Add to Bag without selecting size
    And user clicks on Add to Bag without selecting size
    Then product should not be added to bag

  Scenario: Verify Add to Bag button is clickable
    Given Browser is opened and product page is launched
    When user switches to product page tab
    Then add to bag button should be visible

  Scenario: Verify size options are available
    Given Browser is opened and product page is launched
    When user switches to product page tab
    Then sizes should be available