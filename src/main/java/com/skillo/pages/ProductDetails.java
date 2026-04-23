package com.skillo.pages;

import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.skillo.base.Keyword;
import com.skillo.utils.PropUtil;
import com.skillo.utils.Waits;

public class ProductDetails {

    private final WebDriver driver = Keyword.getDriver();
    private final PropUtil locator = new PropUtil("./src/main/resources/OR.properties");

    private boolean added = false;

    public ProductDetails() {
        PageFactory.initElements(driver, this);
    }

    // ===== LOCATORS =====

    @FindBy(xpath = "//div[contains(@class,'size-buttons')]//button[not(@disabled)]")
    private List<WebElement> availableSizes;

    @FindBy(xpath = "//div[contains(text(),'ADD TO BAG')]")
    private WebElement addToBagBtn;

    // ===== ACTIONS =====

    public void switchToProductTab() {

        String parent = driver.getWindowHandle();

        for (String window : driver.getWindowHandles()) {
            if (!window.equals(parent)) {
                driver.switchTo().window(window);
                break;
            }
        }

        Waits.waitForPageToLoad();
    }

    public void selectSizeAndAddToBag() {

        Waits.waitForPageToLoad();

        try {
            // ✅ Select size
            if (!availableSizes.isEmpty()) {
                WebElement size = availableSizes.get(0);
                Waits.safeClick(size); // BEST WAY
            }

            // ✅ Add to bag
            Waits.safeClick(addToBagBtn);

            added = true;

        } catch (Exception e) {
            added = false;
            throw e;
        }
    }

    public void addToBagWithoutSize() {

        try {
            Waits.safeClick(addToBagBtn);
            added = false;
        } catch (Exception e) {
            added = false;
            throw e;
        }
    }

    public void goToBag() {

        try {
            By goToBag = By.xpath(locator.get("goToBagPopupBtn"));

            Waits.safeClick(goToBag);

        } catch (Exception e) {
            throw new RuntimeException("Go To Bag button not found", e);
        }
    }

    // ===== REUSABLE =====

    public void clickaddToBagProduct() {

        Waits.waitForPageToLoad();
        Waits.safeClick(addToBagBtn);   // 🔥 FIXED
        added = true;
    }

    // ===== VALIDATIONS =====

    public boolean isGotoBagIsVisible() {

        try {
            return driver.findElements(By.xpath(locator.get("goToBagPopupBtn"))).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isProductAdded() {
        return added;
    }

    public boolean isProductNotAdded() {
        return !added;
    }

    public String getProductName() {
        return "Product"; // can improve later
    }
}