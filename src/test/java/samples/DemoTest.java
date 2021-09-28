package samples;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.net.MalformedURLException;

public class DemoTest extends Base {
    
    public static void main(String [] args) throws MalformedURLException {
        AndroidDriver<AndroidElement> driver = capabilities("emulator");

        // Select Preference item
        driver.findElementByAccessibilityId("Preference").click();

        // open the Preference dependencies
        driver.findElementByAccessibilityId("3. Preference dependencies").click();

        // click on the Wifi checkbox
        driver.findElementById("android:id/checkbox").click();

        // click on Wifi Setting
        driver.findElementByXPath("(//android.widget.RelativeLayout)[2]").click();

        driver.findElementById("android:id/edit").sendKeys("hello");
        driver.findElementsByClassName("android.widget.Button").get(1).click();

    }
}
