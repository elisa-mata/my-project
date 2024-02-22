import golbals.Globals;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utilities.BaseInfo;

//Test 2: Login Test
public class LoginTest {
    WebDriver driver = BaseInfo.getDriver();
    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();
    //1. Navigate to: https://demo.nopcommerce.com/
    @BeforeTest
    public void setup(){
        driver.get(Globals.homePageUrl);
    }
    @Test(priority = 0)
    public void testLogin(){
        //2. Click LogIn - Menu
        homePage.clickLoginButton();
        //3. Login with the credentials created from Test 1
        loginPage.fillEmail("nikolamata@gmail.com");
        loginPage.fillPassword("nikola123");
        loginPage.clickLoginButton();
        //4. Verify that login is successful:
        //- “Welcome to our store text” - is displayed
        if (loginPage.isWelcomeTextDisplayed()) {System.out.println("Welcome text is displayed.");}
        //- Log out - Menu is displayed
        Assert.assertTrue(loginPage.isLogoutMenuDisplayed(), "Logout menu is not displayed");
    }
    //5. Log out
    @Test(priority = 1)
    public void LogOut(){loginPage.clickLogoutMenu();}
    @AfterTest
    public void terminate() {driver.quit();}
}
