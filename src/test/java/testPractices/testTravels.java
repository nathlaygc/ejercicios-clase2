package testPractices;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import practice.Base;

import java.util.List;

public class testTravels extends Base {
    private WebDriver driver;

    // Tarea:
    // ir a https://phptravels.com/demo/
    // hacer clic en product (menú superior)
    // hacer clic en Providers (submenu de product)
    // verificar que el título sea Travel XML API Integrations Providers
    // hacer clic en demo (menú superior)
    // verificar que el título sea Application Test Drive

    @BeforeMethod
    public void beforeMethod(){
        driver = initializeDriver();
    }

    @Test
    public void testTravels() throws InterruptedException {
        String actualTitle = "";
        String expectedTitle1 = "Travel XML API Integrations Providers";
        String expectedTitle2 = "Application Test Drive";
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver,15);

        // Ir a https://phptravels.com/demo/
        driver.get("https://phptravels.com/demo/");

        // Encontrar el elemento "product" y hacer Clic (menú superior)
        driver.findElement(By.xpath("//div[@class='lvl-0 dropdown']//span[contains(text(),'Product')]")).click();

        // Encontrar y hacer clic en "Providers" (submenu de product)
        driver.findElement(By.xpath("//div[@class='dropdown-content featuresDropdown']//a[@href='https://phptravels.com/providers/']")).click();

        // Verificar que el título sea Travel XML API Integrations Providers
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.id("header-title")), "Travel XML API Integrations Providers"));
        actualTitle = driver.findElement(By.id("header-title")).getText();
        System.out.println("Providers> actual: "+actualTitle+"Expected "+expectedTitle1);
        softAssert.assertEquals(expectedTitle1, actualTitle, "Verification failed");

        // Hacer clic en demo (menú superior)
        driver.findElement(By.xpath("//a[@href='https://phptravels.com/demo']")).click();

        // Verificar que el título sea Application Test Drive
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.id("header-title")), "Application Test Drive"));
        actualTitle = driver.findElement(By.id("header-title")).getText();
        System.out.println("Providers> actual: "+actualTitle+"Expected "+expectedTitle2);
        softAssert.assertEquals(expectedTitle2, actualTitle, "Verification failed");

        //Results
        softAssert.assertAll();
    }

    @AfterTest
    public void afterTest(){
        driver.quit();
    }

}
