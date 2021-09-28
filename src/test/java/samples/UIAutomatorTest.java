package samples;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.net.MalformedURLException;
import java.util.List;

public class UIAutomatorTest extends Base {
    public static void main(String [] args) throws MalformedURLException {
        AndroidDriver<AndroidElement> driver = capabilities("emulator");

        // click on Views item
        driver.findElementByAndroidUIAutomator("text(\"Views\")").click();

        // check all clickable items
        List<AndroidElement> allClickableItems = driver.findElementsByAndroidUIAutomator("new UiSelector().clickable(true)");
        for (AndroidElement item: allClickableItems){
            System.out.println(item.getText());
        }
    }
}
