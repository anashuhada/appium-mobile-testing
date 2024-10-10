package DemoQA;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class DemoQAPages {

    static AndroidDriver driver;

    DemoQAPages(AndroidDriver driver) {
        this.driver = driver;
    }

    public void inputTextbox() throws InterruptedException {
        driver.get("https://demoqa.com/text-box");

        driver.findElement(By.xpath("//input[@id='userName']")).sendKeys("Eliza Montgomery");
        driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("eliza.montgomery@example.com");
        driver.findElement(By.xpath("//textarea[@id='currentAddress']")).sendKeys("123 Maple Street, Apt 4B, Portland, OR 97201, USA");
        driver.findElement(By.xpath("//textarea[@id='permanentAddress']")).sendKeys("789 Oak Avenue, Springfield, IL 62704, USA");
        driver.findElement(By.xpath("//button[@id='submit' and @type='button']")).click();

        driver.quit();
    }
}
