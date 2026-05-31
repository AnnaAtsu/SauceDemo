package pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BasePage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
        return this;
    }

    private final By USERNAME_FIELD = By.id("user-name");
    private final By PASSWORD_FIELD = By.id("password");
    private final By LOGIN_BUTTON = By.className("submit-button");
    private final By ERROR_MESSAGE = By.cssSelector("[data-test='error']");

    @Step("Открытие страницы sauce demo")
    public LoginPage open() {
        driver.get(BASE_URL);
        return this;
    }

    @Step("Вход в систему с именем пользователя: {user} и паролем {pass} ")
    public ProductsPage login(String user, String pass) {
        driver.findElement(USERNAME_FIELD).sendKeys(user);
        driver.findElement(PASSWORD_FIELD).sendKeys(pass);
        driver.findElement(LOGIN_BUTTON).click();
        return new ProductsPage(driver);
    }

    @Step("Вход в систему с именем пользователя: {user} и паролем {pass} ")
    public LoginPage loginWithNegtiveCreds(String user, String pass) {
        driver.findElement(USERNAME_FIELD).sendKeys(user);
        driver.findElement(PASSWORD_FIELD).sendKeys(pass);
        driver.findElement(LOGIN_BUTTON).click();
        return this;
    }

    @Step("Получить сообщение об ошибке")
    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }
}
