package pages;

import elements.HomePageElements;
import org.openqa.selenium.support.PageFactory;
import utilities.BaseInfo;

public class HomePage {
    HomePageElements homePageElements = new HomePageElements();
    public HomePage() {
        PageFactory.initElements(BaseInfo.getDriver(), this);
    }
    public void clickLoginButton(){homePageElements.loginButton.click();}
    public void clickRegisterButton(){homePageElements.registerButton.click();}
}
