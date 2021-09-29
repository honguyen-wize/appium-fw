package samples.android.android_components;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.LongPressOptions;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.time.Duration;

import static io.appium.java_client.touch.offset.ElementOption.element;

public class DragDropTest  extends Base {

    public static void main(String [] args) throws MalformedURLException {
        AndroidDriver<AndroidElement> driver = capabilities("emulator");

        // go to drag and drop
        driver.findElementByXPath("//android.widget.TextView[@text='Views']").click();
        driver.findElementByXPath("//android.widget.TextView[@text='Drag and Drop']").click();

        //drag and drop
        WebElement sourceEl = driver.findElementByXPath("//*[contains(@resource-id,'drag_dot_1')]");
        WebElement destinationEl = driver.findElementByXPath("//*[contains(@resource-id,'drag_dot_2')]");
//        WebElement sourceEl = driver.findElementById("io.appium.android.apis:id/drag_dot_1");
//        WebElement destinationEl = driver.findElementById("io.appium.android.apis:id/drag_dot_2");

        TouchAction touchAction = new TouchAction(driver);

        // drag sourceEl to destinationEl
        touchAction.longPress(element(sourceEl)).moveTo(element(destinationEl)).release().perform();

        // drag sourceEl to destinationEl
        touchAction.longPress(LongPressOptions.longPressOptions().withElement(element(sourceEl)).withDuration(Duration.ofSeconds(2)))
                .moveTo(element(destinationEl))
                .release()
                .perform();

    }
}
