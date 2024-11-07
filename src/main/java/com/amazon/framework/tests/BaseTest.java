package com.amazon.framework.tests;

import com.amazon.framework.utils.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected BrowserFactory browserFactory;
    protected WebDriver driver;

    @BeforeMethod
    public void initialize_browser() {
        try {
            browserFactory = BrowserFactory.getBrowserFactory();
            driver = browserFactory.getDriver();

            if (driver == null) {
                throw new RuntimeException("Driver initialization failed.");
            }

            driver.get("https://www.amazon.com/");

            // Load cookies if needed
            browserFactory.loadCookies(driver);
            driver.navigate().refresh();  // Refresh to apply cookies if they were loaded
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Browser setup failed: " + e.getMessage());
        }
    }

    @AfterMethod(alwaysRun = true)
    public void close_browser() {
        try {
            browserFactory.removeDriver(); // Ensure driver is fully closed and reset
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to close the browser: " + e.getMessage());
        }
    }
}
