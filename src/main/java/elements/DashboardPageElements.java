package elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.BaseInfo;

import java.util.List;

public class DashboardPageElements {
    public DashboardPageElements (){
        PageFactory.initElements(BaseInfo.getDriver(),this);
    }

    @FindBy (css = "a[href='/electronics']")
    public WebElement electronicsMenu;
    @FindBy (css = "a[href='/cell-phones']" )
    public WebElement cellphoneMenuItem;
    @FindBy (css = "select[id ='products-orderby']")
    public  WebElement sortingDropdown;
    @FindBy(xpath = "//button[contains(@onclick, '/addproducttocart/catalog/19/2/1') and @title='Add to wishlist']")
    public WebElement addToWishListButton1;
    @FindBy(xpath = "//button[contains(@onclick, '/addproducttocart/catalog/18/2/1') and @title='Add to wishlist']")
    public WebElement addToWishListButton2;
    @FindBy(xpath = "//button[contains(@onclick, '/addproducttocart/catalog/20/2/1') and @title='Add to wishlist']")
    public WebElement addToWishListButton3;
    @FindBy(xpath = "//p[contains(@class, 'content')]")
    public WebElement notificationMessage;
    @FindBy(xpath = "/html/body/div[6]/div[1]/div[1]/div[2]/div[1]/ul/li[3]/a/span[2]")
    public WebElement wishListMenu;
}
