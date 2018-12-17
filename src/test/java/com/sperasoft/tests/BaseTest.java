package com.sperasoft.tests;

import com.sperasoft.models.Account;
import com.sperasoft.utilities.DataPool;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.util.HashMap;

public class BaseTest {

    public WebDriver driver;
    DataPool dataPool;
    HashMap<String,String> parameters;

    @BeforeSuite
    protected void beforeSuite( ITestContext testContext ) {
        dataPool = new DataPool<Account>();
        parameters = new HashMap<String, String>( testContext.getCurrentXmlTest().getAllParameters());
        dataPool.processDataFile( parameters.get( "dataFile" ), Account.class );
    }

    @BeforeTest
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "D:\\WebDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterTest
    public  void afterTest() {

    }

    @AfterSuite
    public void afterSuite() {
        driver.close();
        driver = null;
    }

}