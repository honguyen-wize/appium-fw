package e_commerce_sample;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.List;

public class ShoppingTest extends ECommerceBase {

    AndroidDriver<AndroidElement> driver;

    @BeforeMethod
    public void gotoShoppingPage() throws MalformedURLException {
        driver = capabilities("emulator");

        // Input your name
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Hello");
        driver.hideKeyboard();

        // Select Female radio
        driver.findElement(By.xpath("//*[@text='Female']")).click();

        // Open the dropdown country, scroll and select the right one
        driver.findElementById("android:id/text1").click();
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"))").click();

        // Click on Let's Shopp
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
    }

    @Test (enabled = true)
    public void shoppingOneItemTest() throws MalformedURLException, InterruptedException {
        String selectItemName = "Jordan 6 Rings";

        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()"
                        + ".resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView("
                        + "new UiSelector().text(\""+selectItemName+"\"));");

        List<AndroidElement> itemListOnScreen = driver.findElements(By.id("com.androidsample.generalstore:id/productName"));
        List<AndroidElement> addToCartOnScreen = driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart"));

        for(int i = 0; i < itemListOnScreen.size(); i++){
            if(itemListOnScreen.get(i).getText().equals(selectItemName)){
                addToCartOnScreen.get(i).click();
                break;
            }
        }

        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        Thread.sleep(2000);

         // Verify the selected item is the same as the one in the checkout page
        String checkoutItem = driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
        Assert.assertEquals(selectItemName, checkoutItem);

    }

    @Test (enabled = true)
    public void shoppingTwoItemTest() throws MalformedURLException, InterruptedException {
        // Select 2 first items
        driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
        driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();

        // go to Checkout page
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        Thread.sleep(2000);

        // find total price
        String totalPriceValue = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
        double totalAmountValue = getAmount(totalPriceValue);

        // find sum of products
        List<AndroidElement> productPriceItems = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
        double sumOfProducts = 0;
        for(AndroidElement priceEl: productPriceItems){
            double amount = getAmount(priceEl.getText());
            sumOfProducts += amount;
        }

//        String priceItem1Value = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(0).getText();
//        String priceItem2Value = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(1).getText();
//        // find sum of products
//        double amount1Value = getAmount(priceItem1Value); // remove the 1st character of $
//        double amount2Value = getAmount(priceItem2Value);
//        double sumOfProducts = amount1Value + amount2Value;

        // compare sum of products vs total price
        System.out.println("sumOfProducts: " + sumOfProducts);
        System.out.println("totalAmountValue: " + totalAmountValue);
        Assert.assertEquals(sumOfProducts, totalAmountValue);
    }

    // Example: "$123.4" => 123.4
    private double getAmount(String value){
        return Double.parseDouble(value.substring(1));
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
