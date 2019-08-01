package com.vytrack.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {

    //I created this class based on the Singleton Pattern which is one driver across the entire project


    private Driver() { //make the constructor private so that it will not be instantiated

    }

    private static WebDriver driver;

    public static WebDriver getDriver() {

        if(driver == null) {
            if(ConfigurationReader.getProperty("browser").equals("chrome")) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            } else if (ConfigurationReader.getProperty("browser").equals("firefox")) {
                WebDriverManager.chromedriver().setup();
                driver = new FirefoxDriver();
            } else {
                throw new RuntimeException("Wrong browser name!!!");
            }
        }

        return driver;
    }


    //in order to kill the driver, we use the following method
    public static void closeDriver() {
        if(driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
