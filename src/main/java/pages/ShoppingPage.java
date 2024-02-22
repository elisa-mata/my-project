package pages;

import elements.ShoppingPageElements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BaseInfo;

import java.time.Duration;

public class ShoppingPage {
    WebDriver driver = BaseInfo.getDriver();
    Actions actions = new Actions(driver);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    ShoppingPageElements shoppingPageElements = new ShoppingPageElements();

    public void navigateToShoppingCart() {
        actions.moveToElement(shoppingPageElements.shoppingCartMenu).perform();
        wait.until(ExpectedConditions.elementToBeClickable(shoppingPageElements.shoppingCartMenu));
    }
    public boolean goToCartConfirm() {return shoppingPageElements.goToCart.isDisplayed();}
    public void clickShoppingCart() {shoppingPageElements.shoppingCartMenu.click();}
    public boolean ButtonConfirm3() {
        return shoppingPageElements.continueShoppingButton.isDisplayed() &&
                shoppingPageElements.estimateShippingButton.isDisplayed() &&
                shoppingPageElements.updateCartButton.isDisplayed();
    }

    public void confirmShoppingCartMenu() {
        if (driver.getTitle().equals("Shopping Cart"))
        {System.out.println("We have navigated to " + driver.getTitle());}
    }

    public int getTotalCost() {
        int total = 0;
        for (WebElement list : shoppingPageElements.price) {
            total=total + Integer.parseInt(list.getText().replace("$", "").replace(".00", "").trim());
        }
        return total;
    }
    public void costOfWebsite(){System.out.println("The website's total calculated cost is " + shoppingPageElements.totalCostSum.getText()+" .");}
    public boolean confirmTotalCostSame(){
       int totalWeCalculated = getTotalCost();
       int totalWebCalculated = Integer.parseInt(shoppingPageElements.totalCostSum.getText().replace("$", "").replace(".00", "").trim());
        return totalWeCalculated == totalWebCalculated;
    }
}

