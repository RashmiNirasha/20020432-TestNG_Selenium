package com.test.testcases;

import com.amazon.framework.pages.AmazonHomePage; // Updated to the correct page object class for eBay
import com.amazon.framework.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @Test
    public void searchTest() {
        AmazonHomePage amazonHomePage = new AmazonHomePage(browserFactory.getDriver());
        amazonHomePage.typeTextToSearchBox("samsung galaxy s10");
        amazonHomePage.NotificationCheck();
        Assert.assertTrue(amazonHomePage.isSearchResultDisplayed("samsung galaxy s10"), "Search result was not displayed.");
    }
}
