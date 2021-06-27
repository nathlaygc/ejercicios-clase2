package testPractices;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import practice.Base;

import java.util.List;

public class TestTravels extends Base {
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
        WebDriverWait wait = new WebDriverWait(driver,6);

        // Ir a https://phptravels.com/demo/
        driver.get("https://phptravels.com/demo/");

        // Encontrar el elemento "product" y hacer Clic (menú superior)
        driver.findElement(By.xpath("//span[contains(text(),'Product')]")).click();

        // Encontrar y hacer clic en "Providers" (submenu de product)
        //Comentario: En este caso por el href o texto del link se retornan dos elementos
        // la diferencia basicamente entre ambos es el nombre de la clase y el div que lo contiene, me parecio
        //mas sencillo buscar por la clase : //div[@class='featuresDropdown']//a[@href='https://phptravels.com/providers/'] no funciono
        driver.findElement(By.xpath("//div[@class='dropdown-content featuresDropdown']//a[@href='https://phptravels.com/providers/']")).click();

        // Verificar que el título sea Travel XML API Integrations Providers
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.id("header-title")), "Travel XML API Integrations Providers"));
        actualTitle = driver.findElement(By.id("header-title")).getText();
        System.out.println("Providers> actual: "+actualTitle+"Expected "+expectedTitle1);
        softAssert.assertEquals(expectedTitle1, actualTitle, "Verification failed");

        // Hacer clic en demo (menú superior)
        driver.findElement(By.xpath("//a[@href='https://phptravels.com/demo']")).click();
        //driver.findElement(By.partialLinkText("Demo")); : no funciona

        // Verificar que el título sea Application Test Drive
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.id("header-title")), "Application Test Drive"));
        actualTitle = driver.findElement(By.id("header-title")).getText();
        System.out.println("Providers> actual: "+actualTitle+"Expected "+expectedTitle2);
        softAssert.assertEquals(expectedTitle2, actualTitle, "Verification failed");

        //Results
        softAssert.assertAll();
    }

    @AfterMethod
    public void afterTest(){
        driver.quit();
    }

}
