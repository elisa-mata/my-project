package elements;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.BaseInfo;

import java.util.List;
public class DashboardPageElements {
    public DashboardPageElements() {
        PageFactory.initElements(BaseInfo.getDriver(), this);
    }
    @FindBy(css = "body > div.master-wrapper-page > div.header-menu > ul.top-menu.notmobile > li:nth-child(2) > a")
    public WebElement electronicsHeader;
    @FindBy(css = ".ajax-products-busy")
    public WebElement ajaxProductsBusy;
    @FindBy(css = "body > div.master-wrapper-page > div.header-menu > ul.top-menu.notmobile > li:nth-child(2) > ul > li:nth-child(2) > a")
    public WebElement cellPhonesHeaderItem;
    @FindBy(css = ".block .list .active>a")
    public WebElement selectedCategory;
    @FindBy(css = ".block .sublist .active>a")
    public WebElement selectedSubCategory;
    @FindBy(id = "products-orderby")
    public WebElement sortSelect;
    @FindBy(css = ".price.actual-price")
    public List<WebElement> priceList;
    @FindBy(css = ".add-to-wishlist-button")
    public List<WebElement> addToWishlistButtons;
    @FindBy(css = ".bar-notification.success")
    public WebElement notificationBar;
    @FindBy(css = ".wishlist-qty")
    public WebElement wishlistQuantity;
    @FindBy(css = ".cart-qty")
    public WebElement cartQuantity;
    @FindBy(css = "a.ico-wishlist")
    public WebElement wishlistButton;
    @FindBy(css = ".wishlist-add-to-cart-button")
    public WebElement wishlistAddToCartButton;
    @FindBy(name = "addtocart")
    public List<WebElement> addToCartCheckboxes;
}
