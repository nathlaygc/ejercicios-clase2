package basicmethods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import practice.Base;

public class ExplicitWait extends Base {
    @BeforeTest
    public void initialize() {
        driver = initializeDriver();
        driver.get("https://chercher.tech/practice/explicit-wait-sample-selenium-webdriver");
    }

    @Test
    public void testElementBeClickable1() {
        //first we click on the button that will make the other button clickable in 10 seconds
        WebElement btnEnable = driver.findElement(By.id("enable-button"));
        btnEnable.click();

        //the following button will be enabled to click after 10 seconds
        WebElement btnTest = driver.findElement(By.id("disable"));

        //we initialize the wait object... the max amount it will wait is 5 seconds
        WebDriverWait wait = new WebDriverWait(driver,5);

        //we wait until the element to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(btnTest));
        btnTest.click(); //will fail, the button is enabled after 10 seconds
    }

    @Test
    public void testElementBeClickable2() {
        //first we click on the button that will make the other button clickable in 10 seconds
        WebElement btnEnable = driver.findElement(By.id("enable-button"));
        btnEnable.click();

        //the following button will be enabled to click after 10 seconds
        WebElement btnTest = driver.findElement(By.id("disable"));

        //we initialize the wait object... the max amount it will wait is 10 seconds
        WebDriverWait wait = new WebDriverWait(driver,10);

        //we wait until the element to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(btnTest));
        btnTest.click(); //will pass
    }

    @Test
    public void testVisibilityOf1() {
        //first we click on the button that will display the other button in 10 seconds
        WebElement btnDisplay = driver.findElement(By.id("display-other-button"));
        btnDisplay.click();

        //the following button will appear after 10 seconds
        WebElement btnTest = driver.findElement(By.id("hidden"));

        //we initialize the wait object... the max amount it will wait is 5 seconds
        WebDriverWait wait = new WebDriverWait(driver,5);

        //we wait until the element to be visible (we only will wait 5 seconds for this)
        wait.until(ExpectedConditions.visibilityOf(btnTest));
        btnTest.click(); //will fail
    }

    @Test
    public void testVisibilityOf2() {
        //first we click on the button that will display the other button in 10 seconds
        WebElement btnDisplay = driver.findElement(By.id("display-other-button"));
        btnDisplay.click();

        //the following button will appear after 10 seconds
        WebElement btnTest = driver.findElement(By.id("hidden"));

        //we initialize the wait object... the max amount it will wait is 10 seconds
        WebDriverWait wait = new WebDriverWait(driver,10);

        //we wait until the element to be visible (we only will wait 10 seconds for this)
        wait.until(ExpectedConditions.visibilityOf(btnTest));
        btnTest.click(); //will pass
    }

    @Test
    public void testElementToBeSelected1() {
        //first we click on the button that will check the checkbox in 10 seconds
        WebElement btnEnable = driver.findElement(By.id("checkbox"));
        btnEnable.click();

        //the following checkbox will be selected after 10 seconds
        WebElement chkbk = driver.findElement(By.id("ch"));

        //we initialize the wait object... the max amount it will wait is 5 seconds
        WebDriverWait wait = new WebDriverWait(driver,5);

        //we wait until the element to be selected (we only will wait 5 seconds for this)
        wait.until(ExpectedConditions.elementToBeSelected(chkbk)); //it will fail since only we are waiting 5 seconds
        System.out.println("Hello world"); //just to test if it reaches this line of code
    }

    @Test
    public void testElementToBeSelected2() {
        //first we click on the button that will check the checkbox in 10 seconds
        WebElement btnEnable = driver.findElement(By.id("checkbox"));
        btnEnable.click();

        //the following checkbox will be selected after 10 seconds
        WebElement chkbk = driver.findElement(By.id("ch"));

        //we initialize the wait object... the max amount it will wait is 10 seconds
        WebDriverWait wait = new WebDriverWait(driver,10);

        //we wait until the element to be selected (we only will wait 10 seconds for this)
        wait.until(ExpectedConditions.elementToBeSelected(chkbk)); //will pass
        System.out.println("Hello world"); //just to test if it reaches this line of code
    }

    @Test
    public void testTextToBePresentInElement1() {
        //first we click on the button that change the text from "site" to "Selenium Webdriver" in 10 seconds
        WebElement btnChangeText = driver.findElement(By.id("populate-text"));
        btnChangeText.click();

        //the following lbl will have "Selenium Webdriver" as text after 10 seconds
        WebElement lblDynamic = driver.findElement(By.id("h2"));

        //we initialize the wait object... the max amount it will wait is 5 seconds
        WebDriverWait wait = new WebDriverWait(driver,5);

        //we wait until the text "Selenium Webdriver" is present (we only will wait 5 seconds for this)
        wait.until(ExpectedConditions.textToBePresentInElement(lblDynamic, "Selenium Webdriver")); //it will fail since only we are waiting 5 seconds
        System.out.println(lblDynamic.getText()); //just to test if it reaches this line of code
    }

    @Test
    public void testTextToBePresentInElement2() {
        //first we click on the button that change the text from "site" to "Selenium Webdriver" in 10 seconds
        WebElement btnChangeText = driver.findElement(By.id("populate-text"));
        btnChangeText.click();

        //the following lbl will have "Selenium Webdriver" as text after 10 seconds
        WebElement lblDynamic = driver.findElement(By.id("h2"));

        //we initialize the wait object... the max amount it will wait is 10 seconds
        WebDriverWait wait = new WebDriverWait(driver,10);

        //we wait until the text "Selenium Webdriver" is present (we only will wait 10 seconds for this)
        wait.until(ExpectedConditions.textToBePresentInElement(lblDynamic, "Selenium Webdriver")); //will pass
        System.out.println(lblDynamic.getText()); //just to test if it reaches this line of code
    }

    @Test
    public void testAlertIsPresent1() {
        //first we click on the button that will open the alert in 5 seconds
        WebElement btnEnable = driver.findElement(By.id("alert"));
        btnEnable.click();

        //we initialize the wait object... the max amount it will wait is 3 seconds
        WebDriverWait wait = new WebDriverWait(driver,3);

        //we wait until alert is present (we only will wait 3 seconds for this)
        wait.until(ExpectedConditions.alertIsPresent()); //will fail
        driver.switchTo().alert().accept(); //we try close the alert
        System.out.println("Hello world"); //just to test if it reaches this line of code
    }

    @Test
    public void testAlertIsPresent2() {
        //first we click on the button that will open the alert in 5 seconds
        WebElement btnEnable = driver.findElement(By.id("alert"));
        btnEnable.click();

        //we initialize the wait object... the max amount it will wait is 5 seconds
        WebDriverWait wait = new WebDriverWait(driver,5);

        //we wait until alert is present (we only will wait 5 seconds for this)
        wait.until(ExpectedConditions.alertIsPresent()); //will pass
        driver.switchTo().alert().accept(); //we close the alert
        System.out.println("Hello world"); //just to test if it reaches this line of code
    }

    @AfterTest
    public void closeDriver() {
        driver.quit();
    }
}
