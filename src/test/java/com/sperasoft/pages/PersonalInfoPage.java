package com.sperasoft.pages;

import com.sperasoft.models.Account;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Calendar;

public class PersonalInfoPage extends BasePage {

    @FindBy(className = "nav")
    private WebElement nav;
    @FindBy(id = "id_gender1")
    private WebElement gender1;
    @FindBy(id = "id_gender2")
    private WebElement gender2;
    @FindBy(id = "firstname")
    private WebElement firstname;
    @FindBy(id = "lastname")
    private WebElement lastname;
    @FindBy(id = "email")
    private WebElement email;
    @FindBy(id = "days")
    private WebElement days;
    @FindBy(id = "months")
    private WebElement months;
    @FindBy(id = "years")
    private WebElement years;
    @FindBy(id = "old_passwd")
    private WebElement old_passwd;
    @FindBy(id = "passwd")
    private WebElement passwd;
    @FindBy(id = "confirmation")
    private WebElement confirmation;
    @FindBy(id = "newsletter")
    private WebElement newsletter;
    @FindBy(id = "optin")
    private WebElement optin;
    @FindBy(css = "#center_column > div > form > fieldset > div:nth-child(11) > button")
    private WebElement submit;

    public PersonalInfoPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean verifyPersonalInfo(Account account) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(account.getDateOfBirth());
        return account.getFirstname().equals(firstname.getAttribute("value")) &&
                account.getLastname().equals(lastname.getAttribute("value")) &&
                account.getEmail().equals(email.getAttribute("value")) &&
                Integer.toString(cal.get(Calendar.DAY_OF_MONTH)).equals((new Select(days)).getFirstSelectedOption().getAttribute("value")) &&
                Integer.toString(cal.get(Calendar.MONTH) + 1).equals((new Select(months)).getFirstSelectedOption().getAttribute("value")) &&
                Integer.toString(cal.get(Calendar.YEAR)).equals((new Select(years)).getFirstSelectedOption().getAttribute("value"));
    }

    public void signOut() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(nav));
        PageFactory.initElements(driver, NavBar.class).singOut();
    }

    public void changePersonalName(Account account) {
        firstname.clear();
        firstname.sendKeys(account.getFirstname());
        lastname.clear();
        lastname.sendKeys(account.getLastname());
        old_passwd.sendKeys(account.getPasswd());
        submit.click();
    }

    public void goToAccount() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(nav));
        PageFactory.initElements(driver, NavBar.class).goToAccount();
    }

}
