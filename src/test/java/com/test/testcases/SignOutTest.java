package com.test.testcases;

import com.amazon.framework.pages.AmazonHomePage; // Updated to the correct page object class for amazon
import com.amazon.framework.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignOutTest extends BaseTest {

    @Test
    public void signOutTest() {
        AmazonHomePage amazonHomePage = new AmazonHomePage(browserFactory.getDriver());
        amazonHomePage.clickSignOut();
        amazonHomePage.saveCookiesAfterSignIn();
        Assert.assertTrue(amazonHomePage.isSignOutSuccessful(), "Sign Out was not successful.");
    }
}