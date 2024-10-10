package APIDemos;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class FirstAppiumDemo {

    static AndroidDriver driver;

    public static void main(String[] args)  {

        try {
            openAppDemo();
        }
        catch (Exception e) {
            System.out.print("Error message: " + e.getMessage());
            System.out.print("What causes the error: " + e.getCause());
            e.getStackTrace();
        }
    }

    public static void openAppDemo() throws MalformedURLException {
//        ClassLoader cl = getClass().getClassLoader();
        ClassLoader cl = FirstAppiumDemo.class.getClassLoader();
        File file = new File(cl.getResource("builds/api-demos.apk").getFile());
        String apkPath = file.getAbsolutePath();

        UiAutomator2Options op = new UiAutomator2Options()
                .setDeviceName("sdk_gphone64_arm64") // using emulator
                .setUdid("emulator-5554")
                .setAutomationName("uiautomator2")
                .setPlatformName("Android")
                .setPlatformVersion("15")
                .setAppPackage("io.appium.android.apis")
                .setApp(apkPath)
                .setAppActivity(".ApiDemos")
                .setNoReset(false)
                .setFullReset(false)
                .setIgnoreHiddenApiPolicyError(true);

        URL url = new URL("http://127.0.0.1:4723");
        driver = new AndroidDriver(url, op);

        System.out.println("API Demo app started...");

        driver.quit();

    }
}
