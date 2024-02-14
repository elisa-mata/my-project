import elements.DashboardPageElements;
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
      loginPage1.fillEmail("hekur1234@gmail.com");
      loginPage1.fillPassword("nikola123");
      loginPage1.clickLoginButton();

      dashboardPage.hoverOverElectronicsMenu();
      dashboardPage.clickCellPhones();
      boolean isCellPhoneNav = dashboardPage.isCellPhonePageNavigated();
      Assert.assertTrue(isCellPhoneNav, "Navigation to Cell phones page failed");
dashboardPage.selectSortingByPrice();

dashboardPage.addItemToWishList1();
      int wishlistItemCount1 = dashboardPage.getWishListItem();
      Assert.assertTrue(wishlistItemCount1 > 0, "Wishlist item count is not greater than 0");
      dashboardPage.addItemToWishlist2();
      int wishlistItemCount2 = dashboardPage.getWishListItem();
      Assert.assertTrue(wishlistItemCount2 > 1, "Wishlist item count after adding the second item is not greater than 1");
      dashboardPage.addItemToWishlist3();
      int wishlistItemCount3 = dashboardPage.getWishListItem();
      Assert.assertTrue(wishlistItemCount3 > 2, "Wishlist item count after adding the third item is not greater than 2");
      int wishlistItemCount = dashboardPage.getWishListItem();
      Assert.assertEquals(wishlistItemCount, 3, "Wishlist count is not as expected");

  }


}


