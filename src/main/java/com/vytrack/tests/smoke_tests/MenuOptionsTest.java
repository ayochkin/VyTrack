package com.vytrack.tests.smoke_tests;

import com.vytrack.utilities.SeleniumUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class MenuOptionsTest {

    WebDriver driver;
    String login = "//input[@id='prependedInput']";
    String password = "//input[@id='prependedInput2']";
    String submitBtn = "//button[@type='submit']";

    public void loginAsTruckDriver(){
        driver.findElement(By.xpath(login)).sendKeys("user36");
        driver.findElement(By.xpath(password)).sendKeys("UserUser123");
        driver.findElement(By.xpath(submitBtn)).click();
    }

    public void loginAsStoreManager(){
        driver.findElement(By.xpath(login)).sendKeys("storemanager75");
        driver.findElement(By.xpath(password)).sendKeys("UserUser123");
        driver.findElement(By.xpath(submitBtn)).click();
    }

    @BeforeClass
    public void beforeClass(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("http://qa2.vytrack.com/user/login");

    }

    @Test(priority = 1, description = "navigating to fleet -> vehicles, verifying page title and name")
    public void test1(){
        loginAsTruckDriver();
        SeleniumUtils.waitPlease(2);
        driver.findElement(By.xpath("(//a[@class='unclickable'])[1]")).click();
        SeleniumUtils.waitPlease(2);
        driver.findElement(By.xpath("//span[.='Vehicles']")).click();
        SeleniumUtils.waitPlease(2);
        Assert.assertEquals(driver.getTitle(),"Car - Entities - System - Car - Entities - System");
        SeleniumUtils.waitPlease(3);
        Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).getText(),"All Cars"); // page name assertion failed

    }

    @Test(priority = 2, description = "navigating to customers -> accounts, verifying page title and name")
    public void test2(){
        loginAsTruckDriver();
        SeleniumUtils.waitPlease(2);
        driver.findElement(By.xpath("(//a[@class='unclickable'])[2]")).click();
        SeleniumUtils.waitPlease(3);
        driver.findElement(By.xpath("//span[.='Accounts']")).click();
        SeleniumUtils.waitPlease(3);
        Assert.assertEquals(driver.getTitle(),"Accounts - Customers");
        Assert.assertEquals(driver.findElement(By.cssSelector("[class='oro-subtitle']")).getText(),"Accounts");
    }

    @Test(priority = 3, description = "navigating to customers -> contacts, verifying page title and name")
    public void test3(){
        loginAsTruckDriver();
        SeleniumUtils.waitPlease(2);
        driver.findElement(By.xpath("(//a[@class='unclickable'])[2]")).click();
        SeleniumUtils.waitPlease(2);
        driver.findElement(By.xpath("//span[.='Contacts']")).click();
        SeleniumUtils.waitPlease(2);
        Assert.assertEquals(driver.getTitle(),"Contacts - Customers");
        Assert.assertEquals(driver.findElement(By.cssSelector("h1[class=oro-subtitle]")).getText(),"Contacts");
    }

    @Test (priority = 4, description = "navigating to activities -> calendar events, verifying page title and name")
    public void test4(){
        loginAsTruckDriver();
        SeleniumUtils.waitPlease(2);
        driver.findElement(By.xpath("(//a[@class='unclickable'])[3]")).click();
        SeleniumUtils.waitPlease(2);
        driver.findElement(By.xpath("//span[.='Calendar Events']")).click();
        SeleniumUtils.waitPlease(2);
        Assert.assertTrue(driver.getTitle().equals("Calendar Events - Activities"));
        Assert.assertTrue(driver.findElement(By.cssSelector("h1[class=oro-subtitle]")).getText().contains("Calendar Events"));
    }

    @Test (priority = 5, description = "navigating to dashboards -> dashboard, verifying page title and name")
    public void test5(){
        loginAsStoreManager();
        SeleniumUtils.waitPlease(4);
        driver.findElement(By.xpath("//span[@class='title title-level-1'][contains(text(),'Dashboard')]")).click();
        SeleniumUtils.waitPlease(3);
        driver.findElement(By.xpath("//a[.='Dashboard']")).click();
        SeleniumUtils.waitPlease(3);
        Assert.assertTrue(driver.getTitle().equals("Dashboard - Dashboards"));
        Assert.assertTrue(driver.findElement(By.cssSelector("h1[class=oro-subtitle]")).getText().equals("Dashboard"));
    }

    @Test(priority = 6, description = "navigating to fleet -> vehicles, verifying page title and name")
    public void test6(){
        loginAsStoreManager();
        SeleniumUtils.waitPlease(4);
        driver.findElement(By.xpath("(//a[@class='unclickable'])[2]")).click();
        SeleniumUtils.waitPlease(3);
        driver.findElement(By.xpath("//span[.='Vehicles']")).click();
        SeleniumUtils.waitPlease(3);
        Assert.assertEquals(driver.getTitle(),"All - Car - Entities - System - Car - Entities - System");
        Assert.assertEquals(driver.findElement(By.cssSelector("h1[class='oro-subtitle']")).getText(),"All Cars");
    }
    @Test(priority = 7, description = "navigating to customers -> accounts, verifying page title and name")
    public void test7(){
        loginAsStoreManager();
        SeleniumUtils.waitPlease(4);
        driver.findElement(By.xpath("//span[@class='title title-level-1'][contains(text(),'Customers')]")).click();
        SeleniumUtils.waitPlease(3);
        driver.findElement(By.xpath("//span[@class='title title-level-2'][text()='Accounts']")).click();
        SeleniumUtils.waitPlease(3);
        Assert.assertEquals(driver.getTitle(),"All - Accounts - Customers");
        Assert.assertEquals(driver.findElement(By.cssSelector("h1[class='oro-subtitle']")).getText(),"All Accounts");
    }

    @Test(priority = 8, description = "navigating to customers -> contacts, verifying page title and name")
    public void test8(){
        loginAsStoreManager();
        SeleniumUtils.waitPlease(4);
        driver.findElement(By.xpath("//span[@class='title title-level-1'][contains(text(),'Customers')]")).click();
        SeleniumUtils.waitPlease(3);
        driver.findElement(By.xpath("//span[@class='title title-level-2'][text()='Contacts']")).click();
        SeleniumUtils.waitPlease(3);
        Assert.assertTrue(driver.getTitle().contains("All - Contacts - Customers"));
        Assert.assertTrue(driver.findElement(By.cssSelector("h1[class=oro-subtitle]")).getText().equals("All Contacts"));
    }

    @Test (priority = 9, description = "navigating to sales -> opportunities, verifying page title and name")
    public void test9(){
        loginAsStoreManager();
        SeleniumUtils.waitPlease(4);
        driver.findElement(By.xpath("//span[@class='title title-level-1'][contains(text(),'Sales')]")).click();
        SeleniumUtils.waitPlease(3);
        driver.findElement(By.xpath("//span[@class='title title-level-2'][contains(text(),'Opportunities')]")).click();
        SeleniumUtils.waitPlease(4);
        Assert.assertTrue(driver.getTitle().equals("Open Opportunities - Opportunities - Sales"));
        Assert.assertTrue(driver.findElement(By.cssSelector("h1[class='oro-subtitle']")).getText().equals("Open Opportunities"));
    }

    @Test(priority = 10, description = "navigating to activities -> calls, verifying page title and name")
    public void test10(){
        loginAsStoreManager();
        SeleniumUtils.waitPlease(4);
        driver.findElement(By.xpath("//span[@class='title title-level-1'][contains(text(),'Activities')]")).click();
        SeleniumUtils.waitPlease(3);
        driver.findElement(By.xpath("//span[@class='title title-level-2'][contains(text(),'Calls')]")).click();
        SeleniumUtils.waitPlease(3);
        Assert.assertEquals(driver.getTitle(),"All - Calls - Activities");
        Assert.assertTrue(driver.findElement(By.cssSelector("h1[class='oro-subtitle']")).getText().equals("All Calls"));
    }

    @Test(priority = 11, description = "navigating to activities, verufying page title and name")
    public void test11(){
        loginAsStoreManager();
        SeleniumUtils.waitPlease(5);
        driver.findElement(By.xpath("//span[@class='title title-level-1'][contains(text(),'Activities')]")).click();
        SeleniumUtils.waitPlease(3);
        driver.findElement(By.xpath("//span[@class='title title-level-2'][contains(text(),'Calendar Events')]")).click();
        SeleniumUtils.waitPlease(3);
        Assert.assertTrue(driver.getTitle().equals("Calendar Events - Activities")); // page title assertion failed
        SeleniumUtils.waitPlease(4);
        Assert.assertEquals(driver.findElement(By.cssSelector("h1[class=oro-subtitle]")).getText(),"All Calendar Events");
    }


    @AfterMethod
    public void tearDown(){
        SeleniumUtils.waitPlease(3);
        driver.close();
    }



}
