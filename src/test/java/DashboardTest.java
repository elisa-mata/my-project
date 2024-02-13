import golbals.Globals;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import utilities.BaseInfo;

public class DashboardTest {
    WebDriver driver = BaseInfo.getDriver();
    LoginPage loginPage1 = new LoginPage();
    DashboardPage dashboardPage = new DashboardPage();

    @BeforeTest
    public void setup(){
        driver.get(Globals.homePageUrl);
    }

  @Test
    public void dashboardFunctionality(){
        loginPage1.clickLoginMenu();
      loginPage1.fillEmail("hekur12@gmail.com");
      loginPage1.fillPassword("nikola123");
      loginPage1.clickLoginButton();

      dashboardPage.hoverOverElectronicsMenu();
      dashboardPage.clickCellPhones();
      boolean isCellPhoneNav = dashboardPage.isCellPhonePageNavigated();
      Assert.assertTrue(isCellPhoneNav, "Navigation to Cell phones page failed");
dashboardPage.selectSortingByPrice();
dashboardPage.addItemstoWishlist();
int wishListItemcount = dashboardPage.getWishlistItemCoun();
Assert.assertEquals(wishListItemcount, 3, "Wishlist count is not as expected");
  }


}

