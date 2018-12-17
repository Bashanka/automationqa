package com.sperasoft.pages;

import com.sperasoft.models.Account;
import com.sperasoft.models.Address;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class RegisterPage extends BasePage{

    public RegisterPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "id_gender1")
    private WebElement radioBtnMrPI;
    @FindBy(id = "id_gender2")
    private WebElement radioBtnMrsPI;
    @FindBy(id = "customer_firstname")
    private WebElement firstnamePI;
    @FindBy(id = "customer_lastname")
    private WebElement lastnamePI;
    @FindBy(id = "email")
    private WebElement emailPI;
    @FindBy(id = "passwd")
    private WebElement passwdPI;
    @FindBy(id = "days")
    private WebElement bDayDaysPI;
    @FindBy(id = "months")
    private WebElement bDayMonthsPI;
    @FindBy(id = "years")
    private WebElement bDayYearsPI;
    @FindBy(id = "newsletter")
    private WebElement newsletterPI;
    @FindBy(id = "optin")
    private WebElement optinPI;
    @FindBy(id = "firstname")
    private WebElement firstNameYA;
    @FindBy(id = "lastname")
    private WebElement lastnameYA;
    @FindBy(id = "address1")
    private WebElement address1YA;
    @FindBy(id = "address2")
    private WebElement address2YA;
    @FindBy(id = "city")
    private WebElement cityYA;
    @FindBy(id = "id_state")
    private WebElement stateYA;
    @FindBy(id = "postcode")
    private WebElement postcodeYA;
    @FindBy(id = "other")
    private WebElement otherYA;
    @FindBy(id = "phone")
    private WebElement phoneYA;
    @FindBy(id = "phone_mobile")
    private WebElement phone_mobileYA;
    @FindBy(id = "alias")
    private WebElement aliasYA;
    @FindBy(id = "submitAccount")
    private WebElement submitAccount;



    public void fillRegistrationForm(Account account) {
        (new WebDriverWait(driver, 30)).until(
                ExpectedConditions.visibilityOf(submitAccount)
        );

        //Personal information
        if (account.getGender() == Account.Gender.MALE) radioBtnMrPI.click();
        else radioBtnMrsPI.click();
        firstnamePI.sendKeys(account.getFirstname());
        lastnamePI.sendKeys(account.getLastname());
        // auto filled
//        writeText(emailPI, account.getEmail());
        passwdPI.sendKeys(account.getPasswd());

        Calendar cal = Calendar.getInstance();
        cal.setTime(account.getDateOfBirth());


        (new Select(bDayDaysPI)).selectByValue(Integer.toString(cal.get(Calendar.DAY_OF_MONTH)));
        (new Select(bDayMonthsPI)).selectByValue(Integer.toString(cal.get(Calendar.MONTH) + 1));
        (new Select(bDayYearsPI)).selectByValue(Integer.toString(cal.get(Calendar.YEAR)));
        newsletterPI.click();
        optinPI.click();

        // Your address
        firstNameYA.sendKeys(account.getFirstname());
        lastnameYA.sendKeys(account.getLastname());
        Address address = account.getAddresses().get(0);
        address1YA.sendKeys(address.getAddress1());
        address2YA.sendKeys(address.getAddress2());
        cityYA.sendKeys(address.getCity());
        (new Select(stateYA)).selectByValue(address.getState());
        postcodeYA.sendKeys(address.getPostcode());
        otherYA.sendKeys(address.getAddInfo());
        phoneYA.sendKeys(address.getHomephone());
        phone_mobileYA.sendKeys(account.getMobilephone());
        aliasYA.clear();
        aliasYA.sendKeys(address.getTitle());
    }

    public void sendRegistrationForm() {
        submitAccount.click();
    }

    public boolean verifyRegistration(Account account) {
        NavBar navBar = PageFactory.initElements(driver, NavBar.class);
        return navBar.getCustomerName().equals(account.getFirstname() + " " + account.getLastname());
    }
    @FindBy(className = "nav")
    private WebElement nav;
    public void signOut() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(nav));
        PageFactory.initElements(driver, NavBar.class).singOut();
    }

}
