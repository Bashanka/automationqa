package com.sperasoft.pages;

import com.sperasoft.utilities.PropertyManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

abstract public class BasePage {
    public WebDriver driver;
    public String BASE_URL = PropertyManager.getInstance().getURL();

    //Constructor
    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    //Click Method
    public void click (By elementLocation) {
        driver.findElement(elementLocation).click();
    }

    //Write Text
    public void writeText (By elementLocation, String text) {
        driver.findElement(elementLocation).sendKeys(text);
    }

    //Read Text
    public String readText (By elementLocation) {
        return driver.findElement(elementLocation).getText();
    }

    public void select(By elementLocation, String value) {
        WebElement select = driver.findElement(elementLocation);
        Select dropDown = new Select(select);
        dropDown.selectByValue(value);
    }


}
