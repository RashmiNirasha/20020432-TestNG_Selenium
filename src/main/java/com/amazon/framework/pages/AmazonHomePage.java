package com.amazon.framework.pages;

import com.amazon.framework.utils.CookieUpdater;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class AmazonHomePage extends BasePage {

    private final By searchBox = By.xpath("//input[@id='twotabsearchtextbox']");
    private final By searchButton = By.xpath("//input[@value='Go']");
    private final By shipTo = By.xpath("//a[@id='nav-global-location-popover-link']");
    private final By countryDropdown = By.xpath("//span[@data-action='a-dropdown-button']");
    private final By countryPopup = By.xpath("//div[@id='GLUXCountryList']");
    private final By selectCountry = By.xpath("//a[@id='GLUXCountryList_210']");
    private final By shipToDone = By.xpath("//button[@name='glowDoneButton']");
    private final By signIn = By.xpath("//a[@data-nav-role='signin']");
    private final By signOut = By.xpath("//a[@id='nav-item-signout']");
    private final By accountMenu = By.xpath("//span[@id='nav-link-accountList-nav-line-1']");
    private final By search_result = By.xpath("//span[@class=\"a-color-state a-text-bold\"]");
    private final By firstProduct = By.xpath("//span[@class='a-size-base-plus a-color-base a-text-normal']");
    private final By addToCart = By.xpath("//input[@id='add-to-cart-button']");
    private final By cart = By.xpath("//a[@id='nav-cart']");
    private final By delete = By.xpath("//input[@value='Delete']");
    private final By removedFromCart = By.xpath("//div[@class='a-row sc-your-amazon-cart-is-empty']");

    public AmazonHomePage(WebDriver driver) {
        super(driver);
    }

    public void typeTextToSearchBox(String text) {
        driver.findElement(searchBox).sendKeys(text);
        driver.findElement(searchButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(search_result));
    }

    public void NotificationCheck(){
        wait.until(ExpectedConditions.presenceOfElementLocated(search_result));
        String Expected = "samsung galaxy s10";
        String Actual = driver.findElement(search_result).getText();
        Assert.assertTrue(Actual.contains(Expected));
    }

    public void clickSearchButton() {
        driver.findElement(searchButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(search_result));
    }

    public void clickFirstProduct() {
        driver.findElement(firstProduct).click();
    }

    public void clickAddToCart() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(addToCart));
        driver.findElement(addToCart).click();
    }

    public boolean isAddToCartSuccessful() {
        return driver.findElements(addToCart).isEmpty();
    }

    public boolean isSearchResultDisplayed(String keyword) {
        return driver.findElements(search_result).stream()
                .anyMatch(element -> element.getText().contains(keyword));
    }

    public void clickShipTo() {
        driver.findElement(shipTo).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(countryDropdown));
    }

    public void selectCountry() {
        driver.findElement(countryDropdown).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(selectCountry));
        driver.findElement(selectCountry).click();
    }

    public void closeShipTo() {
        driver.findElement(shipToDone).click();
    }

    public boolean isShipToPopupDisplayed() {
        return !driver.findElements(countryPopup).isEmpty();
    }

    public void goToMainPage() {
        driver.get("https://www.amazon.com");
    }

    public void clickSignIn() {
        goToMainPage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(signIn));
        driver.findElement(signIn).click();
    }

    public void clickSignOut() {
        WebElement accountElement = driver.findElement(signIn);
        actions.moveToElement(accountElement).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(signOut));
        WebElement signOutElement = driver.findElement(signOut);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", signOutElement);
        wait.until(ExpectedConditions.urlContains("signin"));
    }

    public boolean isSignOutSuccessful() {
        return driver.getCurrentUrl().contains("https://www.amazon.com/") &&
                driver.getCurrentUrl().contains("signin");
    }

    public void clickCart() {
        driver.findElement(cart).click();
    }

    public void clickDelete() {
        driver.findElement(delete).click();
    }

    public boolean isRemoveFromCartSuccessful() {
        return !driver.findElements(removedFromCart).isEmpty();
    }

    public boolean isUserSignedIn() {
        return !driver.findElements(signOut).isEmpty();
    }

    public void saveCookiesAfterSignIn() {
        CookieUpdater cookieUpdater = new CookieUpdater();
        cookieUpdater.saveCookiesToFile(driver);
    }

    public void saveCookiesAfterSignOut() {
        CookieUpdater cookieUpdater = new CookieUpdater();
        cookieUpdater.saveCookiesToFile(driver);
    }

    public boolean isSignInPageDisplayed() {
        return driver.getCurrentUrl().contains("signin");
    }
}
