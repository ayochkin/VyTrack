package com.vytrack.pages.activities;


import com.vytrack.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CalendarEventsPage {
        static WebDriver driver = Driver.getDriver();

        @FindBy(css = "[title='Create Calendar event']")
        public WebElement createCalendarEventBtn;

        @FindBy(css = "[id^='oro_calendar_event_form_title']")
        public WebElement titleInputLocator;

        @FindBy(css = "[id^='date_selector_oro_calendar_event_form_start']")
        public WebElement startDateLocator;

        @FindBy(css = "table tbody tr:nth-of-type(5) td:nth-of-type(4)")
        public WebElement dayLocator;

        @FindBy(css = ".ui-datepicker-month")
        public WebElement monthLocator;

        @FindBy(css = ".ui-datepicker-year")
        public WebElement yearLocator;

        @FindBy(css = "[id^='date_selector_oro_calendar_event_form_end']")
        public WebElement endDateLocator;

        @FindBy(xpath = "//button[text()='Today']")
        public WebElement todayLocator;

        @FindBy(css = "[id^='time_selector_oro_calendar_event_form_start-uid']")
        public WebElement startTimeLocator;

        @FindBy(xpath = "//li[text()='1:30 AM']")
        public WebElement newTimeLocator;

        @FindBy(css = "[id^='time_selector_oro_calendar_event_form_end-uid']")
        public WebElement endTimeLocator;

        @FindBy(xpath = "//li[text()='11:30 PM']")
        public WebElement startTime1130Pm;

        @FindBy(css = "[id^='recurrence-repeat-view']")
        public WebElement repeatCheckboxLocator;

        @FindBy(css = "[id^='recurrence-repeats-view']")
        public WebElement repeatsInputLocator;

        @FindBy(css = "div[data-name='recurrence-daily'] .recurrence-subview-control__number")
        public WebElement repeatEveryDayInputLocator;

        @FindBy(css = "input[checked='checked']")
        public WebElement daysCheckboxLocator;

        @FindBy(xpath = "//div[div[label[.='Summary:']]]//span")
        public WebElement summaryLocator;

        @FindBy(xpath = "(//input[@type='radio'])[2]")
        public WebElement weekdayCheckboxLocator;

        @FindBy(xpath = "(//span[starts-with(@id,'temp-validation-name')])[1]")
        public WebElement errorMessage1;

        @FindBy(xpath = "//span[.='After']//following-sibling::input")
        public WebElement afterOccurrencesInputlocator;

        @FindBy(xpath = "(//div[@class='recurrence-subview-control__item'])[4]")
        public WebElement afterOccurrence;

        @FindBy(css = "span[id^='temp-validation-name']")
        public WebElement errorMessage2;

        @FindBy(css ="a[title='Grid Settings']")
        public WebElement gridSettingsElement;

        @FindBy(css = "a[title='Reset']")
        public WebElement resetBtnElement;

        @FindBy(css = ".grid-header-cell__label")
        public List<WebElement> headers;

        public CalendarEventsPage(){
            PageFactory.initElements(Driver.getDriver(), this);
        }

        public void selectGridSetting(String name, boolean yesOrNo){
                //click on grid options
                gridSettingsElement.click();
                //create locator for grid option based on the name
                String locator = "//td//label[text()='"+name+"']/../following-sibling::td//input";
                //find element
                //you can also call Driver.getDriver()
                WebElement gridOption = driver.findElement(By.xpath(locator));
                //if param yesOrNo is true, and checkbox is not selected yet
                //click on it
                //or
                //checkbox is selected and you want to unselect it
                if((yesOrNo == true && !gridOption.isSelected())  ||
                        (yesOrNo == false && gridOption.isSelected())){
                        gridOption.click();
                }
        }
        //lets write a method
        //that will take a headerName as a parameter
        //and will try to look up for header name in the collection of headers
        //if header name was not found
        //return false
        //otherwise return true

        public boolean verifyHeaderExists(String headerNameOrColumnName){
                for(WebElement tableHeader : headers){
                        if(tableHeader.getText().equalsIgnoreCase(headerNameOrColumnName)){
                                return true;
                        }
                }
                return false;
        }

    }



