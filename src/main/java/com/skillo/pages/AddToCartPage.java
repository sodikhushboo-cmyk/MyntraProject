package com.skillo.pages;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import com.skillo.base.Keyword;
import com.skillo.utils.PropUtil;

public class AddToCartPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private PropUtil locator;

    public AddToCartPage() {
        this.driver = Keyword.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.locator = new PropUtil("./src/main/resources/OR.properties");
    }

    private By getBy(String key) {
        return By.xpath(locator.get(key));
    }

    // ================= ACTION =================

    public void clickPlaceOrder() {

        try {
            // ✅ Wait for cart URL
            wait.until(ExpectedConditions.urlContains("cart"));

            // ✅ Handle login case
            if (driver.getCurrentUrl().contains("login")) {
                System.out.println("⚠ Login required - skipping place order");
                return;
            }

            // ✅ Find button safely
            if (driver.findElements(getBy("placeOrderBtn")).size() == 0) {
                System.out.println("⚠ Place Order button not present");
                return;
            }

            WebElement btn = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(getBy("placeOrderBtn"))
            );

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView(true);", btn);

            wait.until(ExpectedConditions.elementToBeClickable(btn));

            btn.click();

        } catch (Exception e) {
            System.out.println("⚠ Failed to click Place Order: " + e.getMessage());
        }
    }

    // ================= VALIDATIONS =================

    public boolean isOrderPlaced() {
        try {
            return driver.getCurrentUrl().contains("address")
                    || driver.getCurrentUrl().contains("payment");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCartPageDisplayed() {
        try {
            return driver.getCurrentUrl().contains("cart");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPlaceOrderButtonVisible() {
        try {
            return driver.findElements(getBy("placeOrderBtn")).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }
}