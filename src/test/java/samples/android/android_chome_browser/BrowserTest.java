package samples.android.android_chome_browser;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class BrowserTest extends BaseBrowser{

    AndroidDriver<AndroidElement> driver;

    @BeforeMethod
    public void gotoShoppingPage() throws MalformedURLException {
        driver = browserCapacities();
    }

    @Test
    public void demoTest(){
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.className("navbar-toggler-icon")).click();
        driver.findElementByCssSelector("li a[href*='products']").click();

        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("window.scrollBy(0,1000)");
        String productName = driver.findElementByXPath("(//*[@class='list-group-item'])[3]//a").getText();
        Assert.assertEquals(productName, "Devops");

    }
}
