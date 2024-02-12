package pages;

import elements.HomePageElements;

public class HomePage {
    HomePageElements homePageElements = new HomePageElements();


    public void clickLoginButton(){
        homePageElements.loginButton.click();

    }
    public RegistrationPage clickRegisterButton(){
        homePageElements.registerButton.click();
        return new RegistrationPage();
    }
}
