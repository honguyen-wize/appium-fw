package ecommerce_test.tests;

import ecommerce_test.common.UtilsTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
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
import java.net.MalformedURLException;
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
        if(!isRunningOnCloud()){
            String deviceName = this.getDeviceName();
            UtilsTest.killAllProcess();
            UtilsTest.startEmulator(deviceName);
        }

    }

    public AndroidDriver<AndroidElement> capabilities() throws IOException, InterruptedException {
        String deviceName = this.getDeviceName();
        String appName = resourceBundle.getString("app_name");
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

    public AndroidDriver<AndroidElement> capabilitiesCloud() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("browserstack.user", "honguyen3");
        cap.setCapability("browserstack.key", "1aMwo6RkK7zHmbx6M1JE");
        cap.setCapability("device", "Samsung Galaxy S21");
        cap.setCapability("os_version", "11.0");
        cap.setCapability("app", "bs://76372c9ae91de6736f125fbf87b774eaf5607435");
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://hub.browserstack.com/wd/hub"), cap);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return driver;
    }


    protected AndroidDriver<AndroidElement> getDriver() throws IOException, InterruptedException {
        if(isRunningOnCloud()) { // if run on cloud, then get cloud driver
            return capabilitiesCloud();
        } else {
            return capabilities();
        }
    }

    protected boolean isRunningOnCloud(){
        if(StringUtils.isNotEmpty(System.getProperty("cloud"))
                && System.getProperty("cloud").equalsIgnoreCase("true")){
            return true;
        }
         return false;
    }

    private String getDeviceName(){
        String deviceName = "";
        if(System.getProperty("deviceName") != null){
            deviceName = System.getProperty("deviceName");
        } else {
            deviceName = resourceBundle.getString("deviceName");
        }
        return deviceName;
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
