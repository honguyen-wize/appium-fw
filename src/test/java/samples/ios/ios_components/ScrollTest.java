package samples.ios.ios_components;

import io.appium.java_client.ios.IOSDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.HashMap;

public class ScrollTest extends BaseIOS {
    IOSDriver driver;

    @Test
    public void alertTest() throws MalformedURLException, InterruptedException {
        driver = capabilitiesIOS();

        HashMap<String, Object> scrollObject = new HashMap<>();
        scrollObject.put("direction", "down");
        scrollObject.put("name", "Web View"); // scroll until see the text "Web View"

        driver.executeScript("mobile:scroll", scrollObject);

        driver.findElementByAccessibilityId("Web View").click();
        Thread.sleep(2000);

        // back to the menu
        driver.findElementByXPath("//XCUIElementTypeButton[@name=\"UIKitCatalog\"]").click();

        // open the picker view and select others
        driver.findElementByAccessibilityId("Picker View").click();
        driver.findElementByAccessibilityId("Red color component value").sendKeys("85");
        driver.findElementByAccessibilityId("Green color component value").sendKeys("220");
        driver.findElementByAccessibilityId("Blue color component value").sendKeys("105");

    }
}
