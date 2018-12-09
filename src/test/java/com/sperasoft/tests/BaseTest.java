package com.sperasoft.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    public WebDriver driver;

    @BeforeTest
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "D:\\WebDrivers\\chromedriver.exe");
        driver = new ChromeDriver();

    }

    @AfterTest
    public  void afterTest() {

        driver.close();
        driver = null;
    }

}