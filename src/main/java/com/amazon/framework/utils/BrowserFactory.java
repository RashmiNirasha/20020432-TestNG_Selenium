package com.amazon.framework.utils;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.json.Json;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class BrowserFactory {
    private static BrowserFactory browserFactory;
    private ThreadLocal<WebDriver> threadLocal = ThreadLocal.withInitial(() -> {
        WebDriver driver = null;
        String browserType = System.getProperty("browser", "chrome");
        switch (browserType) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("The browser is not defined.");
        }
        driver.manage().window().maximize();
        return driver;
    });

    private BrowserFactory() {
    }

    public static BrowserFactory getBrowserFactory() {
        if (browserFactory == null) {
            browserFactory = new BrowserFactory();
        }
        return browserFactory;
    }

    public WebDriver getDriver() {
        return threadLocal.get();
    }

    public void removeDriver() {
        WebDriver driver = threadLocal.get();
        if (driver != null) {
            driver.quit();
            threadLocal.remove();
        }
    }

    public void loadCookies(WebDriver driver) {
        try (FileReader reader = new FileReader("src/main/resources/cookies.json")) {
            Json json = new Json();
            Set<Map<String, Object>> cookies = json.toType(reader, Set.class);

            for (Map<String, Object> cookieData : cookies) {
                Cookie cookie = new Cookie.Builder((String) cookieData.get("name"), (String) cookieData.get("value"))
                        .domain((String) cookieData.get("domain"))
                        .path((String) cookieData.get("path"))
                        .isSecure(true)
                        .isHttpOnly((Boolean) cookieData.get("httpOnly"))
                        .build();
                driver.manage().addCookie(cookie);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
