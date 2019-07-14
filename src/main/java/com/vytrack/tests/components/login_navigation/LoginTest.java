package com.vytrack.tests.components.login_navigation;

import com.vytrack.utilities.SeleniumUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.testng.Assert.*;
import org.testng.annotations.*;


import java.util.concurrent.TimeUnit;

public class LoginTest {

    WebDriver driver;
    String login = "//input[@id='prependedInput']";
    String password = "//input[@id='prependedInput2']";
    String submitBtn = "//button[@type='submit']";

    public void loginAsStoreManager() {
        driver.findElement(By.xpath(login)).sendKeys("storemanager75");
        driver.findElement(By.xpath(password)).sendKeys("UserUser123");
        driver.findElement(By.xpath(submitBtn)).click();
    }

    public void loginAsSalesManager(){
        driver.findElement(By.xpath(login)).sendKeys("salesmanager137");
        driver.findElement(By.xpath(password)).sendKeys("UserUser123");
        driver.findElement(By.xpath(submitBtn)).click();
    }

    public void loginAsADriver(){
        driver.findElement(By.xpath(login)).sendKeys("user36");
        driver.findElement(By.xpath(password)).sendKeys("UserUser123");
        driver.findElement(By.xpath(submitBtn)).click();
    }

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("http://qa2.vytrack.com/user/login");
    }

    @Test(priority = 1, description = "verifying website as a store manager")
    public void loginTestPositive1(){
        loginAsStoreManager();
        SeleniumUtils.waitPlease(3);
        //verifying name of the store manager
        driver.findElement(By.xpath("//a[@class='dropdown-toggle'][contains(text(),'Regan Brown')]")).isEnabled();
        SeleniumUtils.waitPlease(3);
        driver.findElement(By.xpath("//a[@class='dropdown-toggle'][contains(text(),'Regan Brown')]")).isDisplayed();
        //verifying Dashboard page
        assertEquals(driver.findElement(By.cssSelector("h1[class=oro-subtitle]")).getText(),"Dashboard");
        SeleniumUtils.waitPlease(4);
        //logging out
        driver.findElement(By.xpath("//a[@class='dropdown-toggle'][contains(text(),'Regan Brown')]")).click();
        SeleniumUtils.waitPlease(3);
        driver.findElement(By.cssSelector("a[href='/user/logout']")).click();
    }

    @Test (priority = 2, dependsOnMethods = {"loginTestPositive1"}, description = "verifying website as a sales manager")
    public void loginTestPositive2(){
        loginAsSalesManager();
        //verifying dashboard page
        assertTrue(driver.findElement(By.cssSelector("h1[class='oro-subtitle']")).getText().equals("Dashboard"));
        //verifying that the name is different than in above test
        SeleniumUtils.waitPlease(4);
        assertFalse(driver.findElement(By.xpath("//a[@class='dropdown-toggle'][contains(text(),'Buck Howe')]")).getText().equals("Regan Brown"));
        assertTrue(driver.findElement(By.xpath("//a[@class='dropdown-toggle'][contains(text(),'Buck Howe')]")).getText().equals("Buck Howe"));
        SeleniumUtils.waitPlease(3);
        //loging out
        driver.findElement(By.xpath("//a[@class='dropdown-toggle'][contains(text(),'Buck Howe')]")).click();
        SeleniumUtils.waitPlease(3);
        driver.findElement(By.cssSelector("a[href='/user/logout']")).click();
    }

    @Test (priority = 3, dependsOnMethods = {"loginTestPositive2"}, description = "verifying website as a driver")
    public void loginTestPositive3(){
        loginAsADriver();
        //verifying the Dashboard page
        assertTrue(driver.findElement(By.cssSelector("h1[class='oro-subtitle']")).getText().contains("Quick Launchpad"));
        SeleniumUtils.waitPlease(4);
        //verifying the name is different than in above test
        driver.findElement(By.xpath("//a[@class='dropdown-toggle'][contains(text(),'Palma Balistreri')]")).isDisplayed();
        assertFalse(driver.findElement(By.xpath("//a[@class='dropdown-toggle'][contains(text(),'Palma Balistreri')]")).getText().equals("Buck Howe"));
    }

    @Test (priority = 4, description = "login test negative scenario")
    public void LoginTestNegative(){
        //opening login page
        driver.findElement(By.xpath(login)).sendKeys("salesmanager137");
        driver.findElement(By.xpath(password)).sendKeys("UserUser321");
        driver.findElement(By.xpath(submitBtn)).click();
        SeleniumUtils.waitPlease(3);
        assertTrue(driver.findElement(By.xpath("//*[contains(text(),'Invalid user name or password.')]")).getText().equals("Invalid user name or password."));
        SeleniumUtils.waitPlease(3);
        driver.findElement(By.xpath("//*[contains(text(),'Invalid user name or password.')]")).isDisplayed();
        assertEquals(driver.getCurrentUrl(),"http://qa2.vytrack.com/user/login");
        assertEquals(driver.getTitle(),"Login");


    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }

}