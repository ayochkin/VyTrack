package com.vytrack.tests.components.activities;

import com.vytrack.pages.activities.CalendarEventsPage;
import com.vytrack.utilities.*;
import org.openqa.selenium.support.ui.Select;
import static org.testng.Assert.*;
import org.testng.annotations.Test;


public class DateAndTimeTests extends TestBase {


    @Test(priority = 1, description = "Date time, End time auto adjust")
    public void verifyingDate(){
        CalendarEventsPage calendarEvents = new CalendarEventsPage();

        //1. Log in as Valid user
        String username = ConfigurationReader.getProperty("salesmanagerusername");
        String password = ConfigurationReader.getProperty("salesmanagerpassword");
        VYTrackUtils.login(driver,username,password);
        //2. Go to Activities -> Calendar Events
        VYTrackUtils.navigateToModule(driver,"Activities","Calendar Events");
        VYTrackUtils.waitUntilLoaderScreenDisappear(driver);
        //3. Click on create new calendar event
        calendarEvents.createCalendarEventBtn.click();
        VYTrackUtils.waitUntilLoaderScreenDisappear(driver);
        //4. Change the start date to future date
        calendarEvents.startDateLocator.click();
        Select month = new Select(calendarEvents.monthLocator);
        month.selectByVisibleText("Sep");
        Select year = new Select(calendarEvents.yearLocator);
        year.selectByVisibleText("2021");
        calendarEvents.dayLocator.click();
        //5. Verify that end date changes to the same date
        VYTrackUtils.waitUntilLoaderScreenDisappear(driver);
        SeleniumUtils.waitPlease(1);
         String startDate = calendarEvents.startDateLocator.getAttribute("value");
         String endDate = calendarEvents.endDateLocator.getAttribute("value");
        assertTrue(endDate.equals(startDate));
        //6. Change back the start date to today’s date
        calendarEvents.startDateLocator.click();
        calendarEvents.todayLocator.click();
        //7. Verify that end date changes back to today’s date
        String startDateToday = calendarEvents.startDateLocator.getAttribute("value");
        String endDateToday = calendarEvents.endDateLocator.getAttribute("value");
        assertEquals(endDateToday, startDateToday,"End date and start date doesn't match");
  }

    @Test (priority = 2, description = "Date time, End time auto adjust")
    public void VerifyingEndDateChanges(){
        CalendarEventsPage calendarEvents = new CalendarEventsPage();
        //1.Login as a Valid User
        String username = ConfigurationReader.getProperty("salesmanagerusername");
        String password = ConfigurationReader.getProperty("salesmanagerpassword");
        VYTrackUtils.login(driver,username,password);
        //2. Go to Activities -> Calendar Events
        VYTrackUtils.navigateToModule(driver,"Activities","Calendar Events");
        VYTrackUtils.waitUntilLoaderScreenDisappear(driver);
        //3. Click on create new calendar event
        calendarEvents.createCalendarEventBtn.click();
        //4. Change the start time to any other time
        VYTrackUtils.waitUntilLoaderScreenDisappear(driver);
        calendarEvents.startTimeLocator.click();
        calendarEvents.newTimeLocator.click();
        SeleniumUtils.waitPlease(5);
        //5.Verify that end time changes exactly 1 hours later
        String endTime= calendarEvents.endTimeLocator.getAttribute("value");
        assertEquals(endTime, "2:30 AM", "End time doesn't match");

    }

    @Test (priority = 3, description = "Date time, End time auto adjust")
    public void verifyingDateAndTime(){
        CalendarEventsPage calendarEvents = new CalendarEventsPage();

        //1.Login as a Valid User
        String username = ConfigurationReader.getProperty("salesmanagerusername");
        String password = ConfigurationReader.getProperty("salesmanagerpassword");
        VYTrackUtils.login(driver,username,password);
        //2. Go to Activities -> Calendar Events
        VYTrackUtils.navigateToModule(driver,"Activities","Calendar Events");
        VYTrackUtils.waitUntilLoaderScreenDisappear(driver);
        //3. Click on create new calendar event
        calendarEvents.createCalendarEventBtn.click();
        //4. Change the start time to 11:30 PM
        VYTrackUtils.waitUntilLoaderScreenDisappear(driver);
        calendarEvents.startTimeLocator.click();
        calendarEvents.startTime1130Pm.click();
        //5. Verify that end date shows tomorrows date
        assertEquals(calendarEvents.endDateLocator.getAttribute("value"),"Jul 31, 2019","End dates doesn't match!");
        //6. Verify that end Time is 12:30 AM
        String endTime =calendarEvents.endTimeLocator.getAttribute("value");
        assertEquals(endTime,"12:30 AM","End times doesn't match");

    }



}
