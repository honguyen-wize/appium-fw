package common;

import io.appium.java_client.android.AndroidDriver;

public class Utils {
    public static void scrollToText(AndroidDriver driver, String scrollToText){
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + scrollToText + "\"))");
    }
}
