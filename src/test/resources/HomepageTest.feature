Feature: Men Search Functionality

# ✅ Positive Scenario
@SearchFunctionality
Scenario Outline: Verify search functionality with valid men products

  When user search for the Various "<product>"
  Then user should see the result for the valid product

Examples:
  | product        |
  | t-shirt        |
  | shirt          |
  | jeans          |
  | trousers       |
  | jacket         |
  | hoodie         |
  | blazer         |
  | kurta          |
  | shorts         |
  | track pants    |
  | sweatshirt     |
  | formal shoes   |
  | casual shoes   |
  | sports shoes   |
  | sandals        |
  | Belts          |
  | Helmets        |
  | Boxers         |


# ❌ Negative Scenario 1
Scenario: Verify search with special characters

  When user enters special character to search product "@#$%^&*()"
  Then user cannot find the men products


# ❌ Negative Scenario 2
@NumberInvalidProduct
Scenario: Verify search with numbers

  When user enters numbers to search products "48964596186"
  Then user cannot find any matches


# ❌ Negative Scenario 3
Scenario: Verify search with invalid text

  When user enters giberish text to search product "xyzabc99999nonsense"
  Then user cannot see men product 


# ❌ Negative Scenario 4
Scenario: Verify search suggestions

  When user enters some keyword to search "Shirt"
  Then user should see the autosuggestions


# ❌ Negative Scenario 5
Scenario: Verify wishlist without login

  When user click on wishlist icon without login 
  Then user should redirected on the login page 


# ❌ Negative Scenario 6
Scenario: Verify orders without login

  When user click on orders List without login
  Then user should redirected on the login page