package test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;


public class LoginTest extends BaseTest {

    @Test(
            priority = 3,
            description = "Проверка логина с позитивными кредами",
            testName = "Позитивные креды"
          //  invocationCount = 2,
            //threadPoolSize = 2
    )
    public void checkLoginWithPositiveCreds() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertNotEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required", "Very bad");
    }

    @Test
    public void checkLoginWithEmptyUsername() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("", "standard_user");
        softAssert.assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username is required", "Very bad");
    }

    @Test
    public void checkLoginWithEmptyPassword() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "");
        softAssert.assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Password is required", "Very bad");
    }

    @Test
    public void checkLoginWithNegativeCreds() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("test", "test");
        softAssert.assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service", "Very bad");
    }

    @DataProvider(name = "Параметризированный тест для негативного логина")
    public Object[][] loginData() {
        return new Object[][] {
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"test", "test", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(dataProvider = "Параметризированный тест для негативного логина")
    public void checkLoginWithEmptyPassworWithloginData(String user, String password, String error) {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login(user, password);
        softAssert.assertEquals(loginPage.getErrorMessage(), error, "Very bad");
    }
}
