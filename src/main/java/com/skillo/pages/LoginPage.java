package com.skillo.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.skillo.base.Keyword;
import com.skillo.utils.Waits;

public class LoginPage {

    // ===== LOCATORS =====

    @FindBy(css = ".wishlistLogin-button")
    WebElement wishListLoginPopUp;

    @FindBy(xpath = "//div[contains(@class,'signInContainer')]")
    WebElement loginPage;

    @FindBy(xpath = "//input[@type='tel']")
    WebElement mobileInput;

    @FindBy(xpath = "//div[normalize-space()='CONTINUE']")
    WebElement continueBtn;

    @FindBy(xpath = "//div[contains(text(),'valid mobile')]")
    WebElement errorMessage;

    // ===== INITIALIZATION =====

    public LoginPage() {
        PageFactory.initElements(Keyword.getDriver(), this);
    }

    // ================= VALIDATIONS =================

    public boolean isLoginPageDisplayed() {
        try {
            Waits.waitForVisibility(loginPage);
            return loginPage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLoginPopUpDisplayed() {
        try {
            Waits.waitForVisibility(loginPage); // ensure page

            Thread.sleep(2000); // Myntra popup delay

            return wishListLoginPopUp.isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }

    public boolean isMobileInputVisible() {
        try {
            Waits.waitForVisibility(mobileInput);
            return mobileInput.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isContinueButtonVisible() {
        try {
            Waits.waitForVisibility(continueBtn);
            return continueBtn.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return Keyword.getDriver()
                    .getPageSource()
                    .toLowerCase()
                    .contains("valid mobile");
        } catch (Exception e) {
            return false;
        }
    }

    // ================= ACTIONS =================

    public void enterMobileNumber(String number) {
        Waits.waitForVisibility(mobileInput);
        mobileInput.clear();
        mobileInput.sendKeys(number);
    }

    public void clickContinue() {
        Waits.waitForVisibility(continueBtn);
        continueBtn.click();
    }

    public void clickOutsidePopup() {
        mobileInput.click();
        mobileInput.sendKeys(Keys.TAB);
    }

    // ================= COMBINED =================

    public void enterMobileAndContinue(String number) {
        enterMobileNumber(number);
        clickContinue();
    }

    // ================= EXTRA VALIDATION =================

    public boolean isMobileNumberEntered() {
        try {
            String value = mobileInput.getAttribute("value");
            return value != null && !value.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isOtpScreenDisplayed() {
        try {
            return Keyword.getDriver().getCurrentUrl().contains("login")
                    && mobileInput.getAttribute("value") != null;
        } catch (Exception e) {
            return false;
        }
    }
}