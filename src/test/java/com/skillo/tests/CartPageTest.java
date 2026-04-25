package com.skillo.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.skillo.base.BaseClass;
import com.skillo.base.Keyword;
import com.skillo.pages.AddToCartPage;
import com.skillo.pages.HomePage;
import com.skillo.pages.ProductDetails;
import com.skillo.pages.ProductListingPage;

public class CartPageTest extends BaseClass {

	private HomePage home;
	private ProductListingPage plp;
	private ProductDetails pdp;
	private AddToCartPage cart;

	private static final String PRODUCT = "t-shirt";

	@BeforeMethod
	public void setupPages() {
		home = new HomePage();
		plp = new ProductListingPage();
		pdp = new ProductDetails();
		cart = new AddToCartPage();
	}

	// 🔁 COMMON FLOW → reach cart page
	private void navigateToCartPage() {

		home.enterTextOnSearchBar(PRODUCT);
		home.enterPressOnSearchBar();

		plp.clickProduct(0);
		plp.switchToChildWindow();

		pdp.selectSize();
		pdp.clickaddToBagProduct();
		pdp.goToBag();
	}

	// ================= CORE =================

	/*
	 * @Test(priority = 1, groups = "smoke") public void verifyPlaceOrder() {
	 * 
	 * navigateToCartPage(); cart.clickPlaceOrder();
	 * 
	 * Assert.assertTrue(cart.isOrderPlaced(), "❌ Order not placed"); }
	 */

	// ================= EDGE =================

	/*
	 * @Test(priority = 2, groups = "regression") public void
	 * verifyMultiplePlaceOrderClicks() {
	 * 
	 * navigateToCartPage();
	 * 
	 * cart.clickPlaceOrder(); cart.clickPlaceOrder();
	 * 
	 * Assert.assertTrue(cart.isOrderPlaced(), "❌ Multiple click handling failed");
	 * }
	 */

	// ================= PAGE VALIDATION =================

	@Test(priority = 3, groups = "smoke")
	public void verifyCartPageLoaded() {

		navigateToCartPage();

		Assert.assertTrue(cart.isCartPageDisplayed(), "❌ Cart page not loaded");
	}

	/*
	 * @Test(priority = 4, groups = "smoke") public void
	 * verifyPlaceOrderButtonClickable() {
	 * 
	 * navigateToCartPage();
	 * 
	 * Assert.assertTrue(cart.isPlaceOrderButtonVisible(),
	 * "❌ Place Order button not visible"); }
	 */

	// ================= REFRESH =================

	/*
	 * @Test(priority = 5, groups = "regression") public void
	 * verifyRefreshAndPlaceOrder() {
	 * 
	 * navigateToCartPage();
	 * 
	 * Keyword.getDriver().navigate().refresh(); cart.clickPlaceOrder();
	 * 
	 * Assert.assertTrue(cart.isOrderPlaced(), "❌ Order failed after refresh"); }
	 * 
	 * @Test(priority = 6, groups = "regression") public void
	 * verifyMultipleRefresh() {
	 * 
	 * navigateToCartPage();
	 * 
	 * Keyword.getDriver().navigate().refresh();
	 * Keyword.getDriver().navigate().refresh();
	 * 
	 * Assert.assertTrue(cart.isCartPageDisplayed(),
	 * "❌ Cart page broken after multiple refresh"); }
	 */

	// ================= NAVIGATION =================

	
	/*
	 * @Test(priority = 7, groups = "regression") public void
	 * verifyNavigationAndPlaceOrder() {
	 * 
	 * navigateToCartPage();
	 * 
	 * Keyword.getDriver().navigate().back();
	 * Keyword.getDriver().navigate().forward();
	 * 
	 * cart.clickPlaceOrder();
	 * 
	 * Assert.assertTrue(cart.isOrderPlaced(), "❌ Order failed after navigation");
	 * 
	 * }
	 */
	 
	 /** @Test(priority = 8, groups = "regression") public void
	 * verifyMultipleNavigation() {
	 * 
	 * navigateToCartPage();
	 * 
	 * Keyword.getDriver().navigate().back();
	 * Keyword.getDriver().navigate().forward();
	 * Keyword.getDriver().navigate().back();
	 * Keyword.getDriver().navigate().forward();
	 * 
	 * cart.clickPlaceOrder();
	 * 
	 * Assert.assertTrue(cart.isOrderPlaced(),
	 * "❌ Order failed after multiple navigation"); }
	 */
	  
	@Test(priority = 9, groups = "regression")
	public void verifyOnlyNavigation() {

		navigateToCartPage();

		Keyword.getDriver().navigate().back();
		Keyword.getDriver().navigate().forward();

		Assert.assertTrue(cart.isCartPageDisplayed(), "❌ Cart page unstable after navigation");
	}

	@Test(priority = 10, groups = "regression")
	public void verifyOnlyRefresh() {

		navigateToCartPage();

		Keyword.getDriver().navigate().refresh();

		Assert.assertTrue(cart.isCartPageDisplayed(), "❌ Cart page unstable after refresh");
	}
}