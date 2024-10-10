package APIDemos;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AppTest {

    static AndroidDriver driver;

    public static void main(String[] args)  {

        try {
            openMobileApp();
        }
        catch (Exception e) {
            System.out.print("Error message: " + e.getMessage());
            System.out.print("What causes the error: " + e.getCause());
            e.getStackTrace();
        }

   }

    public static void openMobileApp() throws MalformedURLException {

//        DesiredCapabilities dc = new DesiredCapabilities(); // old version - deprecated
//        dc.setCapability("deviceName", "realme 5 Pro"); // phone name
//        dc.setCapability("udid", "630e78c");
//        dc.setCapability("platformName", "ANDROID");
//        dc.setCapability("platformVersion", "11"); // phone Android version
//        dc.setCapability("automationName", "uiautomator2");
//        dc.setCapability("appPackage", "com.google.android.calculator");
//        dc.setCapability("appActivity", "com.google.android.gms.common.api.GoogleApiActivity");

        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("realme 5 Pro")
                .setUdid("630e78c")
                .setAppPackage("com.swaglabsmobileapp")
                .setAppActivity(".MainActivity")
//                .setApp("/Volumes/KINGSTON/Software Testing/Mobile Testing/apk-files/calculator.apk")
                .setAutomationName("uiautomator2")
                .setPlatformName("Android")
                .setPlatformVersion("11")
                .setNoReset(false)
                .setFullReset(false)
                .setIgnoreHiddenApiPolicyError(true);

        URL url = new URL("http://127.0.0.1:4723");
        driver = new AndroidDriver(url, options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("Application started...");

//        try {
            WebElement el = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@content-desc=\"test-Username\"]"));
            el.sendKeys("standard_user");
            System.out.println(el.getText());
            System.out.println(driver.getPageSource());
//        }
//
//        finally {
//            driver.quit();
//        }

    }
}
