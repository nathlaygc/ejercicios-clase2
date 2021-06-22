package testPractices;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import practice.Base;

import java.util.List;

public class testHeroku extends Base {

    private WebDriver driver;

    @BeforeMethod
    public void beforeMethod(){
        driver = initializeDriver();
    }

    @Test
    public void test_one(){
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver,5);

        // PASOS:
        // Ir a http://the-internet.herokuapp.com/
        driver.get("http://the-internet.herokuapp.com/");

        // Seleccionar la opción dropdown
        driver.findElement(By.xpath("//div[@id='content']/ul/li/a[@href='/dropdown']")).click();

        // Seleccionar la opción 2 (por value)
        WebElement dropdownElement = driver.findElement(By.id("dropdown"));
        wait.until(ExpectedConditions.visibilityOf(dropdownElement));
        Select dropList = new Select(dropdownElement);
        dropList.selectByValue("2");

        // Verificar que la opción 1 NO esté seleccionada
        softAssert.assertFalse(dropList.getOptions().get(1).isSelected());

        // Verificar que la opción 2 esté seleccionada
        softAssert.assertTrue(dropList.getOptions().get(2).isSelected());

        // Seleccionar la opción 1 (por visible text)
        dropList.selectByVisibleText("Option 1");

        // Verificar que la opción 1 esté seleccionada
        softAssert.assertTrue(dropList.getOptions().get(1).isSelected());

        // Verificar que la opción 2 NO esté seleccionada
        softAssert.assertFalse(dropList.getOptions().get(2).isSelected());

        // Seleccionar la opción 2 (por index)
        dropList.selectByIndex(2);

        // Verificar que la opción 1 NO esté seleccionada
        softAssert.assertFalse(dropList.getOptions().get(1).isSelected());

        // Verificar que la opción 2 esté seleccionada
        softAssert.assertTrue(dropList.getOptions().get(2).isSelected());

        softAssert.assertAll();

    }

    @Test
    public void test_two() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver,5);
        int actualCount = 0;

        // Ir a http://the-internet.herokuapp.com/
        driver.get("http://the-internet.herokuapp.com/");

        // Seleccionar la opción add/remove elements
        driver.findElement(By.xpath("//div[@id='content']/ul/li/a[@href='/add_remove_elements/']")).click();

        // Verificar que el título diga Add/Remove Elements (assert)
        WebElement titleElement = driver.findElement(By.xpath("//h3"));
        wait.until(ExpectedConditions.visibilityOf(titleElement));
        String actualTitle = titleElement.getText();
        Assert.assertEquals(actualTitle,"Add/Remove Elements");

        // Verificar que add element es visible
        softAssert.assertTrue(driver.findElement(By.xpath("//div[@class='example']/button")).isDisplayed());

        // Verificar que el botón delete no es visible
        List <WebElement> listDeletes = driver.findElements(By.className("added-manually"));
        actualCount = listDeletes.size();
        softAssert.assertEquals(actualCount,0,"No hay deletes");

        // Hacer clic en add element
        driver.findElement(By.xpath("//div[@class='example']/button")).click();

        //  Verificar que el botón delete es visible
        softAssert.assertTrue(driver.findElement(By.xpath("//button[@class='added-manually']")).isDisplayed());

        // Hacer clic en delete
        driver.findElement(By.xpath("//button[@class='added-manually']")).click();

        // Verificar que el botón delete NO es visible
        listDeletes = driver.findElements(By.className("added-manually"));
        actualCount = listDeletes.size();
        softAssert.assertEquals(actualCount,0,"No hay deletes");

        softAssert.assertAll();
    }

    @Test
    public void test_three() {
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver,15);
        int actualCount = 0;

        // Ir a http://the-internet.herokuapp.com/
        driver.get("http://the-internet.herokuapp.com/");

        // Seleccionar la opción Form Authentication
        driver.findElement(By.xpath("//div[@id='content']/ul/li/a[@href='/login']")).click();

        // Verificar que el título sea login page (assert)
        Assert.assertEquals(driver.findElement(By.xpath("//h2")).getText(),"Login Page");

        // Hacer clic en el botón login (sin poner usuario ni contaseña)
        driver.findElement(By.className("radius")).click();

        // Verificar que el mensaje de error se muestre
        softAssert.assertTrue(driver.findElement(By.xpath("//div[@class='flash error']")).isDisplayed());

        // Llenar el usuario y contraseña que indica la página
        List<WebElement> data = driver.findElements(By.xpath("//h4[@class='subheader']/em"));
        String userPage = data.get(0).getText();
        String passPage = data.get(1).getText();

        // Escribir el usuario y contraseña que dicen el página
        driver.findElement(By.id("username")).sendKeys(userPage);
        driver.findElement(By.id("password")).sendKeys(passPage);
        driver.findElement(By.className("radius")).click();

        // Verificar que el título sea "Secure Area" (assert)
        WebElement titleElement = driver.findElement(By.xpath("//h2"));
        wait.until(ExpectedConditions.visibilityOf(titleElement));
        Assert.assertEquals(titleElement.getText(),"Secure Area");

        // Verificar que el mensaje de éxito salga arriba (assert)
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='flash success']")).isDisplayed());

        // Hacer clic en logout
        driver.findElement(By.xpath("//a[@href='/logout']")).click();

        // Verificar que el título sea nuevamente login page (assert)
        Assert.assertEquals(driver.findElement(By.xpath("//h2")).getText(),"Login Page");

        softAssert.assertAll();
    }

    @AfterTest
    public void afterTest(){
        driver.quit();
    }
}