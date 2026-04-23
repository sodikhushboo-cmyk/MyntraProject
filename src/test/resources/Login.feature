Feature: Myntra Login Functionality

Scenario: Verify login page is displayed
  Given Browser is opened and login page is launched
  Then user should be redirected to login page

Scenario: Verify login popup is displayed
  Given Browser is opened and login page is launched
  Then user should see login popup