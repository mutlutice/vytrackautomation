package com.vytrack.tests.smoke_tests;

import com.vytrack.utilities.SeleniumUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.security.Key;
import java.util.concurrent.TimeUnit;

public class MenuOptionsTest {

    WebDriver driver;

    // Since we have two different types of users, we created two different groups  for different credentials
    // Login to Vytrack as a driver
    @BeforeGroups (groups = {"driver"})
    public void setUpDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://qa2.vytrack.com/");
        // Find location for the username
        driver.findElement(By.id("prependedInput")).sendKeys("user180");
        SeleniumUtils.waitPlease(2);
        // Find the location for the password
        driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123", Keys.ENTER);
        SeleniumUtils.waitPlease(2);

    }

    // Test1 : Navigate to Fleet - Vehicles and verify that the page name and page title match with expected information
    @Test (groups = {"driver"})
    public void driverTest1() {
        SeleniumUtils.waitPlease(3);
        //Locate the Fleet Tab
        driver.findElement(By.xpath("(//li[@class='dropdown dropdown-level-1'])[1]")).click();
        SeleniumUtils.waitPlease(3);
        //Locate the Vehicle module
        driver.findElement(By.xpath("//span[@class='title title-level-2' and contains(text(), 'Vehicles')]")).click();
        SeleniumUtils.waitPlease(3);

        // Verify the page title matches
        String expectedPageTitle = "Car - Entities - System - Car - Entities - System";
        String actualPageTitle = driver.getTitle();
        System.out.println("Expected page title is: " + expectedPageTitle);
        System.out.println("Actual Page title is: " + actualPageTitle);
        SeleniumUtils.waitPlease(3);
        Assert.assertEquals(expectedPageTitle, actualPageTitle);

        // Verify that the page name matches
       SeleniumUtils.waitPlease(3);
        String expectedPageName = "All Cars";
        System.out.println("Expected page name is: " + expectedPageName);
        String actualPageName = driver.findElement(By.xpath("//*[@class='oro-subtitle']")).getText();
        System.out.println("Actual page name is: " + actualPageName);
        Assert.assertEquals(expectedPageName, actualPageName);


    }

    // Test2: Navigate to Customers -> Accounts and verify that page title and page name is matching with given information
    @Test (groups = {"driver"})
    public void driverTest2() {
        // Locate Customers tab
        SeleniumUtils.waitPlease(3);
        driver.findElement(By.xpath("//li[@class='dropdown dropdown-level-1'][2]")).click();
        SeleniumUtils.waitPlease(3);
        //Locate the Accounts module
        driver.findElement(By.xpath("//span[@class='title title-level-2'][contains(text(), 'Accounts')]")).click();
        SeleniumUtils.waitPlease(3);

        // Verify the page title is matching
       String expectedPageTitle = "Accounts - Customers";
       String actualPageTitle = driver.getTitle();
        System.out.println("Expected page title is: " + expectedPageTitle);
        System.out.println("Actual page title is: " + actualPageTitle);
       Assert.assertEquals(expectedPageTitle, actualPageTitle);
       SeleniumUtils.waitPlease(3);

       // Verify that the page name is matching
        String expectedPageName = "Accounts";
        String actualPageName = driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).getText();
        Assert.assertEquals(expectedPageName, actualPageName);
        System.out.println("Expected page name is " + expectedPageName);
        System.out.println("Actual page name is " + actualPageName);
        SeleniumUtils.waitPlease(5); // it was originally 20 before Ahmet messed it up :)


    }


    //Test3: Navigate to Customers Tab --> Contacts and verify that the page name and title is matching
    @Test (groups = {"driver"})
    public void driverTest3() {

        //Locate the Curstomers tab
        driver.findElement(By.xpath("(//span[@class='title title-level-1'])[2]")).click();
        SeleniumUtils.waitPlease(3);
        //Locate contacts module
        driver.findElement(By.xpath("//span[@class='title title-level-2'][contains(text(),'Contacts')]")).click();
        SeleniumUtils.waitPlease(3);

        //Verify that the page title is matching
        String expectedPageTitle = "Contacts - Customers";
        String actualPageTitle = driver.getTitle();
        System.out.println("Expected page title is: " + expectedPageTitle);
        System.out.println("Actual page title is: " + actualPageTitle);
        Assert.assertEquals(expectedPageTitle, actualPageTitle);
        SeleniumUtils.waitPlease(3);

       //Verify that the page name is mathcing
        String expectedPageName = "Contacts";
        String actualPageName = driver.findElement(By.cssSelector("h1[class='oro-subtitle']")).getText();
        Assert.assertEquals(expectedPageName, actualPageName);
        System.out.println("Expected page name is " + expectedPageName);
        System.out.println("Actual page name is " + actualPageName);
        SeleniumUtils.waitPlease(2);


    }

    //Test4: Navigate to Activities Tab --> Calendar Events verify that the page title and page name is matching
    @Test (groups = {"driver"})
    public void driverTest4() {

        //Locate the Activities Tab
        driver.findElement(By.xpath("(//span[@class='title title-level-1'])[3]")).click();
        SeleniumUtils.waitPlease(3);
        //Locate Calendar Events modules
        driver.findElement(By.xpath("//span[@class='title title-level-2'][contains(text(),'Calendar')]")).click();
        SeleniumUtils.waitPlease(3);
        //Verify that page title is matching
        String expectedPageTitle = "Calendar Events - Activities";
        String actualPageTitle = driver.getTitle();
        System.out.println("Expected page title is: " + expectedPageTitle);
        System.out.println("Actual page title is: " + actualPageTitle);
        Assert.assertEquals(expectedPageTitle, actualPageTitle);

        //Verify that page name is matching
        String expectedPageName = "Calendar Events";
        String actualPageName = driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).getText();
        System.out.println("Expected page name is " + expectedPageName);
        System.out.println("Actual page name is " + actualPageName);
        Assert.assertEquals(expectedPageName, actualPageName);
        driver.quit();

    }



    //Login to vytrack website as StoreManager
    @BeforeGroups (groups = {"storeManager"})
    public void storeManagerSetUp() {
        SeleniumUtils.waitPlease(5);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://qa2.vytrack.com/user/login");
        //locate the username
        driver.findElement(By.id("prependedInput")).sendKeys("storemanager213");
        //locate the password
        driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123", Keys.ENTER);
        SeleniumUtils.waitPlease(5);
    }

    // Navigate to Dashboard Tab --->  Dashboard Module verify that the page title and page name is matching
    @Test (groups = {"storeManager"})
    public void storeManagerTest1() {
        SeleniumUtils.waitPlease(2);
        //Locate the Dashboard tab
        driver.findElement(By.xpath("//li[@class='dropdown dropdown-level-1 first']")).click();
        //Locate the Dashboard module
        driver.findElement(By.xpath("(//span[@class='title title-level-2'][contains(text(),'Dashboard')])[1]")).click();
        SeleniumUtils.waitPlease(2);

        //Verify that page title is matching
        String expectedPageTitle = "Dashboard - Dashboards";
        String actualPageTitle = driver.getTitle();
        System.out.println("Expected page title is: " + expectedPageTitle);
        System.out.println("Actual page title is: " + actualPageTitle);
        Assert.assertEquals(expectedPageTitle, actualPageTitle);

        //Verify that page name is matching
        String expectedPageName = "Dashboard";
        String actualPageName = driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).getText();
        System.out.println("Expected page name is: " + expectedPageName);
        System.out.println("Actual page name is: " + actualPageName);
        Assert.assertEquals(expectedPageName, actualPageName);

    }

    // Navigate to Fleet Tab --> Vehicle Module verify that the page title and page name is matching
    @Test (groups = {"storeManager"})
    public void storeManagerTest2() {
        SeleniumUtils.waitPlease(5);
        //Locate Fleet tab
        driver.findElement(By.xpath("//li[@class='dropdown dropdown-level-1'][1]")).click();
        //Locate the vehicle module
        driver.findElement(By.xpath("//span[@class='title title-level-2'][contains(text(),'Vehicle')]")).click();
        SeleniumUtils.waitPlease(5);

        //Verify that page title is matching
        String expectedPageTitle = "All - Car - Entities - System - Car - Entities - System";
        String actualPageTitle = driver.getTitle();
        System.out.println("Expected page title is: " + expectedPageTitle);
        System.out.println("Actual page title is: " + actualPageTitle);
        Assert.assertEquals(expectedPageTitle, actualPageTitle);

        //Verify that page name is matching
        String expectedPageName = "All Cars";
        String actualPageName = driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).getText();
        System.out.println("Expected page name is: " + expectedPageName);
        System.out.println("Actual page name is: " + actualPageName);
        Assert.assertTrue(expectedPageName.contains(actualPageName));

    }


    //Navigate to Customers Tab --> Accounts Moduel verify that page title and page name is matching
    @Test (groups = {"storeManager"})
    public void storeManagerTest3() {
        SeleniumUtils.waitPlease(3);
        //Locate Customers Tab
        driver.findElement(By.xpath("//li[@class='dropdown dropdown-level-1'][2]")).click();
        //Locate Account Module
        driver.findElement(By.xpath("//*[@class='title title-level-2'][contains(text(), 'Acco')]")).click();
        SeleniumUtils.waitPlease(4);

        //Verify that the page title is matching
        String expectedPageTitle = "All - Accounts - Customers";
        String actualPageTitle = driver.getTitle();
        System.out.println("Expected page title is: " + expectedPageTitle);
        System.out.println("Actual page title is: " + actualPageTitle);
        Assert.assertTrue(actualPageTitle.contains(expectedPageTitle));


        //Verify that page name is matching
        String expectedPageName = "All Accounts";
        String actualPageName = driver.findElement(By.className("oro-subtitle")).getText();
        System.out.println("Expected page name is: " + expectedPageName);
        System.out.println("Actual page name is: " + actualPageName);
        Assert.assertTrue(expectedPageName.contains(actualPageName));

    }

    //Navigate to Customers ---> Contacts and verify that the page title and page name is matching
    @Test (groups = {"storeManager"})
    public void storeManagerTest4() {
        SeleniumUtils.waitPlease(5);
        //Locate Customers tab
        driver.findElement(By.xpath("(//span[@class='title title-level-1'])[3]")).click();
        //Locate Contacts module
        driver.findElement(By.xpath("//*[@class='title title-level-2'][contains(text(),'Contacts')]")).click();
        SeleniumUtils.waitPlease(3);



        //Verify that the page title is matching
        String expectedPageTitle = "All - Contacts - Customers";
        String actualPageTitle = driver.getTitle();
        System.out.println("Expected page title is: " + expectedPageTitle);
        System.out.println("Actual page title is: " + actualPageTitle);
        Assert.assertTrue(actualPageTitle.contains(expectedPageTitle));


        //Verify that page name is matching
        String expectedPageName = "All Contacts";
        String actualPageName = driver.findElement(By.className("oro-subtitle")).getText();
        System.out.println("Expected page name is: " + expectedPageName);
        System.out.println("Actual page name is: " + actualPageName);
        Assert.assertTrue(expectedPageName.contains(actualPageName));

    }

    //Navigate to Sales ---> Opprtunitiess and verify that the page title and page name is matching
    @Test (groups = {"storeManager"})
    public void storeManagerTest5() {
        SeleniumUtils.waitPlease(5);
        //Locate Sales tab
        driver.findElement(By.xpath("(//span[@class='title title-level-1'])[4]")).click();
        //Locate Opportunities module
        driver.findElement(By.xpath("//*[@class='title title-level-2'][contains(text(),'Opportunities')]")).click();
        SeleniumUtils.waitPlease(3);



        //Verify that the page title is matching
        String expectedPageTitle = "Open Opportunities - Opportunities - Sales";
        String actualPageTitle = driver.getTitle();
        System.out.println("Expected page title is: " + expectedPageTitle);
        System.out.println("Actual page title is: " + actualPageTitle);
        Assert.assertTrue(actualPageTitle.contains(expectedPageTitle));


        //Verify that page name is matching
        String expectedPageName = "Open Opportunities";
        String actualPageName = driver.findElement(By.className("oro-subtitle")).getText();
        System.out.println("Expected page name is: " + expectedPageName);
        System.out.println("Actual page name is: " + actualPageName);
        Assert.assertTrue(expectedPageName.contains(actualPageName));

    }

    //Navigate to Activities ---> Calls and verify that the page title and page name is matching
    @Test (groups = {"storeManager"})
    public void storeManagerTest6() {
        SeleniumUtils.waitPlease(5);
        //Locate Activities tab
        driver.findElement(By.xpath("(//span[@class='title title-level-1'])[5]")).click();
        //Locate Calls module
        driver.findElement(By.xpath("//*[@class='title title-level-2'][contains(text(),'Calls')]")).click();
        SeleniumUtils.waitPlease(3);



        //Verify that the page title is matching
        String expectedPageTitle = "All - Calls - Activities";
        String actualPageTitle = driver.getTitle();
        System.out.println("Expected page title is: " + expectedPageTitle);
        System.out.println("Actual page title is: " + actualPageTitle);
        Assert.assertTrue(actualPageTitle.contains(expectedPageTitle));


        //Verify that page name is matching
        String expectedPageName = "All Calls";
        String actualPageName = driver.findElement(By.className("oro-subtitle")).getText();
        System.out.println("Expected page name is: " + expectedPageName);
        System.out.println("Actual page name is: " + actualPageName);
        Assert.assertTrue(expectedPageName.contains(actualPageName));

    }

    //Navigate to Activities ---> Calendar Events and verify that the page title and page name is matching
    @Test (groups = {"storeManager"})
    public void storeManagerTest7() {
        SeleniumUtils.waitPlease(5);
        //Locate Activities tab
        driver.findElement(By.xpath("(//span[@class='title title-level-1'])[5]")).click();
        //Locate Calendar Events module
        driver.findElement(By.xpath("//*[@class='title title-level-2'][contains(text(),'Calendar Events')]")).click();
        SeleniumUtils.waitPlease(3);



        //Verify that the page title is matching
        String expectedPageTitle = "Calendar Events - Activities";
        String actualPageTitle = driver.getTitle();
        System.out.println("Expected page title is: " + expectedPageTitle);
        System.out.println("Actual page title is: " + actualPageTitle);
        Assert.assertTrue(actualPageTitle.contains(expectedPageTitle));


        //Verify that page name is matching
        String expectedPageName = "All Calendar Events";
        String actualPageName = driver.findElement(By.className("oro-subtitle")).getText();
        System.out.println("Expected page name is: " + expectedPageName);
        System.out.println("Actual page name is: " + actualPageName);
        Assert.assertTrue(expectedPageName.contains(actualPageName));
        driver.quit();
    }


}


