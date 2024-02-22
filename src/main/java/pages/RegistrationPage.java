package pages;

import elements.RegisterPageElements;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utilities.BaseInfo;

public class RegistrationPage {
    public RegistrationPage (){PageFactory.initElements(BaseInfo.getDriver(),this);}
    RegisterPageElements registerPageElements =  new RegisterPageElements();
    public void clickRadioFemale(){
        registerPageElements.radioButtonFemale.click();
    }
    public void fillFirstName (String firstName) {
      registerPageElements.firstNameInput.sendKeys(firstName);
    }
    public void fillLastName (String lastName) {
        registerPageElements.lastNameInput.sendKeys(lastName);
    }
    public void selectDateOfBirth(String day, String month, String year ){
    selectFromDropdown(registerPageElements.selectDayDropdown, day );
    selectFromDropdown(registerPageElements.selectMonthDropdown, month);
    selectFromDropdown(registerPageElements.selectYearDropdown, year );
    }
    public void fillEmail(String email) {
        registerPageElements.emailInput.sendKeys(email);
    }
    public void fillCompanyName(String companyName){
        registerPageElements.companyInput.sendKeys(companyName);
    }

    private void selectFromDropdown(WebElement dropdownElement, String value){
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(value);
    }
    public void uncheckNewsletterBox(){
        WebElement checkbox = registerPageElements.newsletterCheckbox;
        if (checkbox.isSelected()) {
            checkbox.click();
        }
    }
    public void checkNewsletterBox(){
        WebElement checkbox = registerPageElements.newsletterCheckbox;
        if (!checkbox.isSelected()) {checkbox.click();}
    }
    public void fillPassword(String password){registerPageElements.passwordInput.sendKeys(password);}
    public void confirmPassword(String password){
        registerPageElements.confirmPasswordInput.sendKeys(password);
        }
    public void registerButton(){registerPageElements.registrationButton.click();}
    public boolean registerConfirm(){return registerPageElements.registrationCompleted.isDisplayed();}

    }


