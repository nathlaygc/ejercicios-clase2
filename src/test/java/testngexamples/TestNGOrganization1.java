package testngexamples;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import practice.Base;

//this class is for testng2
public class TestNGOrganization1 extends Base {
    @BeforeTest
    public void initialize() {
        driver = initializeDriver();
    }

    @Test
    public void testExample1_1() throws InterruptedException {
        driver.get("https://www.google.com/");
        System.out.println("we open google");
        Thread.sleep(1000); //for academic purpose
    }

    @Test
    public void testExample1_2() throws InterruptedException {
        driver.get("https://www.sony.com/");
        System.out.println("we open sony");
        Thread.sleep(1000); //for academic purpose
    }

    @AfterTest
    public void closeDriver() {
        driver.quit();
    }
}
