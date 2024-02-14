package pages;

import elements.DashboardPageElements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BaseInfo;

import java.time.Duration;
import java.util.List;
//import dev.failsafe.internal.util.Assert;
//import elements.DashboardPageElements;
//import org.openqa.selenium.*;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.Wait;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import utilities.BaseInfo;
//
//import java.time.Duration;

public class DashboardPage {

    DashboardPageElements dashboardElements = new DashboardPageElements();

    public DashboardPage() {
        PageFactory.initElements(BaseInfo.getDriver(), this);
    }

    public WebElement getAjaxInProgress() {
        return dashboardElements.ajaxProductsBusy;
    }

    public void waitForNotificationFade() {
        new WebDriverWait(BaseInfo.getDriver(), Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOf(getNotificationBar()));
        new WebDriverWait(BaseInfo.getDriver(), Duration.ofSeconds(6)).until(ExpectedConditions.invisibilityOf(getNotificationBar()));
    }

    public void navigateToCellPhones() {
        WebDriver driver = BaseInfo.getDriver();

        new Actions(driver)
                .moveToElement(dashboardElements.electronicsHeader)
                .perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(dashboardElements.cellPhonesHeaderItem));

        dashboardElements.cellPhonesHeaderItem.click();
    }

    public boolean isInCellPhones() {
        return dashboardElements.selectedCategory.getText().trim().equals("Electronics") && dashboardElements.selectedSubCategory.getText().trim().equals("Cell phones");
    }

    public void sortLowToHigh() {
        new Select(dashboardElements.sortSelect).selectByValue("6");
        new Select(dashboardElements.sortSelect).selectByValue("10");
    }

    public List<WebElement> getPriceList() {
        return dashboardElements.priceList;
    }

    public void wishlistFirstItem() {
        dashboardElements.addToWishlistButtons.get(0).click();
    }

    public boolean isSuccessNotificationVisible() {
        return dashboardElements.notificationBar.isDisplayed() && dashboardElements.notificationBar.getText().equals("The product has been added to your wishlist");
    }

    public WebElement getNotificationBar() {
        return dashboardElements.notificationBar;
    }

    public int getWishlistQuantity() {
        return Integer.parseInt(dashboardElements.wishlistQuantity.getText().replace("(", "").replace(")", "").trim());
    }

    public int getCartQuantity() {
        return Integer.parseInt(dashboardElements.cartQuantity.getText().replace("(", "").replace(")", "").trim());
    }

    public void goToWishlist() {
        dashboardElements.wishlistButton.click();
    }

    public void selectAllWishlistProducts() {
        dashboardElements.addToCartCheckboxes.forEach(WebElement::click);
    }

    public void clickAddToCart() {
        dashboardElements.wishlistAddToCartButton.click();
    }

    public void wishlistSecondItem() {
        dashboardElements.addToWishlistButtons.get(1).click();
    }

    public void wishlistThirdItem() {
        dashboardElements.addToWishlistButtons.get(2).click();
    }
//    WebDriver driver = BaseInfo.getDriver();
//
//    public DashboardPage() {
//        PageFactory.initElements(BaseInfo.getDriver(), this);
//    }
//
//    DashboardPageElements dashboardPageElements = new DashboardPageElements();
//
//    public void hoverOverElectronicsMenu() {
//        Actions actions = new Actions(BaseInfo.getDriver());
//        actions.moveToElement(dashboardPageElements.electronicsMenu).perform();
//    }
//
//    public void clickCellPhones() {
//        dashboardPageElements.cellphoneMenuItem.click();
//    }
//
//    public boolean isCellPhonePageNavigated() {
//        String electronicMenuColor = dashboardPageElements.electronicsMenu.getCssValue("color");
//        String cellPhonecolor = dashboardPageElements.cellphoneMenuItem.getCssValue("color");
//        return !electronicMenuColor.equals(cellPhonecolor);
//    }
//
//    public void selectSortingByPrice() {
//        Select sortingDropdown = new Select(dashboardPageElements.sortingDropdown);
//        sortingDropdown.selectByIndex(3);
//    }
//
//    public void wishlistFirstItem() {
//        dashboardPageElements.addToWishlistButtons.get(0).click();
//    }
//
//    public boolean isSuccessNotificationVisible() {
//        return dashboardPageElements.notificationBar.isDisplayed() && dashboardPageElements.notificationBar.getText().equals("The product has been added to your wishlist");
//    }
//    public WebElement getNotificationBar() {
//        return dashboardPageElements.notificationBar;
//    }
//    public int getWishlistQuantity() {
//        return Integer.parseInt(dashboardPageElements.wishlistQuantity.getText().replace("(", "").replace(")", "").trim());
//    }
//

//    public void addItemToWishList1() {
//        try {
//            dashboardPageElements.addToWishListButton1.click();
//        } catch (StaleElementReferenceException st) {
//            WebElement addToWishListButton1 = driver.findElement(By.xpath("//button[contains(@onclick, '/addproducttocart/catalog/19/2/1') and @title='Add to wishlist']"));
//            addToWishListButton1.click();
//        } catch (WebDriverException we) {
//            we.printStackTrace();
//        }
//
//        waitForNotification();
//    }
//
//        public void addItemToWishlist2() {
//        try {
//            dashboardPageElements.addToWishListButton2.click();
//        } catch (StaleElementReferenceException st){
//            WebElement addToWishListButton2 = driver.findElement(By.xpath("//button[contains(@onclick, '/addproducttocart/catalog/18/2/1') and @title='Add to wishlist']"));
//            addToWishListButton2.click();
//        }catch (WebDriverException we) {
//            we.printStackTrace();
//        }
//
//        waitForNotification();
//    }
//    public void addItemToWishlist3() {
//        try {
//            dashboardPageElements.addToWishListButton3.click();
//        } catch (StaleElementReferenceException st){
//            WebElement addToWishListButton3 = driver.findElement(By.xpath("//button[contains(@onclick, '/addproducttocart/catalog/20/2/1') and @title='Add to wishlist']"));
//            addToWishListButton3.click();
//        }catch (WebDriverException we) {
//            we.printStackTrace();
//        }
//
//        waitForNotification();
//    }
//    public void waitForNotification() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.visibilityOf(dashboardPageElements.notificationMessage));
//
//    }

//    public int getWishListItem() {
//        String wishlistCountText = dashboardPageElements.wishListMenu.getText();
//        String numericalValue = wishlistCountText.replaceAll("[^0-9]", ""); // Extract numerical portion
//        return Integer.valueOf(numericalValue);
//    }
}



