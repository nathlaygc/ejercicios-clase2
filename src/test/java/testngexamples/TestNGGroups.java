package testngexamples;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import practice.Base;

//this class is for testng4
public class TestNGGroups extends Base {
    @BeforeTest  (alwaysRun = true)
    public void initialize() {
        driver = initializeDriver();
    }

    @Test(groups = {"work", "salesforce"})
    public void testExample4_1() throws InterruptedException {
        driver.get("https://dictionary.cambridge.org/dictionary/english/work");
        System.out.println("we open work");
        Thread.sleep(1000); //for academic purpose
    }

    @Test (groups = {"work", "hire"})
    public void testExample4_2() throws InterruptedException {
        driver.get("https://www.linkedin.com/");
        System.out.println("we open linkedin");
        Thread.sleep(1000); //for academic purpose
    }

    @Test (groups = {"crm", "salesforce"})
    public void testExample4_3() throws InterruptedException {
        driver.get("https://www.salesforce.com/");
        System.out.println("we open salesforce");
        Thread.sleep(1000); //for academic purpose
    }

    @AfterTest(alwaysRun = true)
    public void closeDriver() {
        driver.quit();
    }
}
