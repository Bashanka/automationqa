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
import org.testng.asserts.SoftAssert;

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

    @FindBy(className = "alert-danger")
    private WebElement alertDanger;

    public boolean verifyRegistrationError() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(alertDanger));
        return alertDanger.isDisplayed();
    }

    @FindBy(className = "nav")
    private WebElement nav;
    public void signOut() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(nav));
        PageFactory.initElements(driver, NavBar.class).singOut();
    }

    public SoftAssert verifyPageElements() {
        SoftAssert softAssert = new SoftAssert();

        WebDriverWait wait = new WebDriverWait(driver, 5);
//        wait.until(ExpectedConditions.visibilityOf(radioBtnMrPI));
//        wait.until(ExpectedConditions.visibilityOf(radioBtnMrsPI));
//        wait.until(ExpectedConditions.visibilityOf(firstnamePI));
//        wait.until(ExpectedConditions.visibilityOf(lastnamePI));
//        wait.until(ExpectedConditions.visibilityOf(emailPI));
//        wait.until(ExpectedConditions.visibilityOf(passwdPI));
//        wait.until(ExpectedConditions.visibilityOf(bDayDaysPI));
//        wait.until(ExpectedConditions.visibilityOf(bDayMonthsPI));
//        wait.until(ExpectedConditions.visibilityOf(bDayYearsPI));
//        wait.until(ExpectedConditions.visibilityOf(newsletterPI));
//        wait.until(ExpectedConditions.visibilityOf(optinPI));
//        wait.until(ExpectedConditions.visibilityOf(firstNameYA));
//        wait.until(ExpectedConditions.visibilityOf(lastnameYA));
//        wait.until(ExpectedConditions.visibilityOf(cityYA));
//        wait.until(ExpectedConditions.visibilityOf(postcodeYA));
//        wait.until(ExpectedConditions.visibilityOf(stateYA));
//        wait.until(ExpectedConditions.visibilityOf(otherYA));
//        wait.until(ExpectedConditions.visibilityOf(phoneYA));
//        wait.until(ExpectedConditions.visibilityOf(phone_mobileYA));
//        wait.until(ExpectedConditions.visibilityOf(aliasYA));
        wait.until(ExpectedConditions.visibilityOf(submitAccount));

        softAssert.assertTrue(radioBtnMrPI.isDisplayed(), "radioBtnMrPI");
        softAssert.assertTrue(radioBtnMrsPI.isDisplayed(), "radioBtnMrsPI");
        softAssert.assertTrue(firstnamePI.isDisplayed(), "firstnamePI");
        softAssert.assertTrue(lastnamePI.isDisplayed(), "lastnamePI");
        softAssert.assertTrue(emailPI.isDisplayed(), "emailPI");
        softAssert.assertTrue(passwdPI.isDisplayed(), "passwdPI");
        softAssert.assertTrue(bDayDaysPI.isDisplayed(), "bDayDaysPI");
        softAssert.assertTrue(bDayMonthsPI.isDisplayed(), "bDayMonthsPI");
        softAssert.assertTrue(bDayYearsPI.isDisplayed(), "bDayYearsPI");
        softAssert.assertTrue(newsletterPI.isDisplayed(), "newsletterPI");
        softAssert.assertTrue(optinPI.isDisplayed(), "optinPI");
        softAssert.assertTrue(firstNameYA.isDisplayed(), "firstNameYA");
        softAssert.assertTrue(lastnameYA.isDisplayed(), "lastnameYA");
        softAssert.assertTrue(address1YA.isDisplayed(), "address1YA");
        softAssert.assertTrue(address2YA.isDisplayed(), "address2YA");
        softAssert.assertTrue(cityYA.isDisplayed(), "cityYA");
        softAssert.assertTrue(postcodeYA.isDisplayed(), "postcodeYA");
        softAssert.assertTrue(stateYA.isDisplayed(), "stateYA");
        softAssert.assertTrue(otherYA.isDisplayed(), "otherYA");
        softAssert.assertTrue(phoneYA.isDisplayed(), "phoneYA");
        softAssert.assertTrue(phone_mobileYA.isDisplayed(), "phone_mobileYA");
        softAssert.assertTrue(aliasYA.isDisplayed(), "aliasYA");
        softAssert.assertTrue(submitAccount.isDisplayed(), "submitAccount");

        return softAssert;
    }
}
