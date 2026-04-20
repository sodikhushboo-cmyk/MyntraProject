package com.skillo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.skillo.base.Keyword;
import com.skillo.utils.App;
import com.skillo.utils.Waits;

public class MyntraLoginPage {

    private static final Logger LOG = LogManager.getLogger(MyntraLoginPage.class);

    public MyntraLoginPage() {
        PageFactory.initElements(Keyword.getDriver(), this);
        LOG.info("MyntraLoginPage initialized");
    }

    @FindBy(xpath = "//input[@type='tel']")
    private WebElement mobileInput;

    @FindBy(xpath = "//input[@type='checkbox']")
    private WebElement termsCheckbox;

    @FindBy(xpath = "//div[text()='CONTINUE']")
    private WebElement continueBtn;

    // Error message locator
    private By errorLocator = By.xpath("//div[contains(@class,'error') or contains(text(),'Please')]");

    // LOGIN METHOD
    public void login(String mobile) {

        LOG.info("Starting login process");

        try {
            String url = App.loginUrl();
            LOG.info("Navigating to login URL: {}", url);

            Keyword.getDriver().get(url);
            Waits.waitForPageToLoad();

            LOG.debug("Entering mobile number");
            mobileInput.clear();
            mobileInput.sendKeys(mobile);

            // Optional masking for security
            LOG.info("Mobile number entered: {}", maskMobile(mobile));

            if (!termsCheckbox.isSelected()) {
                LOG.debug("Selecting terms & conditions checkbox");
                termsCheckbox.click();
            } else {
                LOG.debug("Checkbox already selected");
            }

            Waits.safeClick(continueBtn);
            LOG.info("Clicked on Continue button");

        } catch (Exception e) {
            LOG.error("Login action failed", e);
            throw e;
        }
    }

    // ERROR VALIDATION
    public boolean isErrorMessageDisplayed() {

        try {
            boolean result = Keyword.getDriver().findElements(errorLocator).size() > 0;
            LOG.debug("Error message displayed: {}", result);
            return result;

        } catch (Exception e) {
            LOG.error("Error while checking error message", e);
            return false;
        }
    }

    // SUCCESS VALIDATION
    public boolean isLoginSuccessful() {

        try {
            Waits.waitForPageToLoad();

            String currentUrl = Keyword.getDriver().getCurrentUrl();
            LOG.debug("Current URL after login: {}", currentUrl);

            boolean result = currentUrl.contains("login");

            LOG.info("Login success validation result: {}", result);
            return result;

        } catch (Exception e) {
            LOG.error("Error while validating login success", e);
            return false;
        }
    }

    // Get value
    public String getEnteredMobileNumber() {

        try {
            String value = mobileInput.getAttribute("value");
            LOG.debug("Retrieved entered mobile number");
            return value;

        } catch (Exception e) {
            LOG.error("Failed to fetch entered mobile number", e);
            return null;
        }
    }

    // Utility: mask mobile number (security best practice)
    private String maskMobile(String mobile) {
        if (mobile == null || mobile.length() < 4) return "****";
        return "******" + mobile.substring(mobile.length() - 4);
    }
}