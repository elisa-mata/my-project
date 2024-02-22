import golbals.Globals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.DashboardPage;
import utilities.BaseInfo;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.stream.IntStream;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

//Test 3: Dashboard Test
public class DashboardTest {
    DashboardPage dashboardPage = new DashboardPage();
    WebDriver driver = BaseInfo.getDriver();
    LoginTest loginTest = new LoginTest();

    @BeforeClass
    public void setup() {
        driver.get(Globals.homePageUrl);
    }
    //Precondition: Log in nopCommerce Application
    @Test(priority = 0)
    public void PreConditionLogIn(){loginTest.testLogin();}

    //1. Hover over Electronics Menu
    @Test(priority = 1)
    public void CellPhoneMenu() throws InterruptedException {
        //2. Click Cell phones
        dashboardPage.navigateToCellPhones();
        //3. Verify that we have navigated to Cell phones page (Electronic and Cell phones menu items have different color)
        assertTrue(dashboardPage.isInCellPhones());
        //4. Apply Sort Price: Low to High and check that items displayed are sorted by the price.
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
    //5. Add all three items in wishlist. Verify that after every item is added, a notification with text:
    // The product has been added to your wishlist – is displayed.
    @Test(priority = 2)
    public void testAddToWishlist() {
        dashboardPage.wishlistFirstItem();
        dashboardPage.waitForNotificationFade();
        dashboardPage.wishlistSecondItem();
        dashboardPage.waitForNotificationFade();
        dashboardPage.wishlistThirdItem();
        dashboardPage.waitForNotificationFade();
        //6. Verify that Wishlist on Menu bar displays (3).
        assertEquals(dashboardPage.getWishlistQuantity(), 3);
    }

    //7. Click Wishlist menu, select all three products and click Add To Cart button.
    @Test(priority = 3)
    public void testWishlistToCart() {
        dashboardPage.goToWishlist();
        dashboardPage.selectAllWishlistProducts();
        dashboardPage.clickAddToCart();
        //8. Verify that now Wishlist displays (0) and Shopping cart (3).
        assertEquals(dashboardPage.getWishlistQuantity(), 0);
        assertEquals(dashboardPage.getCartQuantity(), 3);
    }
    //9. Close the browser.
    @AfterTest
    public void terminate() {driver.quit();}
}