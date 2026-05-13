package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;


public class LoginTest extends BaseTest{

    @Test
    public void checkLoginWithPosotiveCreds() {
        loginPage.open();
        loginPage.login("standard_user", "standard_use");
        assertNotEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required", "Very bad");
    }

    @Test
    public void checkLoginWithEmptyUsername() {
        loginPage.open();
        loginPage.login("", "standard_user");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required", "Very bad");
    }

    @Test
    public void checkLoginWithEmptyPasword() {
        loginPage.open();
        loginPage.login("standard_user", "");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required", "Very bad");
    }
}
