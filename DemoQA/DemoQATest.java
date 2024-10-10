package DemoQA;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;

public class DemoQATest {

    static AndroidDriver driver;

    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        DemoQATest dqat = new DemoQATest(); // constructor DemoQATest
        URL url = new URL("http://127.0.0.1:4723");
        driver = new AndroidDriver(url, dqat.setupDemoQA());

        DemoQAPages dqap = new DemoQAPages(driver); // calling locators and actions pages
        dqap.inputTextbox();

        System.out.println("DemoQA using Chrome closed...");

    }

    public UiAutomator2Options setupDemoQA() {

        UiAutomator2Options op = new UiAutomator2Options()
                .setDeviceName("sdk_gphone64_arm64") // using emulator
                .setUdid("emulator-5554")
                .setAutomationName("uiautomator2")
                .setPlatformName("Android")
                .setPlatformVersion("15")
                .withBrowserName("Chrome");

        System.out.println("DemoQA using Chrome started...");

        return op;
    }

}
