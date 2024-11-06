package pages;

import elements.LoginPageElements;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BaseInfo;

import java.time.Duration;

public class LoginPage {
    public LoginPage (){PageFactory.initElements(BaseInfo.getDriver(), this);}
    LoginPageElements loginPageElements = new LoginPageElements();
    public void clickLoginMenu(){
        loginPageElements.loginMenu.click();
    }
    public void fillEmail(String email) {
        loginPageElements.emailInput.sendKeys(email);
    }
    public void fillPassword(String password){loginPageElements.passwordInput.sendKeys(password);}
    public void clickLoginButton(){
        loginPageElements.loginButton.click();
    }

    public boolean isWelcomeTextDisplayed() {
        WebDriverWait wait = new WebDriverWait(BaseInfo.getDriver(), Duration.ofSeconds(5));
        try {
            wait.until(ExpectedConditions.visibilityOf(loginPageElements.welcomeText));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
    public boolean isLoggedIn() {
        WebDriverWait wait = new WebDriverWait(BaseInfo.getDriver(), Duration.ofSeconds(5));
        try {
            wait.until(ExpectedConditions.visibilityOf(loginPageElements.logoutButton));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clickLogoutMenu() {
        loginPageElements.logoutButton.click();
    }
}

