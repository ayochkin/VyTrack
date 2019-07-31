package com.vytrack.tests.components.activities;

import com.vytrack.pages.activities.CalendarEventsPage;
import com.vytrack.utilities.*;

import static org.testng.Assert.*;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class DailyRepeatTests extends TestBase {

    @Test(priority = 1, description = "Daily repeat option, Repeat every, summary")
    public void verifyingDailyOptions() {
        CalendarEventsPage calendarEvents = new CalendarEventsPage();

        //1. Log in as Valid user
        String username = ConfigurationReader.getProperty("salesmanagerusername");
        String password = ConfigurationReader.getProperty("salesmanagerpassword");
        VYTrackUtils.login(driver, username, password);
        //2. Go to Activities -> Calendar Events
        VYTrackUtils.navigateToModule(driver, "Activities", "Calendar Events");
        //3. Click on create new calendar event
        VYTrackUtils.waitUntilLoaderScreenDisappear(driver);
        calendarEvents.createCalendarEventBtn.click();
        //4. Click on RepeatCheckbox
        VYTrackUtils.waitUntilLoaderScreenDisappear(driver);
        calendarEvents.repeatCheckboxLocator.click();
        //5. Verify that Daily is selected by default
        String daily = calendarEvents.repeatsInputLocator.getText();
        assertTrue(daily.contains("Daily"));
        //6. Verify day(s) checkbox is selected and default value is 1
        assertTrue(calendarEvents.daysCheckboxLocator.isSelected());
        //BrowserUtils.waitForVisibility(calendarEvents.repeatEveryDayInputLocator,10);
        // String dayValue = calendarEvents.repeatEveryDayInputLocator.getAttribute("value");
        //assertEquals(dayValue,"1", "Days value didn't match");
        //7. Verify summary says Daily every 1 day
        String summary = calendarEvents.summaryLocator.getText();
        assertEquals(summary, "Daily every 1 day", "Summary didn't match");
        //8. Check the weekday checkbox
        VYTrackUtils.waitUntilLoaderScreenDisappear(driver);
        calendarEvents.weekdayCheckboxLocator.click();
        //9. Verify that days input now disabled
        SeleniumUtils.waitPlease(4);
        // assertFalse(calendarEvents.daysCheckboxLocator.isEnabled());
        //10. Verify summary says Daily every weekday
        String summary2 = calendarEvents.summaryLocator.getText();
        assertEquals(summary2, "Daily every weekday", "Summary didn't match");
    }

    @Test(priority = 2, description = "Daily repeat option, Repeat every, default values")
    public void verifyingSummary() {
        CalendarEventsPage calendarEvents = new CalendarEventsPage();

        //1. Log in as Valid user
        String username = ConfigurationReader.getProperty("salesmanagerusername");
        String password = ConfigurationReader.getProperty("salesmanagerpassword");
        VYTrackUtils.login(driver, username, password);
        //2. Go to Activities -> Calendar Events
        VYTrackUtils.navigateToModule(driver, "Activities", "Calendar Events");
        //3. Click on create new calendar event
        VYTrackUtils.waitUntilLoaderScreenDisappear(driver);
        calendarEvents.createCalendarEventBtn.click();
        //4. Click on RepeatCheckbox
        VYTrackUtils.waitUntilLoaderScreenDisappear(driver);
        calendarEvents.repeatCheckboxLocator.click();
        //5. Verify that Daily is selected by default
        String daily = calendarEvents.repeatsInputLocator.getText();
        assertTrue(daily.contains("Daily"));
        //6. Verify day(s) checkbox is selected and default value is 1
        assertTrue(calendarEvents.daysCheckboxLocator.isSelected());
        BrowserUtils.waitForVisibility(calendarEvents.repeatEveryDayInputLocator, 10);
        String dayValue = calendarEvents.repeatEveryDayInputLocator.getAttribute("value");
        assertEquals(dayValue, "1", "Days value didn't match");
        //7. Verify summary says Daily every 1 day
        String summary = calendarEvents.summaryLocator.getText();
        assertEquals(summary, "Daily every 1 day", "Summary didn't match");
    }

    @Test(priority = 3, description = "Daily repeat option, Repeat every day(s), error messages")
    public void verifyingErrorMessages() {
        CalendarEventsPage calendarEvents = new CalendarEventsPage();

        //1. Log in as Valid user
        String username = ConfigurationReader.getProperty("salesmanagerusername");
        String password = ConfigurationReader.getProperty("salesmanagerpassword");
        VYTrackUtils.login(driver, username, password);
        //2. Go to Activities -> Calendar Events
        VYTrackUtils.navigateToModule(driver, "Activities", "Calendar Events");
        //3. Click on create new calendar event
        VYTrackUtils.waitUntilLoaderScreenDisappear(driver);
        calendarEvents.createCalendarEventBtn.click();
        //4. Click on RepeatCheckbox
        VYTrackUtils.waitUntilLoaderScreenDisappear(driver);
        calendarEvents.repeatCheckboxLocator.click();
        //5. Test the day(s) input entering different values(boundary value analysis)
        //6. Verify error messages occur when values are too big or small
        // "The value have not to be less than 1.","The value have not to be more than 99."
        SeleniumUtils.waitPlease(5);
        calendarEvents.repeatEveryDayInputLocator.clear();
        calendarEvents.repeatEveryDayInputLocator.sendKeys("0");
        String errorMessage = calendarEvents.errorMessage1.getText();
        assertEquals(errorMessage, "The value have not to be less than 1.", "Messages didn't match");
        calendarEvents.repeatEveryDayInputLocator.clear();
        calendarEvents.repeatEveryDayInputLocator.sendKeys("100 ");
        BrowserUtils.waitForVisibility(calendarEvents.errorMessage1, 10);
        String errorMessage2 = calendarEvents.errorMessage1.getText();
        assertEquals(errorMessage2, "The value have not to be more than 99.");
        //7. Verify that error messages disappear when valid values are entered
        calendarEvents.repeatEveryDayInputLocator.clear();
        calendarEvents.repeatEveryDayInputLocator.sendKeys("2");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.invisibilityOf(calendarEvents.errorMessage1));
        assertFalse(calendarEvents.errorMessage1.isDisplayed());
    }


    @Test(priority = 4, description = "Daily repeat option, Repeat every day(s), functionality")
    public void verifyingFunctionality() {
        CalendarEventsPage calendarEvents = new CalendarEventsPage();

        //1. Log in as Valid user
        String username = ConfigurationReader.getProperty("salesmanagerusername");
        String password = ConfigurationReader.getProperty("salesmanagerpassword");
        VYTrackUtils.login(driver, username, password);
        //2. Go to Activities -> Calendar Events
        VYTrackUtils.navigateToModule(driver, "Activities", "Calendar Events");
        //3. Click on create new calendar event
        VYTrackUtils.waitUntilLoaderScreenDisappear(driver);
        calendarEvents.createCalendarEventBtn.click();
        //4. Click on RepeatCheckbox
        VYTrackUtils.waitUntilLoaderScreenDisappear(driver);
        calendarEvents.repeatCheckboxLocator.click();
        //5. Enter random value to the day(s) field
        calendarEvents.repeatEveryDayInputLocator.clear();
        calendarEvents.repeatEveryDayInputLocator.sendKeys("4");
        calendarEvents.repeatEveryDayInputLocator.click();
        //6. Verify that summary says Daily every <random number> day
        BrowserUtils.waitForVisibility(calendarEvents.summaryLocator, 10);
        String summary = calendarEvents.summaryLocator.getText();
        assertEquals(summary,"Daily every 4 days", "Summary didn't match");
        //7. Enter another random value to the day(s) field
        calendarEvents.repeatEveryDayInputLocator.clear();
        calendarEvents.repeatEveryDayInputLocator.sendKeys("13");
        calendarEvents.repeatEveryDayInputLocator.click();
        //8. Verify that summary updated with Daily every <random number> day
        String summaryUpdate = calendarEvents.summaryLocator.getText();
        assertEquals(summaryUpdate,"Daily every 13 days", "Summary didn't match");
    }

    @Test(priority = 5, description = "Daily repeat option, blank fields")
    public void verifyingMessage() {
        CalendarEventsPage calendarEvents = new CalendarEventsPage();

        //1. Log in as Valid user
        String username = ConfigurationReader.getProperty("salesmanagerusername");
        String password = ConfigurationReader.getProperty("salesmanagerpassword");
        VYTrackUtils.login(driver, username, password);
        //2. Go to Activities -> Calendar Events
        VYTrackUtils.navigateToModule(driver, "Activities", "Calendar Events");
        //3. Click on create new calendar event
        VYTrackUtils.waitUntilLoaderScreenDisappear(driver);
        calendarEvents.createCalendarEventBtn.click();
        //4. Click on RepeatCheckbox
        VYTrackUtils.waitUntilLoaderScreenDisappear(driver);
        calendarEvents.repeatCheckboxLocator.click();
        //5. Clear the value of the day(s) field
        calendarEvents.repeatEveryDayInputLocator.clear();
        //6. Message "This value should not be blank." should come up
        assertTrue(calendarEvents.errorMessage1.isDisplayed());
        assertEquals(calendarEvents.errorMessage1.getText(), "This value should not be blank.");
        //7. Enter a valid value to the day(s) field
        calendarEvents.repeatEveryDayInputLocator.sendKeys("3");
        //8. Message "This value should not be blank." should disappear
        assertFalse(calendarEvents.errorMessage1.isDisplayed());
        //9. Clear the value of the After occurrences field
        calendarEvents.afterOccurrencesInputlocator.click();
        calendarEvents.afterOccurrencesInputlocator.clear();
        //10. Message "This value should not be blank." should come up
        calendarEvents.afterOccurrence.click();
        assertTrue(calendarEvents.errorMessage2.isDisplayed());
        assertEquals(calendarEvents.errorMessage2.getText(),"This value should not be blank.","Messages didn't match");
        //11. Enter a valid value to the After occurrences field
        calendarEvents.afterOccurrencesInputlocator.sendKeys("3");
        //12. Message "This value should not be blank." should disappear
        assertFalse(calendarEvents.errorMessage2.isDisplayed());
    }

    @Test(priority = 6, description = "Daily repeat option, Ends, error messages")
    public void verifyingErrorMessage() {
        CalendarEventsPage calendarEvents = new CalendarEventsPage();

        //1. Log in as Valid user
        String username = ConfigurationReader.getProperty("salesmanagerusername");
        String password = ConfigurationReader.getProperty("salesmanagerpassword");
        VYTrackUtils.login(driver, username, password);
        //2. Go to Activities -> Calendar Events
        VYTrackUtils.navigateToModule(driver, "Activities", "Calendar Events");
        //3. Click on create new calendar event
        VYTrackUtils.waitUntilLoaderScreenDisappear(driver);
        calendarEvents.createCalendarEventBtn.click();
        //4. Click on RepeatCheckbox
        VYTrackUtils.waitUntilLoaderScreenDisappear(driver);
        calendarEvents.repeatCheckboxLocator.click();
        //5. Test the After occurrences input entering different values(boundary value analysis)
        calendarEvents.afterOccurrencesInputlocator.click();
        calendarEvents.afterOccurrencesInputlocator.sendKeys("0");
        calendarEvents.afterOccurrence.click();
        //6. Verify error messages occur when values are too big or small
        // "The value have not to be less than 1.","The value have not to be more than 99."
        BrowserUtils.waitForVisibility(calendarEvents.errorMessage2, 10);
        String errorMessage = calendarEvents.errorMessage2.getText();
        assertEquals(errorMessage,"The value have not to be less than 1.","Messages didn't match");
        calendarEvents.afterOccurrencesInputlocator.clear();
        calendarEvents.afterOccurrencesInputlocator.sendKeys("100");
        String erMessage = calendarEvents.errorMessage2.getText();
        assertEquals(erMessage,"The value have not to be more than 99.","Error messages didn't match");
        //7. Verify that error messages disappear when valid values are entered
        calendarEvents.afterOccurrencesInputlocator.clear();
        calendarEvents.afterOccurrencesInputlocator.sendKeys("28");
        assertFalse(calendarEvents.errorMessage2.isDisplayed());
    }

    @Test(priority = 7, description = "Daily repeat option, Ends, functionality")
    public void verifyingSummaries() {
        CalendarEventsPage calendarEvents = new CalendarEventsPage();

        //1. Log in as Valid user
        String username = ConfigurationReader.getProperty("salesmanagerusername");
        String password = ConfigurationReader.getProperty("salesmanagerpassword");
        VYTrackUtils.login(driver, username, password);
        //2. Go to Activities -> Calendar Events
        VYTrackUtils.navigateToModule(driver, "Activities", "Calendar Events");
        //3. Click on create new calendar event
        VYTrackUtils.waitUntilLoaderScreenDisappear(driver);
        calendarEvents.createCalendarEventBtn.click();
        //4. Click on RepeatCheckbox
        VYTrackUtils.waitUntilLoaderScreenDisappear(driver);
        calendarEvents.repeatCheckboxLocator.click();
        //5. Enter random value to the After occurrences field
        calendarEvents.afterOccurrencesInputlocator.click();
        calendarEvents.afterOccurrencesInputlocator.sendKeys("26");
        //6. Verify that Summary says "Daily every <random number> day"
        assertEquals(calendarEvents.summaryLocator.getText(),"Daily every 26 day","Summary didn't match");
        //7. Enter another random value to the After occurrences field
        calendarEvents.afterOccurrencesInputlocator.clear();
        calendarEvents.afterOccurrencesInputlocator.sendKeys("23");
        //8. Verify that Summary updated "Daily every <random number> day"
        assertEquals(calendarEvents.summaryLocator.getText(),"Daily every 23 day","Summary didn't match");
    }

}