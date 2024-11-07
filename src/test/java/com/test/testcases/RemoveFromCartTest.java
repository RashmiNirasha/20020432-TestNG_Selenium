package com.test.testcases;

import com.amazon.framework.pages.AmazonHomePage;
import com.amazon.framework.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RemoveFromCartTest extends BaseTest {

    @Test
    public void removeFromCartTest() {
        AmazonHomePage amazonHomePage = new AmazonHomePage(browserFactory.getDriver());
        amazonHomePage.typeTextToSearchBox("samsung galaxy s10");
        amazonHomePage.NotificationCheck();
        amazonHomePage.clickSearchButton();
        amazonHomePage.clickFirstProduct();
        amazonHomePage.clickAddToCart();
        amazonHomePage.clickCart();
        amazonHomePage.clickDelete();
        Assert.assertTrue(amazonHomePage.isRemoveFromCartSuccessful(), "Remove from cart was not successful.");
    }
}