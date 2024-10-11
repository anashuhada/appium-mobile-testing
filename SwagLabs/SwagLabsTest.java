package SwagLabs;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.io.File;
import java.net.URL;
import java.time.Duration;

public class SwagLabsTest {

    static AndroidDriver driver;

    @BeforeClass
    public void setup() {
        try {
            SwagLabsTest slt = new SwagLabsTest(); // constructor of the class
            URL url = new URL("http://127.0.0.1:4723");
            UiAutomator2Options op = setupSwagLabs();
            driver = new AndroidDriver(url, slt.setupSwagLabs());

            System.out.println("SwagLabs application started...");
        }
        catch(Exception e) {
            System.out.println("Exception message" + e.getMessage());
            System.out.println("Caused by: " + e.getCause());
            e.getStackTrace();
        }
    }

    @Test
    public void loginApp() throws InterruptedException {
        // without PageFactory
        /*SwagLabsPagesWithoutPageFactory slpwopf = new SwagLabsPagesWithoutPageFactory(driver);
        slpwopf.logoStatus();
        slpwopf.setUsername("standard_user"); // pass username here
        slpwopf.setPassword("secret_sauce");  // pass password here
        slpwopf.imageStatus();
        slpwopf.clickLogin();*/

        // with PageFactory
        SwagLabsPagesWithPageFactory slpwpf = new SwagLabsPagesWithPageFactory(driver);
        slpwpf.logoStatus();
        slpwpf.setUsername("standard_user"); // pass username here
        slpwpf.setPassword("secret_sauce");  // pass password here
        slpwpf.imageStatus();
        slpwpf.clickLogin();
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        //Thread.sleep(5000);
        driver.quit();
        System.out.println("SwagLabs application closed...");
    }

    public UiAutomator2Options setupSwagLabs() {
        try {
            ClassLoader cl = getClass().getClassLoader();
            File file = new File(cl.getResource("builds/swag-labs.apk").getFile());
            String apkPath = file.getAbsolutePath(); // setApp

            // launch the app
            return new UiAutomator2Options()
                    .setDeviceName("sdk_gphone64_arm64") // emulator
                    .setUdid("emulator-5554") // emulator
                    //.setDeviceName("realme 5 Pro") // real device
                    //.setUdid("630e78c") // real device
                    .setAutomationName("uiautomator2")
                    .setPlatformName("Android")
                    .setPlatformVersion("15") // emulator
                    //.setPlatformVersion("11")
                    .setAppPackage("com.swaglabsmobileapp")
                    .setAppActivity(".MainActivity")
                    .setApp(apkPath)
                    .setNoReset(false)
                    .setFullReset(false)
                    .setIgnoreHiddenApiPolicyError(true);
        }
        catch (Exception e) {
            System.out.println("Setup failed...");
            System.out.println("Exception message" + e.getMessage());
            System.out.println("Caused by: " + e.getCause());
            return null;
        }
    }
}
