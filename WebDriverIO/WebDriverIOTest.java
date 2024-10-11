package WebDriverIO;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverIOTest {

    static AndroidDriver driver;

    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        WebDriverIOTest wdiot = new WebDriverIOTest(); // constructor WebDriverIOTest
        URL url = new URL("http://127.0.0.1:4723");
        driver = new AndroidDriver(url, wdiot.setupAPIDemos());

        WebDriverIOPages wdiop = new WebDriverIOPages(driver); // calling locators and actions pages
        wdiop.swipeUp();
        wdiop.nativeToWebToNative();

        System.out.println("WebDriverIO application closed...");

    }

    public UiAutomator2Options setupAPIDemos() {
        ClassLoader cl = getClass().getClassLoader();
        File file = new File(cl.getResource("builds/webdriver-io.apk").getFile());
        String apkPath = file.getAbsolutePath(); // setApp

        UiAutomator2Options op = new UiAutomator2Options()
                .setDeviceName("sdk_gphone64_arm64") // using emulator
                .setUdid("emulator-5554")
                .setAutomationName("uiautomator2")
                .setPlatformName("Android")
                .setPlatformVersion("15")
                .setAppPackage("com.wdiodemoapp")
                .setAppActivity(".MainActivity")
                .setApp(apkPath)
                .setNoReset(false)
                .setFullReset(false)
                .setIgnoreHiddenApiPolicyError(true);

        System.out.println("WebDriverIO application started...");

        return op;
    }

}
