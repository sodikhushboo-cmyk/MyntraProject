package com.skillo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;


import com.skillo.pages.LoginPage;
import com.skillo.base.*;

public class LoginPageTest extends BaseClass {

    @Test
    public void verifyLoginPageVisible() {

        LoginPage login = new LoginPage();

        Assert.assertTrue(login.isLoginPageDisplayed(),
                "Login page not visible");
    }

    @Test
    public void verifyLoginPopupVisible() {

        LoginPage login = new LoginPage();

        Assert.assertTrue(login.isLoginPopUpDisplayed(),
                "Login popup not visible");
    }
}
