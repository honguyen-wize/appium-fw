package samples;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.WebElement;
import java.net.MalformedURLException;

import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;

public class TouchActionsTest extends Base {

    public static void main(String [] args) throws MalformedURLException {
        AndroidDriver<AndroidElement> driver = capabilities("emulator");

        driver.findElementByXPath("//android.widget.TextView[@text='Views']").click();

        // Tap
        WebElement expandListItem = driver.findElementByAccessibilityId("Expandable Lists");
        TouchAction touchAction = new TouchAction(driver);
        touchAction.tap(tapOptions().withElement(element(expandListItem))).perform();

        // click 1. Custom Adapter
        driver.findElementByXPath("//android.widget.TextView[@text=\"1. Custom Adapter\"]").click();

        //Long press
        WebElement peopleNameItem = driver.findElementByXPath("//android.widget.TextView[@text='People Names']");
        touchAction.longPress(longPressOptions().withElement(element(peopleNameItem))).release().perform();

        System.out.println(driver.findElementByXPath("//android.widget.TextView[@text='Sample menu']").getText());

    }
}
