package samples;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.net.MalformedURLException;

public class ScrollingTest extends Base {

    public static void main(String [] args) throws MalformedURLException {
        AndroidDriver<AndroidElement> driver = capabilities("emulator");

        // click on Views item
        driver.findElementByAndroidUIAutomator("text(\"Views\")").click();

        //Scolling by executing Android API code
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Seek Bar\"))");
    }
}
