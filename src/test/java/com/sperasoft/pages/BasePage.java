package com.sperasoft.pages;

import com.sperasoft.utilities.PropertyManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

abstract public class BasePage {
    public WebDriver driver;
    public String BASE_URL = PropertyManager.getInstance().getURL();

    //Constructor
    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public void open() {
        driver.get(BASE_URL);
    }


}
