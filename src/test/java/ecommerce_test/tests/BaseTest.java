package ecommerce_test.tests;

import ecommerce_test.common.UtilsTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import pages.CheckoutPage;
import pages.FormPage;
import pages.HomePage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected ResourceBundle resourceBundle = ResourceBundle.getBundle("env_dev");
    private static AppiumDriverLocalService service;

    protected AndroidDriver<AndroidElement> driver;
    protected FormPage formPage;
    protected HomePage homePage;
    protected CheckoutPage checkoutPage;

    @BeforeTest
    public void initEmulator() throws IOException, InterruptedException {
        String deviceName = resourceBundle.getString("device");

        UtilsTest.killAllProcess();

        if(deviceName.contains("Emulator") || deviceName.contains("emulator")){
            UtilsTest.startEmulator();
        }
    }

    public AndroidDriver<AndroidElement> capabilities() throws IOException, InterruptedException {

        String appName = resourceBundle.getString("app_name");
        String deviceName = resourceBundle.getString("device");

        File appDir = new File("src/main/resources/");
        File app = new File(appDir, appName);

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return driver;
    }

    public void startServer() throws IOException {
        service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingDriverExecutable(new File("/usr/local/bin/node"))
                .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                .withIPAddress("127.0.0.1").usingPort(4723));

        service.start();
        //service = AppiumDriverLocalService.buildDefaultService();
        //service.start();
    }

    public void stopServer(){
        service.stop();
    }

    public void getScreenShotPath(String testcaseName, WebDriver driver) throws IOException {
        TakesScreenshot screenshotDriver = (TakesScreenshot) driver;

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String destinationPath = System.getProperty("user.dir") + "/screenshots/" + testcaseName + timestamp + ".png";

        // take screenshot of the window to the temp folder
        File screenshotFile = screenshotDriver.getScreenshotAs(OutputType.FILE);

        // copy the screenshot file to the target
        FileUtils.copyFile(screenshotFile, new File(destinationPath));
    }

}
