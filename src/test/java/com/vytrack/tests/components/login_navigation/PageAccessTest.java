package com.vytrack.tests.components.login_navigation;

import com.vytrack.utilities.SeleniumUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.security.Key;
import java.util.concurrent.TimeUnit;

public class PageAccessTest {
    WebDriver driver;

    String usernameLocator = "prependedInput";
    String passwordLocator = "prependedInput2";
    String fleetLocator = "(//*[@class='dropdown dropdown-level-1'])[1]";
    String contractsModuleLocator = "//*[contains(text(),'Vehicle Contracts')]";
    String errorMessage = "//*[contains(text(),'You do not have permission')]";



    //This method will run before every method. It will set up the driver and launch the browser
    @BeforeMethod
    public void openPage() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://qa2.vytrack.com/user/login");
    }


    @Test (priority = 1, description = "As a store manager user should have access to Vehicle contracts page")
    public void storeManager() {
        driver.findElement(By.id(usernameLocator)).sendKeys("storemanager213");
        driver.findElement(By.id(passwordLocator)).sendKeys("UserUser123", Keys.ENTER);
        SeleniumUtils.waitPlease(5);
        driver.findElement(By.xpath(fleetLocator)).click();
        driver.findElement(By.xpath(contractsModuleLocator)).click();
        SeleniumUtils.waitPlease(2);
        String expectedTitle = "All - Vehicle Contract - Entities - System - Car - Entities - System";
        System.out.println(driver.getTitle());
        Assert.assertTrue(expectedTitle.contains(driver.getTitle()));

    }

    @Test (priority = 2, description = "As a sales manager user should have access to Vehicle contracts page")
    public void salesManager() {
        driver.findElement(By.id(usernameLocator)).sendKeys("salesmanager276");
        driver.findElement(By.id(passwordLocator)).sendKeys("UserUser123", Keys.ENTER);
        SeleniumUtils.waitPlease(5);
        driver.findElement(By.xpath(fleetLocator)).click();
        driver.findElement(By.xpath(contractsModuleLocator)).click();
        SeleniumUtils.waitPlease(2);
        String expectedTitle = "All - Vehicle Contract - Entities - System - Car - Entities - System";
        System.out.println(driver.getTitle());
        Assert.assertTrue(expectedTitle.contains(driver.getTitle()));
    }

    @Test (priority = 3, description = "As a driver user should NOT have access to Vehicle contracts page")
    public void driver() {
        driver.findElement(By.id(usernameLocator)).sendKeys("user180");
        driver.findElement(By.id(passwordLocator)).sendKeys("UserUser123", Keys.ENTER);
        SeleniumUtils.waitPlease(5);
        driver.findElement(By.xpath(fleetLocator)).click();
        driver.findElement(By.xpath(contractsModuleLocator)).click();
        SeleniumUtils.waitPlease(2);
        String expectedMessage = "You do not have permission to perform this action.";
        System.out.println(driver.findElement(By.xpath(errorMessage)).getText());
        Assert.assertTrue(expectedMessage.contains(driver.findElement(By.xpath(errorMessage)).getText()));


    }


    @AfterMethod
    public void teardown() {
        SeleniumUtils.waitPlease(2);
        driver.quit();
    }
}
