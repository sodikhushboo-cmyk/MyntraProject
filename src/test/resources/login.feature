Feature: Myntra Login Functionality

  # ✅ POSITIVE SCENARIO
  
  Scenario: Verify login flow with valid mobile number
    Given Browser is opened and login page is launched
    When user enters mobile number "8480867155"
    Then user should be redirected to login page


  # ❌ NEGATIVE SCENARIOS (VALID ONES ONLY)
  
  Scenario: Verify error for less than 10 digits
    Given Browser is opened and login page is launched
    When user enters mobile number "12345"
    Then check if the error message appears
    
  
  Scenario: Verify error for alphabets in mobile number
    Given Browser is opened and login page is launched
    When user enters mobile number "abcde12345"
    Then check if the error message appears

  
  Scenario: Verify error for special characters
    Given Browser is opened and login page is launched
    When user enters mobile number "@#$%^&*()"
    Then check if the error message appears

  
  Scenario: Verify behavior when mobile number is empty
    Given Browser is opened and login page is launched
    When user enters mobile number ""
    Then check if the error message appears


  # ✅ SYSTEM BEHAVIOR SCENARIOS

  
  Scenario: Verify system accepts any valid 10 digit number
    Given Browser is opened and login page is launched
    When user enters mobile number "1234567890"
    Then user should be redirected to login page

  
  Scenario: Verify system restricts more than 10 digits
    Given Browser is opened and login page is launched
    When user enters mobile number "1234567890123"
    Then mobile number should be trimmed to 10 digits