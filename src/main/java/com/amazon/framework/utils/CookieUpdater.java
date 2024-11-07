package com.amazon.framework.utils;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.json.Json;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class CookieUpdater {
    public void saveCookiesToFile(WebDriver driver) {
        Set<Cookie> cookies = driver.manage().getCookies();
        try (FileWriter writer = new FileWriter("src/main/resources/cookies.json")) {
            String json = new Json().toJson(cookies);
            writer.write(json);
            System.out.println("Cookies saved to cookies.json in logged-out state.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}