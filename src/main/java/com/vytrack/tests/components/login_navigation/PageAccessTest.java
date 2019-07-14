package com.vytrack.tests.components.login_navigation;

import com.vytrack.utilities.SeleniumUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.testng.Assert.*;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class PageAccessTest {

    WebDriver driver;
    String login = "//input[@id='prependedInput']";
    String password = "//input[@id='prependedInput2']";
    String submitBtn = "//button[@type='submit']";
    String fleetLocator = "//span[@class='title title-level-1'][contains(text(),'Fleet')]";
    String vhcleCtrLocator = "//span[@class='title title-level-2'][contains(text(),'Vehicle Contracts')]";
    String vehiclPage = "h1[class='oro-subtitle']";

    @BeforeClass
    public void beforeClass(){ WebDriverManager.chromedriver().setup(); }

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("http://qa2.vytrack.com/user/login");

    }

    @Test (priority = 1, description = "verifying store manager can access Vehicle Contracts page")
    public void test1(){
        driver.findElement(By.xpath(login)).sendKeys("storemanager75");
        driver.findElement(By.xpath(password)).sendKeys("UserUser123");
        driver.findElement(By.xpath(submitBtn)).click();
        SeleniumUtils.waitPlease(4);
        driver.findElement(By.xpath(fleetLocator)).click();
        SeleniumUtils.waitPlease(3);
        driver.findElement(By.xpath(vhcleCtrLocator)).click();
        SeleniumUtils.waitPlease(3);
        assertTrue(driver.findElement(By.cssSelector(vehiclPage)).getText().equals("All Vehicle Contract"));
    }

    @Test(priority = 2, description = "verifying sales manager can access Vehicle Contracts page")
    public void test2(){
        driver.findElement(By.xpath(login)).sendKeys("salesmanager137");
        driver.findElement(By.xpath(password)).sendKeys("UserUser123");
        driver.findElement(By.xpath(submitBtn)).click();
        SeleniumUtils.waitPlease(4);
        driver.findElement(By.xpath(fleetLocator)).click();
        SeleniumUtils.waitPlease(3);
        driver.findElement(By.xpath(vhcleCtrLocator)).click();
        SeleniumUtils.waitPlease(3);
        assertTrue(driver.findElement(By.cssSelector(vehiclPage)).getText().equals("All Vehicle Contract"));

    }

    @Test(priority = 3, description = "verifying driver can access Vehicle Contracts page")
    public void test3(){
        driver.findElement(By.xpath(login)).sendKeys("user36");
        driver.findElement(By.xpath(password)).sendKeys("UserUser123");
        driver.findElement(By.xpath(submitBtn)).click();
        SeleniumUtils.waitPlease(4);
        driver.findElement(By.xpath(fleetLocator)).click();
        SeleniumUtils.waitPlease(3);
        driver.findElement(By.xpath(vhcleCtrLocator)).click();
        SeleniumUtils.waitPlease(3);
        driver.findElement(By.xpath("//div[@class='message'][contains(text(),'You do not have permission to perform this action.')] ")).isDisplayed();
        assertTrue(driver.findElement(By.xpath("//div[@class='message'][contains(text(),'You do not have permission to perform this action.')]")).getText().equals("You do not have permission to perform this action."));

    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }





}
