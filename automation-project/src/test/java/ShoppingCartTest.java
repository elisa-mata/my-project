import golbals.Globals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.HomePage;
import pages.ShoppingPage;
import utilities.BaseInfo;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.stream.IntStream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ShoppingCartTest {
    WebDriver driver = BaseInfo.getDriver();
    LoginTest loginTest = new LoginTest();
    ShoppingPage shoppingPage = new ShoppingPage();
    DashboardTest dashboardTest = new DashboardTest();
    @BeforeTest
    public void setup() {driver.get(Globals.homePageUrl);}
    @Test
    public void PreConditionLogIn() {
        loginTest.testLogin();
        //dashboardTest.CellPhoneMenu();
        //dashboardTest.testAddToWishlist();
        //dashboardTest.testWishlistToCart();
    }
    @Test
    public void ShoppingCartTest(){
        //1. Hover over Shopping Cart – Menu
        shoppingPage.navigateToShoppingCart();
        //2. Verify that ‘Go To Cart’ – button is displayed
        if (shoppingPage.goToCartConfirm()) {System.out.println("We are in the Cart Menu");}
        //3. Click ‘Go To Cart’ – button
        shoppingPage.clickShoppingCart();
        //4. Verify that we have navigated to Shopping Cart Page
        shoppingPage.confirmShoppingCartMenu();
        //5. Verify that following buttons are displayed
        if (shoppingPage.ButtonConfirm3()) {System.out.println("All three buttons are displayed");}
        //6. Verify that the prices sum for all items is equal to Total Price in the end of the page
        System.out.println("Our calculated cost is $" + shoppingPage.getTotalCost()+".00 .");
        shoppingPage.costOfWebsite();
        if (shoppingPage.confirmTotalCostSame()){ System.out.println("The prices are the same.");}
    }
    //7. Close the browser
    @AfterTest
    public void terminate() {driver.quit();}
}
