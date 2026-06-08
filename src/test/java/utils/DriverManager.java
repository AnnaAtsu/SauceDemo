package utils;

import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    public DriverManager() {
    }

    public static WebDriver getDriver() {
        return DRIVER.get();
    }

    public static void setWebDriver(WebDriver webDriver) {
        DRIVER.set(webDriver);
    }

    public static void quit() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
        }
    }
}
