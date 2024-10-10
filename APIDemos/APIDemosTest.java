package APIDemos;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class APIDemosTest {

    static AndroidDriver driver;

    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        APIDemosTest op = new APIDemosTest(); // constructor APIDemosTest
        URL url = new URL("http://127.0.0.1:4723");
        driver = new AndroidDriver(url, op.setupAPIDemos());

        APIDemosPages adp = new APIDemosPages(driver); // calling locators and actions pages
        adp.useXPath();
        // adp.useClassName();
        // adp.useIdAndAccessibilityId();
        // adp.useGetAttribute();
        // adp.testLongPress();

        System.out.println("APIDemos application closed...");

    }

    public UiAutomator2Options setupAPIDemos() {
        ClassLoader cl = getClass().getClassLoader();
        File file = new File(cl.getResource("builds/api-demos.apk").getFile());
        String apkPath = file.getAbsolutePath(); // setApp

        UiAutomator2Options op = new UiAutomator2Options()
                .setDeviceName("sdk_gphone64_arm64") // using emulator
                .setUdid("emulator-5554")
                .setAutomationName("uiautomator2")
                .setPlatformName("Android")
                .setPlatformVersion("15")
                .setAppPackage("io.appium.android.apis")
                .setAppActivity(".ApiDemos")
                .setApp(apkPath)
                .setNoReset(false)
                .setFullReset(false)
                .setIgnoreHiddenApiPolicyError(true);

        System.out.println("APIDemos application started...");

        return op;
    }
}
