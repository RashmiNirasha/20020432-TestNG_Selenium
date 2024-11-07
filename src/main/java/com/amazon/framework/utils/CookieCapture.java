package com.amazon.framework.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.IOException;

public class CookieCapture {

    public static void main(String[] args) {
        CookieUpdater cookieUpdater = new CookieUpdater();
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.amazon.com");
        System.out.println("Please log in manually, then press Enter to continue...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        cookieUpdater.saveCookiesToFile(driver);
        driver.quit();
    }
}