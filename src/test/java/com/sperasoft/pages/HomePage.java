package com.sperasoft.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    private String singInLink = "login";
    private String emailInput = "email_create";
    private String emailSubmit = "SubmitCreate";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL);
    }

    public void goToRegisterWithEmail(String email) {
        click(By.className(singInLink));
        writeText(By.id(emailInput), email);
        click(By.id(emailSubmit));
    }

}
