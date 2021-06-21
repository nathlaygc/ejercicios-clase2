package basicmethods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import practice.Base;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TestQuitClose extends Base {
    @BeforeMethod
    public void initialize() {
        driver = initializeDriver();
        driver.get("http://the-internet.herokuapp.com/windows"); //every test navigates to the url
    }

    @Test
    public void testDriverQuitClose() throws InterruptedException {
        //driver.close closes the current tab-window
        //driver.quit closes all the windows and shut down the driver

        //we open a lot of windows
        WebElement clickHere = driver.findElement(By.linkText("Click Here"));

        for(int i=0; i<4; i++){
            clickHere.click();
        }

        //get the references of all opened tabs
        Set<String> handlesSet = driver.getWindowHandles();
        List<String> handlesList = new ArrayList<String>(handlesSet);
        //set focus to the first tab (the one we made a lot of clicks)
        driver.switchTo().window(handlesList.get(0));

        Thread.sleep(4000);

        //we close that tab
        driver.close();

        Thread.sleep(4000);

        //3 tabs remaining, with quit we close them all at once
        driver.quit();
    }
}
