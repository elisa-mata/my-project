package elements;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.BaseInfo;

public class RegisterPageElements {
    public RegisterPageElements (){
        PageFactory.initElements(BaseInfo.getDriver(),this);
    }
    @FindBy(css = "input[id='gender-female']")
    public WebElement radioButtonFemale;
    @FindBy (css = "input[id ='FirstName']")
    public WebElement firstNameInput;
    @FindBy (css = "input[id ='LastName']")
    public WebElement lastNameInput;
    @FindBy(css = "input[id ='Email']")
    public  WebElement emailInput;
    @FindBy (css ="select[name ='DateOfBirthDay']" )
    public WebElement selectDayDropdown;
    @FindBy (css ="select[name ='DateOfBirthMonth']" )
    public WebElement selectMonthDropdown;
    @FindBy (css = "select[name ='DateOfBirthYear']")
    public WebElement selectYearDropdown;
    @FindBy(css = "input[id ='Company']")
    public WebElement companyInput;
    @FindBy (css = "input[id ='Newsletter']")
    public WebElement newsletterCheckbox;
    @FindBy(css ="input[id ='Password']" )
    public WebElement passwordInput;
    @FindBy(css = "input[id ='ConfirmPassword']")
    public WebElement confirmPasswordInput;
    @FindBy(xpath = "//*[@id=\"register-button\"]")
    public WebElement registrationButton;
    @FindBy(xpath = "/html/body/div[6]/div[3]/div/div/div/div[2]/div[1]")
    public WebElement registrationCompleted;
}
