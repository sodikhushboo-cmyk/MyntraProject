/*
 * package com.example.demo;
 * 
 * import org.testng.annotations.Test;
 * 
 * import com.skillo.base.Keyword; import com.skillo.pages.MyntraCartPage;
 * import com.skillo.pages.MyntraHomePage; import
 * com.skillo.pages.MyntraProductPage;
 * 
 * public class MyntraLoginTest {
 * 
 * @Test public void completeMyntraFlow() throws Exception {
 * 
 * // ✅ STEP 1: OPEN BROWSER (MANDATORY) Keyword.openBrowser("chrome");
 * 
 * // ✅ STEP 2: LAUNCH URL Keyword.launchUrl("https://www.myntra.com/");
 * 
 * // Page Objects MyntraHomePage homePage = new MyntraHomePage();
 * MyntraProductPage productPage = new MyntraProductPage(); MyntraCartPage
 * cartPage = new MyntraCartPage();
 * 
 * // ❌ REMOVE THIS (not needed and slows execution) // Thread.sleep(25000);
 * 
 * // Flow homePage.navigateToTopwear(); homePage.applyFilters();
 * homePage.openFirstProduct();
 * 
 * productPage.switchToProductTab(); productPage.selectSizeAndAddToBag();
 * productPage.goToBag();
 * 
 * cartPage.placeOrder();
 * 
 * // ✅ STEP 3: CLOSE DRIVER Keyword.getDriver().quit(); Keyword.unload(); } }
 */