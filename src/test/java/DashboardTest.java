import golbals.Globals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestListenerAdapter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import utilities.BaseInfo;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.stream.IntStream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
//import elements.DashboardPageElements;
//import golbals.Globals;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;
//import pages.DashboardPage;
//import pages.LoginPage;
//import utilities.BaseInfo;
//import static org.testng.Assert.assertEquals;

//@Test(groups = {"dashboard"}, dependsOnGroups = {"login"})
//@Listeners(TestListenerAdapter.class)
public class DashboardTest {
    private final DashboardPage dashboardPage = new DashboardPage();
    private final WebDriver driver = BaseInfo.getDriver();
    private final LoginPage loginPage = new LoginPage();

    @BeforeClass
    public void setup() {
        driver.get(Globals.homePageUrl);
    }

    @Test(priority = 0)
    public void testGoToCellPhones() {
        loginPage.clickLoginMenu();
        loginPage.fillEmail("hekur1234@gmail.com");
        loginPage.fillPassword("nikola123");
        loginPage.clickLoginButton();
        dashboardPage.navigateToCellPhones();
        assertTrue(dashboardPage.isInCellPhones());
    }

    @Test(priority = 1)
    public void testSortItems() throws InterruptedException {
        dashboardPage.sortLowToHigh();
        new WebDriverWait(BaseInfo.getDriver(), Duration.ofSeconds(3)).until(ExpectedConditions.invisibilityOf(dashboardPage.getAjaxInProgress()));
        List<BigDecimal> prices = dashboardPage.getPriceList().stream()
                .map(WebElement::getText)
                .map(e -> e.replace("$", ""))
                .map(BigDecimal::new)
                .toList();

        boolean isAscending = IntStream.range(0, prices.size() - 1)
                .allMatch(i -> prices.get(i).compareTo(prices.get(i + 1)) <= 0);
        assertTrue(isAscending);
    }

    @Test(priority = 2)
    public void testAddToWishlist() {
        dashboardPage.wishlistFirstItem();
        dashboardPage.waitForNotificationFade();
        dashboardPage.wishlistSecondItem();
        dashboardPage.waitForNotificationFade();
        dashboardPage.wishlistThirdItem();
        dashboardPage.waitForNotificationFade();
        assertEquals(dashboardPage.getWishlistQuantity(), 3);
    }

    @Test(priority = 3)
    public void testWishlistToCart() {
        dashboardPage.goToWishlist();
        dashboardPage.selectAllWishlistProducts();
        dashboardPage.clickAddToCart();
        assertEquals(dashboardPage.getWishlistQuantity(), 0);
        assertEquals(dashboardPage.getCartQuantity(), 3);
    }
}

//    WebDriver driver = BaseInfo.getDriver();
//    LoginPage loginPage1 = new LoginPage();
//    DashboardPage dashboardPage = new DashboardPage();
//
//
//
//    @BeforeTest
//    public void setup(){
//        driver.get(Globals.homePageUrl);
//    }
//
//  @Test
//    public void dashboardFunctionality(){
//        loginPage1.clickLoginMenu();
//      loginPage1.fillEmail("hekur1234@gmail.com");
//      loginPage1.fillPassword("nikola123");
//      loginPage1.clickLoginButton();
//
//      dashboardPage.hoverOverElectronicsMenu();
//      dashboardPage.clickCellPhones();
//      boolean isCellPhoneNav = dashboardPage.isCellPhonePageNavigated();
//      Assert.assertTrue(isCellPhoneNav, "Navigation to Cell phones page failed");
//dashboardPage.selectSortingByPrice();
//
//      dashboardPage.wishlistFirstItem();
//      dashboardPage.();
//      dashboardPage.wishlistSecondItem();
//      dashboardPage.waitForNotificationFade();
//      dashboardPage.wishlistThirdItem();
//      dashboardPage.waitForNotificationFade();
//      assertEquals(dashboardPage.getWishlistQuantity(), 3);
//dashboardPage.addItemToWishList1();
////      int wishlistItemCount1 = dashboardPage.getWishListItem();
////      Assert.assertTrue(wishlistItemCount1 > 0, "Wishlist item count is not greater than 0");
//      dashboardPage.addItemToWishlist2();
////      int wishlistItemCount2 = dashboardPage.getWishListItem();
////      Assert.assertTrue(wishlistItemCount2 > 1, "Wishlist item count after adding the second item is not greater than 1");
//      dashboardPage.addItemToWishlist3();
////      int wishlistItemCount3 = dashboardPage.getWishListItem();
////      Assert.assertTrue(wishlistItemCount3 > 2, "Wishlist item count after adding the third item is not greater than 2");
//      int wishlistItemCount = dashboardPage.getWishListItem();
//      Assert.assertEquals(wishlistItemCount, 3, "Wishlist count is not as expected");

//  }
//
//
//}


