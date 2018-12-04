package com.sperasoft;

import java.lang.String;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class TestClass {

    @Test
    public  void test() {
        String exePath = "E:\\WebDrivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", exePath);
        WebDriver driver = new ChromeDriver();
        driver.get("http://automationpractice.com/");
        WebElement singInLink = driver.findElement(By.className("login"));
        singInLink.click();
        driver.findElement(By.id("email_create")).sendKeys(mail());
        driver.findElement(By.id("SubmitCreate")).submit();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("submitAccount"))
        );
        driver.close();
    }

    static String mail() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr + "@gmail.com";
    }

}