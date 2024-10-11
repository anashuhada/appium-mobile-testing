package SwagLabs;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SwagLabsPagesWithPageFactory {

    AndroidDriver driver;

    // constructor with driver - without PageFactory
    SwagLabsPagesWithPageFactory(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // mandatory
    }

    // set locators
    @FindBy(xpath = "//android.widget.ScrollView[@content-desc='test-Login']/android.view.ViewGroup/android.widget.ImageView[1]")
    WebElement logo;

    @FindBy(xpath = "//android.widget.EditText[@content-desc='test-Username']")
    WebElement username;

    @FindBy(xpath = "//android.widget.EditText[@content-desc='test-Password']")
    WebElement password;

    @FindBy(xpath = "//android.widget.ScrollView[@content-desc='test-Login']/android.view.ViewGroup/android.widget.ImageView[2]")
    WebElement image;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-LOGIN']")
    WebElement login;

    // create action methods
    public void logoStatus() {
        boolean lStatus = logo.isDisplayed();
        System.out.println("Logo status displayed: " + lStatus);
    }

    public void setUsername(String user) throws InterruptedException {
        username.sendKeys(user);
        Thread.sleep(2000);
    }

    public void setPassword(String pass) throws InterruptedException {
        password.sendKeys(pass);
        Thread.sleep(2000);
    }

    public void imageStatus() {
        boolean iStatus = image.isDisplayed();
        System.out.println("Image status displayed: " + iStatus);
    }

    public void clickLogin() {
        login.click();
    }

}
