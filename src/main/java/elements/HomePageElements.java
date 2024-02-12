package elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.BaseInfo;

public class HomePageElements {


    public HomePageElements(){
        PageFactory.initElements(BaseInfo.getDriver(),this);
    }

    @FindBy (linkText = "Log in")
    public  WebElement loginButton;

    @FindBy (linkText = "Register")
    public  WebElement  registerButton;



}
