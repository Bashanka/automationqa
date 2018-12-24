package com.sperasoft.pages;

import com.sperasoft.utilities.PropertyManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

abstract public class BasePage {
    public WebDriver driver;
    public String BASE_URL = PropertyManager.getInstance().getURL();
    public int TIMEOUT = PropertyManager.getInstance().getTimeout();
    public static String DOWNLOADDIR = PropertyManager.getInstance().getDownloaddir();

    //Constructor
    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public void open() {
        driver.get(BASE_URL);
    }

    public void waitForPageLoaded(WebDriver driver) {
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            Thread.sleep(5000);
            WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }

}
