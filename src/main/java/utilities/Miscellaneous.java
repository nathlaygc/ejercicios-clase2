package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Miscellaneous {
    //method to highlight the current element, added for academic purpose
    public static void highLighterMethod(WebDriver driver, WebElement element) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "arguments[0].setAttribute('style', 'background: green; border: 3px solid blue;');", element);
        Thread.sleep(3000);
        js.executeScript("arguments[0].removeAttribute('style','')", element);
    }
}
