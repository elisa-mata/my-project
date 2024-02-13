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
    @FindBy(xpath = "//button[@class='button-2 add-to-wishlist-button']")
    public List <WebElement> addToWishListButtons;
    @FindBy(xpath = "//p[contains(@class, 'content')]")
    public WebElement notificationMessage;
    @FindBy(xpath = "//span[@class='wishlist-label']")
    public WebElement wishListMenu;
}
