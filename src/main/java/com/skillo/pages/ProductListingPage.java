package com.skillo.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.skillo.base.Keyword;
import com.skillo.utils.Waits;

public class ProductListingPage {

    private static final Logger LOG = LogManager.getLogger(ProductListingPage.class);

    private final WebDriver driver = Keyword.getDriver();

    public ProductListingPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//li[@class='product-base']")
    private List<WebElement> products;

    public void openFirstProduct() {

        Waits.waitForPageToLoad(); // add this

        List<WebElement> products = new WebDriverWait(Keyword.getDriver(), Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//li[@class='product-base']")   // ✅ updated locator
                ));

        products.get(0).click();
    }

    // 🔥 NEW METHODS
    public void clickProduct(int index) {
        products.get(index).click();
    }

    public void switchToChildWindow() {
        String parent = driver.getWindowHandle();

        for (String window : driver.getWindowHandles()) {
            if (!window.equals(parent)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }

    public String getProductBrand(int index) {
        return products.get(index).getText();
    }

    public int getProductPrice(int index) {
        return 1000; // placeholder
    }
}