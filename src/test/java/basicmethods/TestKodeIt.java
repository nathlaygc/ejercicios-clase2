package basicmethods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import practice.Base;

public class TestKodeIt extends Base {
    @BeforeMethod
    public void initialize() {
        driver = initializeDriver();
        driver.get("https://courses.letskodeit.com/practice"); //every test navigates to the url
    }

    @Test
    public void testVerify() { //know as soft assert
        String actualTitle;
        String expectedTitle1 = "Practice Page";
        String expectedTitle2 = "Title Example";
        String actualURL;
        String expectedURL1 = "https://courses.letskodeit.com/practice";
        String expectedURL2 = "https://stackoverflow.com/";

        actualTitle = driver.getTitle(); //get the current page title
        actualURL = driver.getCurrentUrl(); //get the current URL

        //initialize the soft assert object
        SoftAssert softAssert = new SoftAssert();

        //verifying the web page title
        softAssert.assertEquals(expectedTitle1, actualTitle, "verify expected title 1 equals failed"); //will pass
        softAssert.assertNotEquals(expectedTitle2, actualTitle, "verify expected title 2 not equals failed"); // will pass

        //verifying the web page url
        softAssert.assertEquals(expectedURL1, actualURL, "verify expected title 1 equals failed"); //will pass
        softAssert.assertNotEquals(expectedURL2, actualURL, "verify expected title 2 not equals failed"); //will pass

        //Collates the assertion results and marks test as pass or fail
        softAssert.assertAll();
    }

    @Test
    public void testVisible(){
        //initialize the soft assert object
        SoftAssert softAssert = new SoftAssert();

        //verifying if an element is displayed
        WebElement element1 = driver.findElement(By.id("displayed-text")); //element that we will check
        softAssert.assertTrue(element1.isDisplayed()); //will pass

        //we hide the previous element by clicking the "Hide" button
        WebElement hideBtn = driver.findElement(By.id("hide-textbox"));
        hideBtn.click();

        //now we check again if it is displayed or hidden
        softAssert.assertFalse(element1.isDisplayed()); //will pass

        //we show the element by clicking the "Show" button
        WebElement showBtn = driver.findElement(By.id("show-textbox"));
        showBtn.click();

        //we check again if it is displayed
        softAssert.assertTrue(element1.isDisplayed()); //will pass

        //Collates the assertion results and marks test as pass or fail
        softAssert.assertAll();
    }

     @Test
     public void testIsSelected() throws InterruptedException {
        /*
        click on bmw radio button
        click on benz and honda checkboxes
        verify bmw radio button is checked
        verify benz and honda checkboxes are checked
        verify radio buttons and checkboxes not clicked are not selected
        */

         //first we get all 3 radio buttons
         WebElement rdBtnBMW = driver.findElement(By.id("bmwradio")); //radio button called BMW
         WebElement rdBtnBenz = driver.findElement(By.id("benzradio")); //radio button called Benz
         WebElement rdBtnHonda = driver.findElement(By.id("hondaradio")); //radio button called Honda
         //secondly we get all 3 checkboxes
         WebElement chkBMW = driver.findElement(By.id("bmwcheck")); //radio button called BMW
         WebElement chkBenz = driver.findElement(By.id("benzcheck")); //radio button called Benz
         WebElement chkHonda = driver.findElement(By.id("hondacheck")); //radio button called Honda

         //click on the radio button "BMW"
         rdBtnBMW.click();

         //click on Benz and Honda checkboxes
         chkBenz.click();
         chkHonda.click();

         Thread.sleep(4000); //for academic purpose

         //initialize the soft assert object
         SoftAssert softAssert = new SoftAssert();

         //verify if BMW radiobutton is enabled (since we have clicked on it)
         softAssert.assertTrue(rdBtnBMW.isSelected()); //will pass

         //verify that Benz and Honda radio buttons are NOT enabled (since we haven't click on them)
         softAssert.assertFalse(rdBtnBenz.isSelected()); //will pass
         softAssert.assertFalse(rdBtnHonda.isSelected()); //will pass

         //verify if Benz and Honda checkboxes are enabled (since we have clicked on them)
         softAssert.assertTrue(chkBenz.isSelected()); //will pass
         softAssert.assertTrue(chkHonda.isSelected()); //will pass

         //verify if BMW checkboxes are NOT enabled (since we haven't click on it)
         softAssert.assertFalse(chkBMW.isSelected()); //will pass

         //Collates the assertion results and marks test as pass or fail
         softAssert.assertAll();
     }

