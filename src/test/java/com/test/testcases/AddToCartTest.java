package com.test.testcases;

import com.amazon.framework.pages.AmazonHomePage;
import com.amazon.framework.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddToCartTest extends BaseTest {

    @Test
    public void addToCartTest() {
        AmazonHomePage amazonHomePage = new AmazonHomePage(browserFactory.getDriver());
        amazonHomePage.typeTextToSearchBox("samsung galaxy s10");
        amazonHomePage.NotificationCheck();
        amazonHomePage.clickSearchButton();
        amazonHomePage.clickFirstProduct();
        amazonHomePage.clickAddToCart();
        Assert.assertTrue(amazonHomePage.isAddToCartSuccessful(), "Add to cart was not successful.");
    }
}