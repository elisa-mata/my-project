package pages;


import dev.failsafe.internal.util.Assert;
import elements.DashboardPageElements;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BaseInfo;

import java.time.Duration;

public class DashboardPage {
    WebDriver driver = BaseInfo.getDriver();

    public DashboardPage() {
        PageFactory.initElements(BaseInfo.getDriver(), this);
    }

    DashboardPageElements dashboardPageElements = new DashboardPageElements();

    public void hoverOverElectronicsMenu() {
        Actions actions = new Actions(BaseInfo.getDriver());
        actions.moveToElement(dashboardPageElements.electronicsMenu).perform();
    }

    public void clickCellPhones() {
        dashboardPageElements.cellphoneMenuItem.click();
    }

    public boolean isCellPhonePageNavigated() {
        String electronicMenuColor = dashboardPageElements.electronicsMenu.getCssValue("color");
        String cellPhonecolor = dashboardPageElements.cellphoneMenuItem.getCssValue("color");
        return !electronicMenuColor.equals(cellPhonecolor);
    }

    public void selectSortingByPrice() {
        Select sortingDropdown = new Select(dashboardPageElements.sortingDropdown);
        sortingDropdown.selectByIndex(3);
    }

    public void addItemToWishList1() {
        try {
            dashboardPageElements.addToWishListButton1.click();
        } catch (StaleElementReferenceException st) {
            WebElement addToWishListButton1 = driver.findElement(By.xpath("//button[contains(@onclick, '/addproducttocart/catalog/19/2/1') and @title='Add to wishlist']"));
            addToWishListButton1.click();
        } catch (WebDriverException we) {
            we.printStackTrace();
        }

        waitForNotification();
    }

        public void addItemToWishlist2() {
        try {
            dashboardPageElements.addToWishListButton2.click();
        } catch (StaleElementReferenceException st){
            WebElement addToWishListButton2 = driver.findElement(By.xpath("//button[contains(@onclick, '/addproducttocart/catalog/18/2/1') and @title='Add to wishlist']"));
            addToWishListButton2.click();
        }catch (WebDriverException we) {
            we.printStackTrace();
        }

        waitForNotification();
    }
    public void addItemToWishlist3() {

        try {
            dashboardPageElements.addToWishListButton3.click();
        } catch (StaleElementReferenceException st){
            WebElement addToWishListButton3 = driver.findElement(By.xpath("//button[contains(@onclick, '/addproducttocart/catalog/20/2/1') and @title='Add to wishlist']"));
            addToWishListButton3.click();
        }catch (WebDriverException we) {
            we.printStackTrace();
        }

        waitForNotification();
    }
    public void waitForNotification() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(dashboardPageElements.notificationMessage));

    }

    public int getWishListItem() {
        String wishlistCountText = dashboardPageElements.wishListMenu.getText();
        String numericalValue = wishlistCountText.replaceAll("[^0-9]", ""); // Extract numerical portion
        return Integer.valueOf(numericalValue);
    }
}