     @Test
     public void testDropDownSimple() {
         //initialize the soft assert object
         SoftAssert softAssert = new SoftAssert();

         //get the select element as we have been doing before
         WebElement selectElement = driver.findElement(By.id("carselect"));
         //initialize the select element
         Select drpBrand = new Select(selectElement);

         //get all the options to verify if they are enabled or not on the dropdown
         WebElement optBMW = driver.findElement(By.xpath("//*[@id=\"carselect\"]/option[1]")); //BMW
         WebElement optBenz = driver.findElement(By.xpath("//*[@id=\"carselect\"]/option[2]")); //Benz
         WebElement optHonda = driver.findElement(By.xpath("//*[@id=\"carselect\"]/option[3]")); //Honda

         //we can select an option by visible text, value or index

         //select Benz by visible text
         drpBrand.selectByVisibleText("Benz");
         softAssert.assertTrue(optBenz.isSelected()); //will pass

         //select Honda by value
         drpBrand.selectByValue("honda"); //have to inspect <option value="honda">Honda</option>
         softAssert.assertTrue(optHonda.isSelected()); //will pass

         //select BMW by index
         drpBrand.selectByIndex(0); //starts at 0... we use 0 since is the first on the list
         softAssert.assertTrue(optBMW.isSelected()); //will pass

         //Collates the assertion results and marks test as pass or fail
         softAssert.assertAll();

         /*
         equivalents for this example we can choose one of the 3
         //BMW option
         drpBrand.selectByVisibleText("BMW");
         drpBrand.selectByIndex(0);
         drpBrand.selectByValue("bmw");

         //Benz option
         drpBrand.selectByVisibleText("Benz");
         drpBrand.selectByIndex(1);
         drpBrand.selectByValue("benz");

         //Honda option
         drpBrand.selectByVisibleText("Honda");
         drpBrand.selectByIndex(2);
         drpBrand.selectByValue("honda");
          */
     }

    @Test
    public void testDropDownMultiple() {
        //initialize the soft assert object
        SoftAssert softAssert = new SoftAssert();

        //get the select element as we have been doing before
        WebElement selectElement = driver.findElement(By.id("multiple-select-example"));
        //initialize the select element
        Select drpFruits = new Select(selectElement);

        //get all the options to verify if they are enabled or not on the dropdown
        WebElement optApple = driver.findElement(By.xpath("//*[@id=\"multiple-select-example\"]/option[1]")); //Apple
        WebElement optOrange = driver.findElement(By.xpath("//*[@id=\"multiple-select-example\"]/option[2]")); //Orange
        WebElement optPeach = driver.findElement(By.xpath("//*[@id=\"multiple-select-example\"]/option[3]")); //Peach

        //we can select an option by visible text, value or index

        //select orange by visible text
        drpFruits.selectByVisibleText("Orange");
        softAssert.assertTrue(optOrange.isSelected()); //will pass
        //deselect by visible text
        drpFruits.deselectByVisibleText("Orange");
        softAssert.assertFalse(optOrange.isSelected()); //will pass

        //select peach by value
        drpFruits.selectByValue("peach"); //have to inspect <option value="peach">Peach</option>
        softAssert.assertTrue(optPeach.isSelected()); //will pass
        //deselect peach by value
        drpFruits.deselectByValue("peach");
        softAssert.assertFalse(optPeach.isSelected()); //will pass

        //select apple by index
        drpFruits.selectByIndex(0); //starts at 0... we use 0 since is the first on the list
        softAssert.assertTrue(optApple.isSelected()); //will pass
        //deselect apple by index
        drpFruits.deselectByIndex(0); //starts at 0... we use 0 since is the first on the list
        softAssert.assertFalse(optApple.isSelected()); //will pass

        //now we select all options
        drpFruits.selectByIndex(0); //we select apple
        softAssert.assertTrue(optApple.isSelected()); //will pass
        drpFruits.selectByIndex(1); //we select orange
        softAssert.assertTrue(optOrange.isSelected()); //will pass
        drpFruits.selectByIndex(2); //we select peach
        softAssert.assertTrue(optPeach.isSelected()); //will pass

        //now we deselect all options
        drpFruits.deselectAll();
        softAssert.assertFalse(optApple.isSelected()); //will pass
        softAssert.assertFalse(optOrange.isSelected()); //will pass
        softAssert.assertFalse(optPeach.isSelected()); //will pass

        //Collates the assertion results and marks test as pass or fail
        softAssert.assertAll();

         /*
         equivalents for this example we can choose 1 of 3, 2 of 3 or all of them
         //Apple option
         drpBrand.selectByVisibleText("Apple");
         drpBrand.selectByIndex(0);
         drpBrand.selectByValue("apple");

         //Orange option
         drpBrand.selectByVisibleText("Orange");
         drpBrand.selectByIndex(1);
         drpBrand.selectByValue("orange");

         //Peach option
         drpBrand.selectByVisibleText("Peach");
         drpBrand.selectByIndex(2);
         drpBrand.selectByValue("peach");
          */
    }

     @Test
     public void testAssert(){ //know as hard assert
         String actualTitle;
         String expectedTitle = "Practice Page";
         String actualURL;
         String expectedURL1 = "https://courses.letskodeit.com/practice";
         String expectedURL2 = "https://stackoverflow.com/";

         actualTitle = driver.getTitle(); //get the current page title
         actualURL = driver.getCurrentUrl(); //get the current URL

         Assert.assertEquals(expectedURL1, actualURL); //will pass
         Assert.assertEquals(expectedTitle, actualTitle); //will pass

         Assert.assertEquals(expectedURL2, actualURL); //will fail and stop execution

         //we will notice the execution stops and dont run the following steps
         System.out.println("bla bla bla");
         System.out.println("ble ble ble");
     }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }
}
