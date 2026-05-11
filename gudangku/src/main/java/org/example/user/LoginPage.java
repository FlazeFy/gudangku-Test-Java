package org.example.user;

import org.example.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(id = "username-input")
    private WebElement inputUsername;

    @FindBy(id = "password-input")
    private WebElement inputPassword;

    @FindBy(id = "submit-login-btn")
    private WebElement signInButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        waitForElementToBeVisible(inputUsername);
        inputUsername.sendKeys(username);
        inputPassword.sendKeys(password);
        signInButton.click();
    }

    public boolean verifyRedirectToLanding() {
        waitForUrlContains("/");
        return driver.getCurrentUrl().contains("/");
    }
}
