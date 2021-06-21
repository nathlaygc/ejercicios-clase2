package testPractices;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import practice.Base;

public class initialTest extends Base {
    private WebDriver driver;

    @BeforeMethod
    public void beforeMethod(){
        driver = initializeDriver();
    }


    @Test
    public void initialTesting(){
        driver.get("https://www.google.com");
    }

    @AfterTest
    public void afterMethod(){
        driver.quit();
    }
}
