package com.vytrack.utilities;

import org.openqa.selenium.By;

public class DateAndTimePage {

    public String userNameLocator = "prependedInput";
    public String passwordLocator = "prependedInput2";
    public String submitBtnLocator = "_submit";
    public String activitiesLocator = "li[class='dropdown dropdown-level-1']:nth-child(3)"; //css
    public String calanderEventsLocator = "//span[contains(text(),'Calendar Events')]"; // xpath
    public String createCalenderEventBtn = "a[title='Create Calendar event']"; // css
    public String startLocator = "input[id^='date_selector_oro_calendar_event_form_start']"; //css
    public String currentDay = "td[class='ui-datepicker-week-end ui-datepicker-days-cell-over ui-datepicker-current-day ui-datepicker-today']"; //css
    public String endDayLocator = "input[id^='date_selector_oro_calendar_event_form_end']";//css
    public String allDaysLocator = "td[data-handler='selectDay']"; //css
    public String startTimeLocator = "input[id^=time_selector_oro_calendar_event_form_start]"; //css
    public String endTimeLocator = "input[id^=time_selector_oro_calendar_event_form_end]"; //css
    public String allTimeLocator = "ul[class=ui-timepicker-list] li"; // css



    public void login(String username, String password) {
        Driver.getDriver().findElement(By.id(userNameLocator)).sendKeys(username);
        Driver.getDriver().findElement(By.id(passwordLocator)).sendKeys(password);
        Driver.getDriver().findElement(By.id(submitBtnLocator)).click();
    }
}
