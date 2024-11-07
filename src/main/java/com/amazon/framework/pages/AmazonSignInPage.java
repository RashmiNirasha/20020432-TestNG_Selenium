package com.amazon.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AmazonSignInPage extends BasePage {

    private final By email = By.xpath("//input[@type='email']");
    private final By continueButton = By.xpath("//input[@id='continue']");
    private final By password = By.xpath("//input[@type='password']");
    private final By signIn = By.xpath("//input[@id='signInSubmit']");

    public AmazonSignInPage(WebDriver driver) {
        super(driver);
    }

    public void enterEmail(String email) {
        wait.until(driver -> driver.findElement(this.email).isDisplayed());
        driver.findElement(this.email).click();
        driver.findElement(this.email).sendKeys(email);
        wait.until(driver -> driver.findElement(continueButton).isDisplayed());
    }

    public void clickContinue() {
        if (driver.findElements(continueButton).isEmpty()) {
            return;
        }
        driver.findElement(continueButton).click();
        wait.until(driver -> driver.findElement(password).isDisplayed());
    }

    public void enterPassword(String password) {
        wait.until(driver -> driver.findElement(this.password).isDisplayed());
        driver.findElement(this.password).click();
        driver.findElement(this.password).sendKeys(password);
    }

    public void clickSignIn() {
        driver.findElement(signIn).click();
        wait.until(driver -> driver.getCurrentUrl().contains("https://www.amazon.com/"));
    }

    public boolean isSignInSuccessful() {
        return driver.getCurrentUrl().contains("https://www.amazon.com/");
    }

    public boolean isSignInUnsuccessful() {
        return driver.getCurrentUrl().contains("https://www.amazon.com/ap/signin");
    }
}