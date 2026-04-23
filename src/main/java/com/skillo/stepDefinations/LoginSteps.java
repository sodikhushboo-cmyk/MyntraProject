package com.skillo.stepDefinations;

import org.testng.Assert;

import com.skillo.base.Keyword;
import com.skillo.pages.LoginPage;

import io.cucumber.java.en.*;

public class LoginSteps {

    LoginPage loginPage;

    @Given("Browser is opened and login page is launched")
    public void open_login_page() {

        Keyword.launchUrl("https://www.myntra.com/login");

        loginPage = new LoginPage();
    }

    @Then("user should be redirected to login page")
    public void verify_login_page() {

        Assert.assertTrue(loginPage.isLoginPageDisplayed());
    }

    @Then("user should see login popup")
    public void verify_login_popup() {

        Assert.assertTrue(loginPage.isLoginPopUpDisplayed());
    }
}