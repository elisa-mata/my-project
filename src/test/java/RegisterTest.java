import golbals.Globals;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.RegistrationPage;
import utilities.BaseInfo;

public class RegisterTest {


    WebDriver driver = BaseInfo.getDriver();
    RegistrationPage registrationPage = new RegistrationPage();
    @BeforeTest
    public void setup(){
        driver.get(Globals.homePageUrl);

    }

    @Test
    public void testRegister(){
        registrationPage.clickLoginButton();
        registrationPage.clickRegisterButton();
        System.out.println("Page Title after Register: " + driver.getTitle());
        registrationPage.clickRadioFemale();
        registrationPage.fillFirstName("Elisa Nikola");
        registrationPage.fillLastName("Mata");
        registrationPage.selectDateOfBirth("27", "January", "2003");
        registrationPage.fillEmail("nikolamata@gmail.com");
        registrationPage.fillCompanyName("abc Company");
        registrationPage.uncheckNewsletterBox();
        registrationPage.checkNewsletterBox();
        registrationPage.fillPassword("nikola123");
        registrationPage.confirmPassword("nikola123");
        registrationPage.registerbutton();


    }
    @AfterTest
    public void terminate() {
driver.quit();
    }



}
