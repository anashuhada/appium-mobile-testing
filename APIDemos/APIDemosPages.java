package APIDemos;

import com.google.common.collect.ImmutableList;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.List;

public class APIDemosPages {

    static AndroidDriver driver;

    // set driver
    APIDemosPages(AndroidDriver driver) {
        this.driver = driver;
    }

    public void useXPath() {
        driver.findElement(AppiumBy.xpath("//*[@content-desc='Views']")).click();
        int countViews = driver.findElements(AppiumBy.xpath("//*[@resource-id='android:id/text1']")).size();
        System.out.println("Total number of Views list: " + countViews);

        driver.findElement(AppiumBy.xpath("//*[@resource-id='android:id/text1' and @text='Grid']"));
        driver.navigate().back();

        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='App']")).click();
        List<WebElement> countApp = driver.findElements(AppiumBy.xpath("//*[@resource-id='android:id/text1']"));
        System.out.println("Total number of App list: " + countApp.size());

        for(WebElement count : countApp) {
            System.out.println(count.getText());
        }

        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='Activity']")).click();
        WebElement text = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='Hello World']"));
        text.click();
        driver.navigate().back();
        driver.navigate().back();
        driver.navigate().back();

        driver.quit();
    }

    public void useClassName() {
        List<WebElement> countClassName = driver.findElements(AppiumBy.className("android.widget.TextView"));

        for(int i = 1; i < countClassName.size(); i++) {
            System.out.println("ClassName: " + countClassName.get(i).getText());
        }

        for(WebElement cn : countClassName) {
            System.out.println(cn.getText());
        }

        driver.quit();
    }

    // resource-id -> use driver.findElement(By.id(""))
    // accessibility id or content-desc -> use driver.findElement(AppiumBy.accessibilityId(""))
    public void useIdAndAccessibilityId() {
        driver.findElement(AppiumBy.id("android:id/text1")).click(); // returns the first one since sharing the same resource-id
        driver.navigate().back();

        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        driver.navigate().back();

        driver.findElement(AppiumBy.accessibilityId("Media")).click();
        driver.navigate().back();

        driver.quit();
    }

    // when calling getAttribute("name")
    // it will return the text value of the text attribute if it's TextView
    // or it will return the content-desc value if it's not TextView
    public void useGetAttribute() {
        // preferred - use getText()
        String getText = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='Accessibility']")).getText();
        System.out.println("Use getText(): " + getText);

        // getAttribute("name")
        String getAttName = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='Accessibility']")).getAttribute("name");
        System.out.println("Use getAttribute(\"name\"): " + getAttName);

        // getAttribute("content-desc")
        String getAttConDesc = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='Accessibility']")).getAttribute("content-desc");
        System.out.println("Use getAttribute(\"content-desc\"): " + getAttConDesc);

        // getAttribute("bounds")
        String getAttBounds = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='Accessibility']")).getAttribute("bounds");
        System.out.println("Use getAttribute(\"bounds\"): " + getAttBounds);

        driver.quit();
    }

    public static void longPressMethod(WebElement el) {
        Point location = el.getLocation();
        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence sequence = new Sequence(input, 0);
        sequence.addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), location.x, location.y));
        sequence.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        sequence.addAction(input.createPointerMove(Duration.ofSeconds(1), PointerInput.Origin.viewport(), location.x, location.y));
        sequence.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(ImmutableList.of(sequence));
    }

    public void testLongPress() throws InterruptedException {
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='Views']")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='Expandable Lists']")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='1. Custom Adapter']")).click();

        WebElement dogNames = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Dog Names']"));
        longPressMethod(dogNames);
        System.out.println("Long pressed");
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='Sample action']")).click();

        driver.quit();
    }

}
