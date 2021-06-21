package basicmethods;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import practice.Base;

public class TestHelloWorld extends Base {
    @BeforeMethod
    public void initialize() {
        driver = initializeDriver();
    }

    @Test
    public void test() throws InterruptedException {
        driver.get("https://www.google.com/");
        Thread.sleep(5000); //for academic purpose
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }
}
