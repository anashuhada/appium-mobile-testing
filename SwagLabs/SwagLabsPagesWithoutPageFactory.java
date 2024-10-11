package SwagLabs;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class SwagLabsPagesWithoutPageFactory {

    AndroidDriver driver;

    // constructor with driver - without PageFactory
    SwagLabsPagesWithoutPageFactory(AndroidDriver driver) {
        this.driver = driver;
    }

    // set locators
    By logo = By.xpath("//android.widget.ScrollView[@content-desc='test-Login']/android.view.ViewGroup/android.widget.ImageView[1]");
    By username = By.xpath("//android.widget.EditText[@content-desc='test-Username']");
    By password = By.xpath("//android.widget.EditText[@content-desc='test-Password']");
    By image = By.xpath("//android.widget.ScrollView[@content-desc='test-Login']/android.view.ViewGroup/android.widget.ImageView[2]");
    By login = By.xpath("//android.view.ViewGroup[@content-desc='test-LOGIN']");

    // create action methods
    public void logoStatus() {
        boolean lStatus = driver.findElement(logo).isDisplayed();
        System.out.println("Logo status displayed: " + lStatus);
    }

    public void setUsername(String user) {
        driver.findElement(username).sendKeys(user);
    }

    public void setPassword(String pass) {
        driver.findElement(password).sendKeys(pass);
    }

    public void imageStatus() {
        boolean iStatus = driver.findElement(image).isDisplayed();
        System.out.println("Image status displayed: " + iStatus);
    }

    public void clickLogin() {
        driver.findElement(login).click();
    }
}
