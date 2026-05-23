package test;

import io.qameta.allure.testng.AllureTestNg;
import listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.*;

import java.time.Duration;
import java.util.HashMap;

//TestListener.class

@Listeners({AllureTestNg.class, TestListener.class})
public class BaseTest {
    WebDriver driver;
    public LoginPage loginPage;
    //private final ThreadLocal<LoginPage>  loginPage = new ThreadLocal<>();
    public ProductsPage productsPage;
    public CartPage cartPage;
    public CheckpoutPage checkpoutPage;
    public CheckoutOverviewPage checkoutOverviewPage;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true, description = "открытие браузера")
    public void setUp(@Optional("chrome") String browser, ITestContext iTestContext) {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            HashMap<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("credentials_enable_service", false); //отключаем всплывашку менеджера паролей хрома
            chromePrefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", chromePrefs);
            options.addArguments("--incognito");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-infobars");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }
        iTestContext.setAttribute("driver", driver);
        loginPage = new LoginPage(driver);
        //DriverManager.setWebDriver(new ChromeDriver(options));
        // DriverManager.getDriver().manage().window().maximize();
        // loginPage.set(new LoginPage(DriverManager.getDriver()));
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkpoutPage = new CheckpoutPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
    }

    @AfterMethod(alwaysRun = true, description = "закрытие браузера")
    public void teatDown() {
        if (driver != null) {
            driver.quit();
        }
        //  DriverManager.quit();
        //loginPage.remove();
    }
    //public ThreadLocal<LoginPage> getLoginPage() {
    //  return loginPage;
    //}
}
