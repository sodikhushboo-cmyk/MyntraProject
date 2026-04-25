Feature: Myntra Login Functionality

  # ✅ BASIC VALIDATION

  Scenario: Verify login page is displayed
    Given Browser is opened and login page is launched
    Then user should be redirected to login page

  Scenario: Verify login popup is displayed
    Given Browser is opened and login page is launched
    Then user should see login popup


  # ✅ FIELD VALIDATIONS

  Scenario: Verify mobile number input field is visible
    Given Browser is opened and login page is launched
    Then mobile number input field should be visible

  Scenario: Verify continue button is visible
    Given Browser is opened and login page is launched
    Then continue button should be visible



  # ✅ USER INTERACTION

  Scenario: Verify user can enter mobile number
    Given Browser is opened and login page is launched
    When user enters valid mobile number
    Then mobile number should be entered successfully

  Scenario: Verify user can click continue button after entering number
    Given Browser is opened and login page is launched
    When user enters valid mobile number
    And user clicks on continue button
    Then OTP screen should be displayed


  # ✅ UI BEHAVIOR

  Scenario: Verify login popup closes when user clicks outside
    Given Browser is opened and login page is launched
    When user clicks outside login popup
    Then login popup should be closed

  Scenario: Verify login popup persists on page refresh
    Given Browser is opened and login page is launched
    When user refreshes the page
    Then login popup should still be displayed