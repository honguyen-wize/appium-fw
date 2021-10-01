package pages;

import common.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckoutPage extends BasePage {
    public CheckoutPage(AndroidDriver driver){
        super(driver);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
    List<AndroidElement> productPriceItems;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement lblTotalPrice;


    public boolean isTotalPriceDisplayed(){
        return lblTotalPrice.isDisplayed();
    }


}
