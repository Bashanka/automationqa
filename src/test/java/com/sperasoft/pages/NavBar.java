package com.sperasoft.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavBar extends AbstractContainer{

    @FindBy(css = "nav .login")
    private WebElement signInLink;
    @FindBy(css = "nav .logout")
    private WebElement signOutLink;
    @FindBy(css = "nav .account span")
    private WebElement customerName;
    @FindBy(css = "nav a.account")
    private WebElement accountLink;

    public void singIn() {
        signInLink.click();
    }

    public void singOut() {
        signOutLink.click();
    }

    public String getCustomerName() {
        return customerName.getText();
    }

    public void goToAccount() {
        accountLink.click();
    }

}
