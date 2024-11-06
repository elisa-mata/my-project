import golbals.Globals;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegistrationPage;
import utilities.BaseInfo;

//Test 1 : Register Test
public class RegisterTest {
    WebDriver driver = BaseInfo.getDriver();
    RegistrationPage registrationPage = new RegistrationPage();
    HomePage homePage = new HomePage();

    //1. Navigate to: https://demo.nopcommerce.com/
    @BeforeTest
    public void setup(){driver.get(Globals.homePageUrl);}

    @Test
    public void testRegister() {
        //2. Click LogIn - Menu
        homePage.clickLoginButton();
        //3.Click Register - button
        homePage.clickRegisterButton();
        //4. Check the title of the page after clicking Register button
        System.out.println("Page Title after Register: " + driver.getTitle());
        //5. Fill the register form as below:
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
        registrationPage.registerButton();
        //6. Verify that register is successful
        if (registrationPage.registerConfirm()) {System.out.println("Registation Completed succesfully!");};
    }
    @AfterTest
    public void terminate() {driver.quit();}
}
