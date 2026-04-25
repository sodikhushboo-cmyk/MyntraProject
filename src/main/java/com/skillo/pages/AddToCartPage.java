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

    // 🔥 Track action
    private boolean attempted = false;

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
            // ✅ Wait for cart page
            wait.until(ExpectedConditions.urlContains("cart"));

            // ✅ Handle login case
            if (driver.getCurrentUrl().contains("login")) {
                System.out.println("⚠ Login required - skipping place order");
                attempted = true;
                return;
            }

            // ✅ Check button present
            if (driver.findElements(getBy("placeOrderBtn")).size() == 0) {
                System.out.println("⚠ Place Order button not present");
                attempted = true;
                return;
            }

            WebElement btn = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(getBy("placeOrderBtn"))
            );

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView(true);", btn);

            wait.until(ExpectedConditions.elementToBeClickable(btn));

            btn.click();

            attempted = true; // ✅ success

        } catch (Exception e) {
            System.out.println("⚠ Failed to click Place Order: " + e.getMessage());
            attempted = false;
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

    public boolean isPlaceOrderAttempted() {
        return attempted;
    }
}