package com.sperasoft.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    @FindBy(className = "nav")
    private WebElement nav;
    @FindBy(id = "email_create")
    private WebElement emailInputCreate;
    @FindBy(id = "SubmitCreate")
    private WebElement emailSubmitCreate;
    @FindBy(id = "email")
    private WebElement emailInputLogIn;
    @FindBy(id = "passwd")
    private WebElement passwdInputLogIn;
    @FindBy(id = "SubmitLogin")
    private WebElement SubmitLogIn;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public void singIn() {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(nav));
        PageFactory.initElements(driver, NavBar.class).singIn();
    }

    public void signOut() {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(nav));
        PageFactory.initElements(driver, NavBar.class).singOut();
    }


    public void goToRegisterWithEmail(String email) {
//        singInLink.click();
        emailInputCreate.sendKeys(email);
        emailSubmitCreate.submit();
    }

    @FindBy(className = "form-error")
    private WebElement formError;
    public boolean goToRegisterWithEmailError() {
        return formError.isDisplayed();
    }

    public void goToPersonalAccount(String email, String passwd) {
        emailInputLogIn.sendKeys(email);
        passwdInputLogIn.sendKeys(passwd);
        SubmitLogIn.click();
    }

}
