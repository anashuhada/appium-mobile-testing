package WebDriverIO;

import com.google.common.collect.ImmutableList;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.awt.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

public class WebDriverIOPages {

    static AndroidDriver driver;

    WebDriverIOPages(AndroidDriver driver) {
        this.driver = driver;
    }

    protected static void swipeMethod(Point start, Point end, Duration duration) {
        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence swipe = new Sequence(input, 0);
        swipe.addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start.x, start.y));
        swipe.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(input.createPointerMove(duration, PointerInput.Origin.viewport(), end.x, end.y));
        swipe.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(ImmutableList.of(swipe));
    }

    // if scrollRatio = 0.8, then page will scroll more
    // if scrollRatio = 0.2, then page will scroll very less

    // if user wants to scroll page in DOWN direction
    // then scroll the screen starting from Bottom to Top (B -> A)

    // if user wants to scroll page in RIGHT direction
    // then scroll the screen starting from Right to Left (N -> M)

    // assume screen size: x-axis = 50, y-axis = 100
    // midpoint of the screen will be 50 * 0.5, eg: (25, 50)
    public static void scrollMethod(String pageDirection, double scrollRatio) {
        Duration scrollDuration = Duration.ofMillis(300);
        if(scrollRatio < 0 || scrollRatio > 1) {
            throw new Error("Scroll distance must be between 0 and 1");
        }

        Dimension size = driver.manage().window().getSize();
        System.out.println("Screen size: " + size);

        Point midPoint = new Point((int)(size.width * 0.5), (int)(size.height * 0.5));

        int a = (int) (midPoint.x * scrollRatio);
        int b = (int) (midPoint.y * scrollRatio);

        int bottom = midPoint.y + (int)(midPoint.y * scrollRatio); // 50 + 25   B
        int top = midPoint.y - (int)(midPoint.y * scrollRatio); // 50 - 25      A
        int left = midPoint.x + (int)(midPoint.x * scrollRatio); // 25 - 12.5   M
        int right = midPoint.x + (int)(midPoint.x * scrollRatio); // 25 + 12.5  N

        System.out.println("Midpoint: " + midPoint);
        System.out.println("Midpoint x: " + midPoint.x);
        System.out.println("a: " + a);
        System.out.println("Midpoint y: " + midPoint.y);
        System.out.println("b: " + b);
        System.out.println("Bottom: " + bottom);
        System.out.println("Top: " + top);
        System.out.println("Left: " + left);
        System.out.println("Right: " + right);
        System.out.println("-------------------------");

        if (pageDirection == "UP") {
            // swipe Top to Bottom, page will go UP
            swipeMethod(new Point(midPoint.x, top), new Point(midPoint.x, bottom), scrollDuration);
        } else if (pageDirection == "DOWN") {
            swipeMethod(new Point(midPoint.x, bottom), new Point(midPoint.x, top), scrollDuration);
        } else if (pageDirection == "LEFT") {
            swipeMethod(new Point(left, midPoint.y), new Point(right, midPoint.y), scrollDuration);
        } else {
            swipeMethod(new Point(right, midPoint.y), new Point(left, midPoint.y), scrollDuration);
        }
    }

    public void swipeUp() throws InterruptedException {
        driver.findElement(AppiumBy.xpath("//android.view.View[@content-desc='Swipe']")).click();
        Thread.sleep(3000);

        // swipe page towards Right
        scrollMethod("RIGHT", 0.5);
        Thread.sleep(2000);
        scrollMethod("RIGHT", 0.5);
        Thread.sleep(2000);
        scrollMethod("LEFT", 0.5);
        Thread.sleep(2000);
        scrollMethod("DOWN", 0.8);
        Thread.sleep(2000);
        scrollMethod("UP", 0.8);
    }

    public void nativeToWebToNative() throws InterruptedException {
        System.out.println("Current Context: " + driver.getContext());
        System.out.println("Current Handles: " + driver.getContextHandles());
        System.out.println("Go to WebView");
        driver.findElement(AppiumBy.xpath("//android.view.View[@content-desc='Webview']")).click();
        Thread.sleep(5000);
        System.out.println("Current Handles: " + driver.getContextHandles());

        // get all variables context - [NATIVE_APP, WEBVIEW_com.wdiodemoapp]
        Set<String> handles = driver.getContextHandles(); // unordered; not provide methods to access elements by index
        String webContext = new ArrayList<String>(handles).get(1); // convert set string into arraylist
        System.out.println("Fetch WebContext: " + webContext);

        // native to webview
        driver.context(webContext); // switch between types of views; native & webview
        System.out.println("Current Context: " + webContext);
        Thread.sleep(3000);

        driver.findElement(AppiumBy.xpath("//button[@aria-label='Toggle navigation bar']")).click();
        Thread.sleep(3000);

        driver.findElement(AppiumBy.xpath("//button[@aria-label='Close navigation bar']")).click();
        Thread.sleep(3000);

        driver.findElement(AppiumBy.xpath("//a[normalize-space()='Get Started']")).click();
        Thread.sleep(3000);

        // webview to native
        System.out.println("Current Context: " + driver.context("NATIVE_APP"));
        driver.findElement(AppiumBy.xpath("//android.view.View[@content-desc='Login']")).click();
        Thread.sleep(3000);
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@content-desc='input-email']")).sendKeys("ana@example.com");
        Thread.sleep(3000);
    }
}
