package com.test.testcases;

import com.amazon.framework.pages.AmazonHomePage;
import com.amazon.framework.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ShipToTest extends BaseTest {

    @Test
    public void shipToTest() {
        AmazonHomePage amazonHomePage = new AmazonHomePage(browserFactory.getDriver());
        amazonHomePage.clickShipTo();
        amazonHomePage.selectCountry();
        amazonHomePage.closeShipTo();
        Assert.assertFalse(amazonHomePage.isShipToPopupDisplayed(), "Ship To popup was not closed.");
    }
}
