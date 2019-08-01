package com.vytrack.tests.components.login_navigation;

import com.vytrack.utilities.SeleniumUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginTest {


    WebDriver driver;
    String usernameLocator = "prependedInput";
    String passwordLocator = "prependedInput2";
    String errorMessageDisplay = "div[class*='alert alert-error']";


    //This method will run before every method. It will set up the driver and launch the browser
    @BeforeMethod
    public void openPage() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://qa2.vytrack.com/user/login");
    }

    //This method will run after every method and add some wait time as the environment is not very stable
    @AfterMethod
    public void waitPlease() {
        SeleniumUtils.waitPlease(3);
        driver.quit();
    }

    //Login as a Store Manager
    @Test (priority = 1)
    public void storeManager() {
        driver.findElement(By.id(usernameLocator)).sendKeys("storemanager213");
        driver.findElement(By.id(passwordLocator)).sendKeys("UserUser123", Keys.ENTER);
        SeleniumUtils.waitPlease(2);
        //Store the locater of the name
       WebElement name =  driver.findElement(By.xpath("//a[@class='dropdown-toggle'][contains(text(),'Bridie Collins')]"));
       String displayedName =  driver.findElement(By.xpath("//a[@class='dropdown-toggle'][contains(text(),'Bridie Collins')]")).getText();

       //Verify the store manager is displayed
       if(name.isDisplayed()) {
            System.out.println(displayedName + " is displayed");
       } else {
           System.out.println("The name is not displayed");
       }

        //Expected page name
        String expectedPageName = "Dashboard";
        //Locate the page name
        String actualPageName = driver.findElement(By.cssSelector("h1[class='oro-subtitle']")).getText();
        Assert.assertTrue(actualPageName.contains(expectedPageName));
        SeleniumUtils.waitPlease(2);

        //Logout
        driver.findElement(By.xpath("//i[@class='fa-caret-down']")).click();
        driver.findElement(By.xpath("//a[@class='no-hash']")).click();
        SeleniumUtils.waitPlease(2);
        Assert.assertTrue(driver.getTitle().contains("Login"));


    }


    //Login as a Sales Manager
    @Test (priority = 2)
    public void salesManager() {

        driver.findElement(By.id(usernameLocator)).sendKeys("salesmanager276");
        driver.findElement(By.id(passwordLocator)).sendKeys("UserUser123", Keys.ENTER);
        SeleniumUtils.waitPlease(2);
        //Store the locater of the name
        String displayedName =  driver.findElement(By.xpath("//a[@class='dropdown-toggle'][contains(text(),'Rae Cruick')]")).getText();
        Assert.assertFalse(displayedName.equals("Bridie Collins"));

        //Expected page name
        String expectedPageName = "Dashboard";
        //Locate the page name
        String actualPageName = driver.findElement(By.cssSelector("h1[class='oro-subtitle']")).getText();
        Assert.assertTrue(actualPageName.contains(expectedPageName));
        SeleniumUtils.waitPlease(2);

        //Logout
        driver.findElement(By.xpath("//i[@class='fa-caret-down']")).click();
        driver.findElement(By.xpath("//a[@class='no-hash']")).click();
        SeleniumUtils.waitPlease(2);
        Assert.assertTrue(driver.getTitle().contains("Login"));

    }


    //Login as a Driver
    @Test (priority = 3)
    public void driver() {

        driver.findElement(By.id(usernameLocator)).sendKeys("user180");
        driver.findElement(By.id(passwordLocator)).sendKeys("UserUser123", Keys.ENTER);
        SeleniumUtils.waitPlease(2);

        //Expected page name
        String expectedPageName = "Quick Launchpad";
        //Locate the page name
        String actualPageName = driver.findElement(By.cssSelector("h1[class='oro-subtitle']")).getText();
        Assert.assertTrue(actualPageName.contains(expectedPageName));
        SeleniumUtils.waitPlease(2);

        //Locate the username on the top right of the page
        String name = driver.findElement(By.xpath("//a[@class='dropdown-toggle'][contains(text(),'Justice McCullough')]")).getText();
        Assert.assertFalse(name.contains("Rae Cruick"));
        driver.quit();
    }

    //Negative test scenerio with valid username and invalid password
    @Test (priority = 4, description = "Enter valid username and invalid password")
    public void negativeTest() {
        //valid username
        driver.findElement(By.id(usernameLocator)).sendKeys("user180");
        //invalid password
        driver.findElement(By.id(passwordLocator)).sendKeys("Useruser123", Keys.ENTER);
        String expectedErrorMessage = "Invalid user name or password.";
        System.out.println(driver.findElement(By.cssSelector(errorMessageDisplay)).getText());
        String actualErrorMessage = driver.findElement(By.cssSelector(errorMessageDisplay)).getText();

        //Verify the error message, page title and url
        Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
        Assert.assertEquals("http://qa2.vytrack.com/user/login", driver.getCurrentUrl());
        Assert.assertEquals("Login", driver.getTitle());



    }

}
