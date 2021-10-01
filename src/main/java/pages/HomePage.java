package pages;

import common.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
    public HomePage(AndroidDriver driver){
        super(driver);
    }

    @AndroidFindBy(xpath = "//*[@text='ADD TO CART']")
    private WebElement btnAddToCart;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
    private WebElement btnShoppingCart;


    public CheckoutPage doShopping(){
        btnAddToCart.click();
        btnAddToCart.click();
        btnShoppingCart.click();
        return new CheckoutPage(driver);
    }

    public boolean isShoppingCartDisplayed(){
        return btnShoppingCart.isDisplayed();
    }


}
