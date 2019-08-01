package com.vytrack.tests.components.activities;

import com.vytrack.tests.TestBase;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.DateAndTimePage;
import com.vytrack.utilities.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DateAndTimeTests extends TestBase {

    DateAndTimePage dateAndTimePage = new DateAndTimePage();

    /*
    1. Log in as Valid user
2. Go to Activities -> Calendar Events
3. Click on create new calendar event
4. Change the start date to future date
5. Verify that end date changes to the same date
6. Change back the start date to today’s date
7. Verify that end date changes back to today’s date

     */

    @Test (priority = 1, description = "Verify that end date changes to the same date")
    public void test1() {
        String username = ConfigurationReader.getProperty("driverusername");
        String password = ConfigurationReader.getProperty("password");
        dateAndTimePage.login(username, password);
        SeleniumUtils.waitPlease(2);
        driver.findElement(By.cssSelector(dateAndTimePage.activitiesLocator)).click();
        driver.findElement(By.xpath(dateAndTimePage.calanderEventsLocator)).click();
        SeleniumUtils.waitPlease(3);
        driver.findElement(By.cssSelector(dateAndTimePage.createCalenderEventBtn)).click();
        SeleniumUtils.waitPlease(2);
        driver.findElement(By.cssSelector(dateAndTimePage.startLocator)).click();
        SeleniumUtils.waitPlease(2);
        String currentDay = driver.findElement(By.cssSelector(dateAndTimePage.startLocator)).getAttribute("value");
        System.out.println("The local day, by default  is " + currentDay);
        //Get the current day by using LocalDate method
        String localDay = LocalDate.now().format(DateTimeFormatter.ofPattern("d"));
        int local = Integer.parseInt(localDay);

        //Store all days in storeDays WebElement
        List<WebElement> allDays = driver.findElements(By.cssSelector("td[data-handler='selectDay']"));
        System.out.println("Days in the current month " + allDays.size());

        //Choose a day that is bigger than the current day (future day)
        for(int i = 1; i <= allDays.size(); i++) {
            String nums = allDays.get(i).getText();
            int num = Integer.parseInt(nums);
            if(num > local) {
                allDays.get(i).click();
                SeleniumUtils.waitPlease(2);
                break;
            }

        }

        //Print out the updated day.
        String startDay = driver.findElement(By.cssSelector(dateAndTimePage.startLocator)).getAttribute("value");
        System.out.println("We choose a future day  which is " + startDay);

        //Get the value of end date box
        String endDay = driver.findElement(By.cssSelector(dateAndTimePage.endDayLocator)).getAttribute("value");
        System.out.println("The end day is " + endDay);

        //Now we are verifying that the end date is changing to the same date as start date
        Assert.assertEquals(startDay, endDay, "The days do not match");



        //Choose the current day from the calander
        driver.findElement(By.cssSelector(dateAndTimePage.startLocator)).click();


        //Store all days in storeDays WebElement
        List<WebElement> storeDays = driver.findElements(By.cssSelector("td[data-handler='selectDay']"));
        System.out.println("Days in the current month " + storeDays.size());

        //Choose a day that is bigger than the current day (future day)
        for(int i = 1; i <= storeDays.size(); i++) {
            String nums = storeDays.get(i).getText();
            int num = Integer.parseInt(nums);
            if(num == local) {
                storeDays.get(i).click();
                SeleniumUtils.waitPlease(2);
                break;
            }

        }

        //change back the start date to todays date
        String newStartDay = driver.findElement(By.cssSelector(dateAndTimePage.startLocator)).getAttribute("value");
        System.out.println("The new start day is " + newStartDay);

        //New end day should be changed to the new start day
        String newEndDay = driver.findElement(By.cssSelector(dateAndTimePage.endDayLocator)).getAttribute("value");
        System.out.println("The new end day is " + newEndDay);

        //Verify that end date changes back to todays date
        Assert.assertEquals(newStartDay, newEndDay, "The days do not match");
    }



    /*

1. Log in as Valid user
2. Go to Activities -> Calendar Events
3. Click on create new calendar event
4. Change the start time to any other time
5. Verify that end time changes exactly 1 hours later


     */
    @Test (priority = 2, description = "Verify that end time changes to exactly 1 hour later")
    public void test2() {
        String username = ConfigurationReader.getProperty("driverusername");
        String password = ConfigurationReader.getProperty("password");
        dateAndTimePage.login(username, password);
        SeleniumUtils.waitPlease(2);
        driver.findElement(By.cssSelector(dateAndTimePage.activitiesLocator)).click();
        driver.findElement(By.xpath(dateAndTimePage.calanderEventsLocator)).click();
        SeleniumUtils.waitPlease(3);
        driver.findElement(By.cssSelector(dateAndTimePage.createCalenderEventBtn)).click();
        SeleniumUtils.waitPlease(2);
        //click the start time box
        driver.findElement(By.cssSelector(dateAndTimePage.startTimeLocator)).click();
        //Store all time slots
        List<WebElement> allTimeSlots = driver.findElements(By.cssSelector(dateAndTimePage.allTimeLocator));
        allTimeSlots.get(40).click();
        String currentTime = driver.findElement(By.cssSelector(dateAndTimePage.startTimeLocator)).getAttribute("value");
        System.out.println("The selected current time is " + currentTime);
        String endTime = driver.findElement(By.cssSelector(dateAndTimePage.endTimeLocator)).getAttribute("value");
        System.out.println("The end time is " + endTime);

        String[] str1 = endTime.split(" ");


        //Verify that end time changes exactly 1 hours later
        Assert.assertTrue(endTime.contains("9:00 PM"));



    }







}
