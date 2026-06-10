package test;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class LoginTest extends BaseTest {

    @Test(
            priority = 3,
            description = "Проверка логина с позитивными кредами",
            testName = "Позитивные креды",
            groups = {"smoke", "login"}
            //  invocationCount = 2,
            //threadPoolSize = 2
    )
    @Owner("Anna")
    @Epic("Sauce Demo 1")
    @Feature("Login Page")
    @Story("Log in with positive creds")
    @Description("Проверка логина с позитивными кредами")
    @Severity(SeverityLevel.BLOCKER)
    @Flaky
    @Link(name = "analysis", url = "https://www.saucedemo.com/")
    @TmsLink("SD-101")
    @Issue("BUG 01")
    public void checkLoginWithPositiveCreds() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", password);
        String actualTitle = "Products";
        String expectedTitle = driver.getTitle();
        softAssert.assertNotEquals(actualTitle, expectedTitle, "Very bad");
        softAssert.assertAll();
    }

    @Test(description = "Проверка логина c пустым именем",
            testName = "Негативные креды",
            groups = {"regress", "login"}
    )
    @Owner("Anna")
    @Epic("Sauce Demo 1")
    @Feature("Login Page")
    @Story("Log in with empty username")
    @Severity(SeverityLevel.CRITICAL)
    public void checkLoginWithEmptyUsername() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.loginWithNegtiveCreds("", "standard_user");
        loginPage.login("", password);
        softAssert.assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username is required", "Very bad");
    }

    @Test(description = "Проверка логина c пустым паролем",
            testName = "Негативные креды",
            groups = {"regress", "login"}
    )
    @Owner("Anna")
    @Epic("Sauce Demo 1")
    @Feature("Login Page")
    @Story("Log in with empty password")
    @Severity(SeverityLevel.CRITICAL)
    public void checkLoginWithEmptyPassword() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.loginWithNegtiveCreds("standard_user", "");
        softAssert.assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Password is required", "Very bad");
    }

    @Test(description = "Проверка логина c негативными кредами",
            testName = "Негативные креды",
            groups = {"regress", "login"}
    )
    @Owner("Anna")
    @Epic("Sauce Demo 1")
    @Feature("Login Page")
    @Story("Log in with negative creds")
    @Severity(SeverityLevel.BLOCKER)
    public void checkLoginWithNegativeCreds() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.loginWithNegtiveCreds("test", "test");
        softAssert.assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service", "Very bad");
    }

    @DataProvider(name = "Параметризированный тест для негативного логина")
    public Object[][] loginData() {
        return new Object[][]{
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"test", "test", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(dataProvider = "Параметризированный тест для негативного логина",
            description = "Проверка логина c негативными кредами с дата провайдером",
            testName = "Негативные креды",
            groups = {"regress", "login"})
    @Owner("Anna")
    @Epic("Sauce Demo 1")
    @Feature("Login Page")
    @Story("Log in with dataprovuder")
    @Severity(SeverityLevel.NORMAL)
    public void checkLoginWithEmptyPassworWithloginData(String user, String password, String error) {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login(user, password);
        softAssert.assertEquals(loginPage.getErrorMessage(), error, "Very bad");
    }
}
