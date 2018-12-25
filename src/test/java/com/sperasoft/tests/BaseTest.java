package com.sperasoft.tests;

import com.sperasoft.models.Account;
import com.sperasoft.utilities.DataPool;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.util.HashMap;

@Listeners( TakeScreenshotOnFailure.class )
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
        String webdriverFilePath = System.getProperty("user.dir")+
                "\\src\\main\\resources\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", webdriverFilePath);
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