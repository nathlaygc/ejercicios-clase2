package practice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {
    public WebDriver driver;
    public String browser = "CHROME";

    public WebDriver initializeDriver(){
        if(browser.equals("CHROME")){
            WebDriverManager.chromedriver().setup();
            this.driver = new ChromeDriver();
        } else if(browser.equals("FIREFOX")){
            WebDriverManager.firefoxdriver().setup();
            this.driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        return driver;
    }
}
