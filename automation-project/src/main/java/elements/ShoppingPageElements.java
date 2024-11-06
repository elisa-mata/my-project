package elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.BaseInfo;

import java.util.List;

public class ShoppingPageElements {
    public ShoppingPageElements(){PageFactory.initElements(BaseInfo.getDriver(),this);}
    @FindBy(xpath = "//*[@id=\"topcartlink\"]/a")
    public WebElement shoppingCartMenu;
    @FindBy(linkText = "Shopping cart")
    public WebElement goToCart;
    @FindBy(xpath = "//*[@id=\"updatecart\"]")
    public WebElement updateCartButton;
    @FindBy(xpath = "//*[@id=\"shopping-cart-form\"]/div[2]/div[1]/button[2]")
    public WebElement continueShoppingButton;
    @FindBy(xpath = "//*[@id=\"open-estimate-shipping-popup\"]")
    public WebElement estimateShippingButton;
    @FindBy(css = ".product-subtotal")
    public List<WebElement> price;
    @FindBy(css = ".value-summary")
    public WebElement totalCostSum;
}
