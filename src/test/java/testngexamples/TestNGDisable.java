package testngexamples;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import practice.Base;

//this class is for testng3
public class TestNGDisable extends Base {
    @BeforeTest
    public void initialize() {
        driver = initializeDriver();
    }

    @Test(enabled = false)
    public void testExample3_1() throws InterruptedException {
        driver.get("https://www.alibaba.com/");
        System.out.println("we open alibaba");
        Thread.sleep(1000); //for academic purpose
    }

    @Test
    public void testExample3_2() throws InterruptedException {
        driver.get("https://www.amazon.com/");
        System.out.println("we open amazon");
        Thread.sleep(1000); //for academic purpose
    }

    @AfterTest
    public void closeDriver() {
        driver.quit();
    }
}
