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
    public void navigateToCellPhones() {WebDriver driver = BaseInfo.getDriver();

        new Actions(driver).moveToElement(dashboardElements.electronicsHeader).perform();

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
    public boolean isSuccessNotificationVisible() {return dashboardElements.notificationBar.isDisplayed() && dashboardElements.notificationBar.getText().equals("The product has been added to your wishlist");}
    public WebElement getNotificationBar() {
        return dashboardElements.notificationBar;
    }
    public int getWishlistQuantity() {return Integer.parseInt(dashboardElements.wishlistQuantity.getText().replace("(", "").replace(")", "").trim());}
    public int getCartQuantity() {return Integer.parseInt(dashboardElements.cartQuantity.getText().replace("(", "").replace(")", "").trim());}
    public void goToWishlist() {dashboardElements.wishlistButton.click();}
    public void selectAllWishlistProducts() {dashboardElements.addToCartCheckboxes.forEach(WebElement::click);}
    public void clickAddToCart() {dashboardElements.wishlistAddToCartButton.click();}
    public void wishlistSecondItem() {dashboardElements.addToWishlistButtons.get(1).click();}
    public void wishlistThirdItem() {dashboardElements.addToWishlistButtons.get(2).click();}
}



