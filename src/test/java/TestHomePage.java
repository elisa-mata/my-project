import golbals.Globals;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomePage;
import utilities.BaseInfo;

public class TestHomePage {

    private final WebDriver driver = BaseInfo.getDriver();
    HomePage homePage = new HomePage();
    @BeforeTest
    public void setup(){
        driver.get(Globals.homePageUrl);

    }
    @Test
    public void testLoginButton(){
        homePage.clickLoginButton();
homePage.clickRegisterButton();
        System.out.println("Page Title after Register: " + driver.getTitle());

    }

    @AfterTest
    public void terminate() {

    }
}
