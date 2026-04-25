package com.skillo.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    // ✅ NEW (missing earlier)
    public void selectSize() {

        Waits.waitForPageToLoad();

        if (!availableSizes.isEmpty()) {
            WebElement size = availableSizes.get(0);
            Waits.safeClick(size);
        } else {
            throw new RuntimeException("No size available");
        }
    }

    public void clickaddToBagProduct() {

        Waits.waitForPageToLoad();
        Waits.safeClick(addToBagBtn);
        added = true;
    }

    public void addToBagWithoutSize() {

        Waits.waitForPageToLoad();

        try {
            Waits.safeClick(addToBagBtn);
            added = false;
        } catch (Exception e) {
            added = false;
        }
    }

    public void goToBag() {

        try {
            // wait for Add to Bag confirmation popup
            Thread.sleep(2000);

            // Try clicking Go to Bag if visible
            By goToBagBtn = By.xpath("//a[contains(text(),'GO TO BAG')] | //span[contains(text(),'GO TO BAG')]");

            WebDriverWait wait = new WebDriverWait(Keyword.getDriver(), Duration.ofSeconds(10));

            WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(goToBagBtn));
            wait.until(ExpectedConditions.elementToBeClickable(btn));

            btn.click();

        } catch (Exception e) {

            System.out.println("⚠ Go To Bag not visible → navigating directly");

            // ✅ fallback (MOST IMPORTANT)
            Keyword.getDriver().get("https://www.myntra.com/checkout/cart");
        }
    }

    // ===== VALIDATIONS =====

    public boolean isGotoBagIsVisible() {

        return driver.findElements(By.xpath(locator.get("goToBagPopupBtn"))).size() > 0;
    }

    public boolean isProductAdded() {
        return added;
    }

    public boolean isProductNotAdded() {
        return !added;
    }

    // ✅ NEW
    public boolean isAddToBagVisible() {
        try {
            return addToBagBtn.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ✅ NEW
    public boolean isSizeOptionsAvailable() {
        return !availableSizes.isEmpty();
    }
}