package basicmethods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import practice.Base;
import utilities.ListsMethods;

import java.util.List;

import static utilities.Miscellaneous.highLighterMethod;
import static utilities.RandomEmail.randomEmail;

public class TestDOC extends Base {
    @BeforeMethod
    public void initialize() {
        driver = initializeDriver();
        driver.get("https://demo.opencart.com/"); //every test navigates to the url
    }

    @Test
    public void testFindElementBy() throws InterruptedException {
        //find element By link text
        WebElement desktops = driver.findElement(By.linkText("Desktops"));
        highLighterMethod(driver, desktops);

        //find element By partial link text
        WebElement shopping = driver.findElement(By.partialLinkText("Shopping"));
        highLighterMethod(driver, shopping);

        //find element By xpath
        WebElement macImage = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[1]/div/div[1]/a/img"));
        highLighterMethod(driver, macImage);

        //find element By partial css
        WebElement components = driver.findElement(
                By.cssSelector("#menu > div.collapse.navbar-collapse.navbar-ex1-collapse > ul > li:nth-child(3) > a"));
        highLighterMethod(driver, components);

        //for the next examples we will navigate to this form
        driver.get("https://demo.opencart.com/index.php?route=account/register");
        //find element By id
        WebElement firstNameInput = driver.findElement(By.id("input-firstname"));
        highLighterMethod(driver, firstNameInput);

        //find element By name
        WebElement lastNameInput = driver.findElement(By.name("lastname"));
        highLighterMethod(driver, lastNameInput);
    }

    @Test
    public void testListElementsBy(){
        //find elements by classname

        //take not that we use findElements not findElement so we expect a list of web elements
        List<WebElement> dropdownElements = driver.findElements(By.className("dropdown-toggle"));

        //now we iterate and prints the elements
        ListsMethods.printListText(dropdownElements);

        //take not that we use findElements not findElement so we expect a list of web elements
        List<WebElement> imgElements = driver.findElements(By.className("price"));

        //now we iterate and prints the elements
        ListsMethods.printListText(imgElements);
    }

    @Test
    public void testElementAttributes() {
        String link;
        String text;
        String imgSrc;

        WebElement software = driver.findElement(By.linkText("Software"));
        link = software.getAttribute("href"); //getting the href attribute
        text = software.getText();  //getting the text of the element
        System.out.println("navigating to " + link); //printing the href attribute
        System.out.println("text of the element: " + text); //printing the text of the element

        WebElement imgPhone = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[2]/div/div[1]/a/img"));
        imgSrc = imgPhone.getAttribute("src"); //getting the src attribute
        System.out.println("img src of the element: " + imgSrc); //printing the src attribute
    }

    @Test
    public void testClick() throws InterruptedException {
        WebElement myAccount = driver.findElement(By.linkText("My Account"));
        myAccount.click();
        Thread.sleep(2000); //for academic purpose

        WebElement register = driver.findElement(By.linkText("Register"));
        register.click();
        Thread.sleep(2000); //for academic purpose

        driver.navigate().back();

        WebElement macImg = driver.findElement(
                By.cssSelector("#menu > div.collapse.navbar-collapse.navbar-ex1-collapse > ul > li:nth-child(3) > a"));
        macImg.click();
        Thread.sleep(2000); //for academic purpose

        driver.navigate().back();
    }

    @Test
    public void testForm() throws InterruptedException {
        //first we navigate to the form
        WebElement myAccount = driver.findElement(By.linkText("My Account"));
        myAccount.click();

        WebElement register = driver.findElement(By.linkText("Register"));
        register.click();

        //we begin to fill the input fields

        //write the first name
        WebElement firstNameInput = driver.findElement(By.id("input-firstname"));
        firstNameInput.sendKeys("first name test");

        //write the last name
        WebElement lastNameInput = driver.findElement(By.id("input-lastname"));
        lastNameInput.sendKeys("last name test");

        //write the email
        WebElement emailInput = driver.findElement(By.id("input-email"));
        emailInput.sendKeys(randomEmail()); //calls a method that creates a unique and random email

        //write the telephone
        WebElement telephoneInput = driver.findElement(By.id("input-telephone"));
        telephoneInput.sendKeys("912345678");

        //write the password
        WebElement pwd1Input = driver.findElement(By.id("input-password"));
        pwd1Input.sendKeys("password123");

        //write the password confirm
        WebElement pwd2Input = driver.findElement(By.id("input-confirm"));
        pwd2Input.sendKeys("password123");

        //we click on the Yes Subscribe (by default "no" is selected)
        List<WebElement> rdtBtns = driver.findElements(By.name("newsletter"));
        rdtBtns.get(0).click();

        //we agree to the privacy by clicking on the checkbox
        WebElement privacyCheckBox = driver.findElement(By.name("agree"));
        privacyCheckBox.click();

        Thread.sleep(4000); //for academic purpose

        //finally we click on Continue
        WebElement continueBtn = driver.findElement(
                By.cssSelector("#content > form > div > div > input.btn.btn-primary"));
        continueBtn.click();

        Thread.sleep(4000); //for academic purpose
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }
}
