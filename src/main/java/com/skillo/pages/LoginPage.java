package com.skillo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.skillo.base.Keyword;
import com.skillo.utils.Waits;

public class LoginPage {

    // ===== LOCATORS =====

    @FindBy(css = ".wishlistLogin-button")
    WebElement wishListLoginPopUp;

    @FindBy(xpath = "//div[@class='signInContainer']")
    WebElement loginPage;

    // ===== INITIALIZATION =====

    public LoginPage() {
        PageFactory.initElements(Keyword.getDriver(), this);
    }

    // ===== VALIDATIONS =====

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
            Thread.sleep(2000); // TEMP FIX
            return wishListLoginPopUp.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
