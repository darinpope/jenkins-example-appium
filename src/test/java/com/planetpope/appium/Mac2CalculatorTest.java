package com.planetpope.appium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumClientConfig;
import io.appium.java_client.mac.Mac2Driver;
import io.appium.java_client.mac.options.Mac2Options;
import java.net.URI;
import java.time.Duration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

public class Mac2CalculatorTest {
    private static Mac2Driver driver;

    @BeforeAll
    public static void setupRunner() {
        String testUrl = System.getenv().get("TEST_URL");
        AppiumClientConfig appiumClientConfig = AppiumClientConfig.defaultConfig()
                .directConnect(true)
                .baseUri(URI.create(testUrl))
                .readTimeout(Duration.ofMinutes(5));
        Mac2Options options = new Mac2Options()
                .setBundleId("com.apple.calculator")
                .setPlatformName("mac")
                .setAutomationName("mac2")
                .setShowServerLogs(true);
        driver = new Mac2Driver(appiumClientConfig, options);
    }

    @Test
    public void verifyApplicationTitle() {
        WebElement el = driver.findElement(AppiumBy.className("XCUIElementTypeApplication"));
        assertEquals("Calculator", el.getAttribute("title"));
    }

    @Test
    public void verifyAddition() {
        driver.findElement(AppiumBy.name("two")).click();
        driver.findElement(AppiumBy.name("two")).click();
        driver.findElement(AppiumBy.name("add")).click();
        driver.findElement(AppiumBy.name("two")).click();
        driver.findElement(AppiumBy.name("zero")).click();
        driver.findElement(AppiumBy.id("=")).click();
        assertEquals("42", driver.findElement(AppiumBy.name("main display")).getText());
    }

    @AfterAll
    public static void teardownRunner() {
        driver.quit();
    }
}
