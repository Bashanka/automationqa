package com.sperasoft.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    @FindBy(className = "login")
    private WebElement singInLink;
    @FindBy(id = "email_create")
    private WebElement emailInput;
    @FindBy(id = "SubmitCreate")
    private WebElement emailSubmit;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get(BASE_URL);
    }

    public void goToRegisterWithEmail(String email) {
        singInLink.click();
        emailInput.sendKeys(email);
        emailSubmit.submit();
    }

}
