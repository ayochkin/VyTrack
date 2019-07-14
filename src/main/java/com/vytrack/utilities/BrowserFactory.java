package com.vytrack.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {

    /**
     *  @param browser name
     *  @return browser object, otherwise throw exception to prevent test run
     */
    public static WebDriver getDriver(String browser){
        if( browser.equals("chrome") ){
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();

        }else if ( browser.equals("firefox")){
            WebDriverManager.firefoxdriver().setup();// webDriverManager is a dependency coming from bonigarcia from the pom file
            return new FirefoxDriver();
        }else{
            throw new IllegalArgumentException("Wrong browser name!"); //unchecked, run time exception, we don't have to handle it
        }

    }

}
