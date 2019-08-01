package com.vytrack.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class IsDisplayed {

    static WebDriver driver = BrowserFactory.getDriver("chrome");


    public static void main(String[] args) {
        openRadioButtonPage();
        test4();
        driver.close();
    }


    public static void openRadioButtonPage() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://practice.cybertekschool.com/radio_buttons");
    }

    public static void test1() {

        // check if the blue button is selected
        WebElement blueBtn =  driver.findElement(By.id("blue"));

        if(blueBtn.isSelected()) {
            System.out.println("Blue button is selected");
        } else {
            System.out.println("Blue button is not selected");
        }

        SeleniumUtils.waitPlease(2);
    }


    public static void test2() {
        // we will check that green button is disabled which means not clikable
        // and black button is clickable
        WebElement blackBtn = driver.findElement(By.id("black"));
        WebElement greenBtn = driver.findElement(By.id("green"));

        if(blackBtn.isEnabled() && greenBtn.isDisplayed()) {
            System.out.println("PASSED");
        } else {
            System.out.println("FAILED");
        }

    }

    public static void test3() {
        WebElement blueBttn = driver.findElement(By.id("blue"));
        SeleniumUtils.waitPlease(2);

        if(blueBttn.isSelected()) {
            System.out.println("PASSED");
            System.out.println("Blue is selected");
        } else {
            System.out.println("FAILED");
            System.out.println("Blue is not selected");

        }

        WebElement blackBttn = driver.findElement(By.id("black"));
        SeleniumUtils.waitPlease(2);
        blackBttn.click();
        if(blackBttn.isSelected()) {
            System.out.println("PASSED");
            System.out.println("Black is selected");
        } else {
            System.out.println("FAILED");
            System.out.println("Black is not selected");

        }

        System.out.println("Is blue button selected? " + blueBttn.isSelected());


    }


    public static void test4() {
        // Write a test that make sure only one radio button is selected

        //Step1= LEts find all the radio buttons
        List<WebElement> radioButtons = driver.findElements(By.xpath("//input[@type='radio']"));

        // create a counter to count how many button is selected
        int counter = 0;
        // we will go thourough the list and check one by on. If a radio  button is selected, we need to increase the counter
        for(WebElement radioButton : radioButtons) {
            if(radioButton.isSelected()) {
                System.out.println(radioButton.getAttribute("id"));
                counter++;
            }

        }
        System.out.println("The amount of the the radio buttons that are selected is ===> " + counter);


    }
}
