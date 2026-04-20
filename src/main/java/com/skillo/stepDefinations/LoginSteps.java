package com.skillo.stepDefinations;

import static com.skillo.base.Keyword.launchUrl;
import static com.skillo.base.Keyword.openBrowser;

import org.testng.Assert;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.skillo.pages.MyntraLoginPage;
import com.skillo.utils.App;

import io.cucumber.java.en.*;

public class LoginSteps {

    private static final Logger LOG = LogManager.getLogger(LoginSteps.class);

    private MyntraLoginPage loginPage;

    // Setup Step
    @Given("Browser is opened and login page is launched")
    public void openBrowserAndLaunchUrl() {

        LOG.info("STEP: Opening browser and launching login page");

        try {
            String browser = App.browser();
            String url = App.url();

            LOG.info("Opening browser: {}", browser);
            openBrowser(browser);

            LOG.info("Launching URL: {}", url);
            launchUrl(url);

            loginPage = new MyntraLoginPage();

            LOG.info("Login page initialized successfully");

        } catch (Exception e) {
            LOG.error("Failed to open browser or launch URL", e);
            throw e;
        }
    }

    // Common Step
    @When("user enters mobile number {string}")
    public void userEntersMobileNumber(String mobileNumber) {

        LOG.info("STEP: User enters mobile number");

        try {
            if (mobileNumber == null || mobileNumber.trim().isEmpty()) {

                LOG.warn("No mobile number provided");
                loginPage.login("");

            } else {

                loginPage.login(mobileNumber);
                LOG.info("Mobile number entered: {}", maskMobile(mobileNumber));
            }

        } catch (Exception e) {
            LOG.error("Error while entering mobile number", e);
            throw e;
        }
    }

    // Negative Validation
    @Then("check if the error message appears")
    public void checkIfErrorMessageAppears() {

        LOG.info("STEP: Validating error message presence");

        try {
            boolean isErrorDisplayed = loginPage.isErrorMessageDisplayed();
            LOG.debug("Error message displayed: {}", isErrorDisplayed);

            Assert.assertTrue(isErrorDisplayed, "Error message NOT displayed");

            LOG.info("Error message validation passed");

        } catch (AssertionError e) {
            LOG.error("Error message validation failed", e);
            throw e;
        }
    }

    // Positive Validation
    @Then("user should be redirected to login page")
    public void userShouldBeRedirectedToLoginPage() {

        LOG.info("STEP: Validating login redirection");

        try {
            boolean isLoginSuccessful = loginPage.isLoginSuccessful();
            LOG.debug("Login success status: {}", isLoginSuccessful);

            Assert.assertTrue(isLoginSuccessful, "Login flow failed");

            LOG.info("Login flow validation passed");

        } catch (AssertionError e) {
            LOG.error("Login validation failed", e);
            throw e;
        }
    }

    // Length Validation
    @Then("mobile number should be trimmed to 10 digits")
    public void mobileNumberShouldBeTrimmedTo10Digits() {

        LOG.info("STEP: Validating mobile number length");

        try {
            String value = loginPage.getEnteredMobileNumber();
            LOG.debug("Actual entered value: {}", value);

            Assert.assertEquals(value.length(), 10,
                    "Mobile number is not restricted to 10 digits");

            LOG.info("Mobile number length validation passed");

        } catch (AssertionError e) {
            LOG.error("Mobile number length validation failed", e);
            throw e;
        }
    }

    // Utility: mask mobile number
    private String maskMobile(String mobile) {
        if (mobile == null || mobile.length() < 4) return "****";
        return "******" + mobile.substring(mobile.length() - 4);
    }
}