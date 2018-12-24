package com.sperasoft.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountPage extends BasePage{

    @FindBy(css = "#center_column > div > div:nth-child(1) > ul > li:nth-child(4) > a" )
    private WebElement info;
    @FindBy(css = "#center_column > div > div:nth-child(1) > ul > li:nth-child(3) > a")
    private WebElement addresses;
    @FindBy(css = "#center_column > div.addresses > div > div > ul > li.address_update > a:nth-child(1)")
    private WebElement addressesUpdateLink;
    @FindBy(id = "firstname")
    private WebElement firstname;

    public AccountPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void goToAddresses() {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(
                ExpectedConditions.visibilityOf(addresses)
        );
        addresses.click();
        wait.until(
                ExpectedConditions.visibilityOf(addressesUpdateLink)
        );
        addressesUpdateLink.click();

    }

    public void goToPersonalInfo() {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(
                ExpectedConditions.visibilityOf(info)
        );
        info.click();
    }

}
