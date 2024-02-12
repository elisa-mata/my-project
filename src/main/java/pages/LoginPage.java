package pages;

import elements.LoginPageElements;
import org.openqa.selenium.support.PageFactory;
import utilities.BaseInfo;

public class LoginPage {
    public LoginPage (){
        PageFactory.initElements(BaseInfo.getDriver(), this);}

    LoginPageElements loginPageElements = new LoginPageElements();

    public void clickLoginMenu(){
        loginPageElements.loginMenu.click();
    }
    public void fillEmail(String email) {
        loginPageElements.emailInput.sendKeys(email);
    }
    public void fillPassword(String password){
        loginPageElements.passwordInput.sendKeys(password);
    }
    public void clickLoginButton(){
        loginPageElements.loginButton.click();
    }
    public boolean isWelcomeTextDisplayed(){
        return loginPageElements.welcomeText.isDisplayed();
    }
    public boolean isLogoutMenuDisplayed() {
        return loginPageElements.logoutButton.isDisplayed();
    }
    public void clickLogoutMenu() {
        loginPageElements.logoutButton.click();
    }
}

