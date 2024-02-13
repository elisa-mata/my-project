import golbals.Globals;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import utilities.BaseInfo;

public class LoginTest {
    WebDriver driver = BaseInfo.getDriver();
    LoginPage loginPage = new LoginPage();

    @BeforeTest
    public void setup(){
        driver.get(Globals.homePageUrl);
    }

    @Test
    public void testLogin(){
        loginPage.clickLoginMenu();
        loginPage.fillEmail("elisamata27@gmail.com");
        loginPage.fillPassword("nikola123");
        loginPage.clickLoginButton();
        if (loginPage.isWelcomeTextDisplayed()) {
            System.out.println("Welcome text is displayed.");
        } else {
            System.out.println("Welcome text is not displayed.");
        }
        Assert.assertTrue(loginPage.isLogoutMenuDisplayed(), "Logout menu is not displayed");

        loginPage.clickLogoutMenu();

    }
}
