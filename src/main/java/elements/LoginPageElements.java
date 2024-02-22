package elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.BaseInfo;

public class LoginPageElements {
    public LoginPageElements (){
        PageFactory.initElements(BaseInfo.getDriver(), this);
    }
    @FindBy (xpath = "/html/body/div[6]/div[1]/div[1]/div[2]/div[1]/ul/li[2]/a")
    public WebElement loginMenu;
    @FindBy(css = "input[id ='Email']")
    public  WebElement emailInput;
    @FindBy(css ="input[id ='Password']" )
    public WebElement passwordInput;
    @FindBy(xpath = "/html/body/div[6]/div[3]/div/div/div/div[2]/div[1]/div[2]/form/div[3]/button")
    public WebElement loginButton;
    @FindBy(xpath = "/html/body/div[6]/div[3]/div/div/div/div/div[2]/div[1]/h2")
    public WebElement welcomeText;
    @FindBy(xpath = "/html/body/div[6]/div[1]/div[1]/div[2]/div[1]/ul/li[2]/a")
    public WebElement logoutButton;
}
