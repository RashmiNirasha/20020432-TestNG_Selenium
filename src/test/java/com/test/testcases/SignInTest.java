package com.test.testcases;

import com.amazon.framework.pages.AmazonHomePage;
import com.amazon.framework.pages.AmazonSignInPage;
import com.amazon.framework.tests.BaseTest;
import com.amazon.framework.utils.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

public class SignInTest extends BaseTest {

    @DataProvider(name = "signInData")
    public Object[][] getSignInData() {
        ExcelReader reader = new ExcelReader("src/main/resources/testData.xlsx");
        Map<String, String> validData = reader.getRowData("SignInData", "ValidLogin");
        Map<String, String> invalidData = reader.getRowData("SignInData", "InvalidLogin");
        reader.close();

        return new Object[][] {
                { validData.get("Username"), validData.get("Password"), true },
                { invalidData.get("Username"), invalidData.get("Password"), false }
        };
    }

    @Test(dataProvider = "signInData")
    public void goToSignInPage(String username, String password, boolean isSignInSuccessful) {
        AmazonHomePage amazonHomePage = new AmazonHomePage(browserFactory.getDriver());
        AmazonSignInPage amazonSignInPage = new AmazonSignInPage(browserFactory.getDriver());

        if (amazonHomePage.isUserSignedIn()) {
            amazonHomePage.clickSignOut();
            amazonHomePage.saveCookiesAfterSignOut();
            Assert.assertTrue(amazonHomePage.isSignOutSuccessful(), "Sign Out was not successful.");
        }

        amazonHomePage.clickSignIn();
        Assert.assertTrue(amazonHomePage.isSignInPageDisplayed(), "Sign In page was not displayed.");

        amazonSignInPage.enterEmail(username);
        amazonSignInPage.clickContinue();
        amazonSignInPage.enterPassword(password);
        amazonSignInPage.clickSignIn();
        amazonHomePage.saveCookiesAfterSignIn();

        if (isSignInSuccessful) {
            Assert.assertTrue(amazonSignInPage.isSignInSuccessful(), "Sign In was not successful.");
        } else {
            Assert.assertTrue(amazonSignInPage.isSignInUnsuccessful(), "Sign In was successful but expected to fail.");
        }
    }
}
