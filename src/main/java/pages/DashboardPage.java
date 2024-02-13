package pages;


import dev.failsafe.internal.util.Assert;
import elements.DashboardPageElements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BaseInfo;
public class DashboardPage {
    public DashboardPage (){
        PageFactory.initElements(BaseInfo.getDriver(), this);}
   DashboardPageElements dashboardPageElements = new DashboardPageElements();

    public void hoverOverElectronicsMenu(){
        Actions actions = new Actions(BaseInfo.getDriver());
        actions.moveToElement(dashboardPageElements.electronicsMenu).perform();
    }
    public void clickCellPhones() {
        dashboardPageElements.cellphoneMenuItem.click();
    }
    public boolean isCellPhonePageNavigated(){
        String electronicMenuColor = dashboardPageElements.electronicsMenu.getCssValue("color");
        String cellPhonecolor = dashboardPageElements.cellphoneMenuItem.getCssValue("color");
         return !electronicMenuColor.equals(cellPhonecolor);
    }
    public void selectSortingByPrice(){
        Select sortingDropdown = new Select(dashboardPageElements.sortingDropdown);
        sortingDropdown.selectByIndex(3);
    }

    public void addItemstoWishlist(){
        long startime = System.currentTimeMillis();
        long timeout = 10000;

        for (WebElement button: dashboardPageElements.addToWishListButtons){
            button.click();
            String notificationText = "";
            while (!notificationText.contains("The product has been added to your wishlist")) {
                notificationText = dashboardPageElements.notificationMessage.getText();
                if (System.currentTimeMillis() - startime > timeout) {
                    System.out.println("Timeout: Notification message did not contain expected text");
                    break;
                }
            }
        }


    }
    public int getWishlistItemCoun(){
        String wishListCountText = dashboardPageElements.wishListMenu.getText();
        return Integer.parseInt(wishListCountText);
    }

    }


