package com.sperasoft.pages;

import com.sperasoft.models.PersonData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage extends BasePage{

    public RegisterPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void waitUntilPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(
                ExpectedConditions.visibilityOf(submitAccount)
        );
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
    @FindBy(id = "firstname")
    private WebElement firstNameYA;
    @FindBy(id = "lastname")
    private WebElement lastnameYA;
    @FindBy(id = "address1")
    private WebElement address1YA;
    @FindBy(id = "city")
    private WebElement cityYA;
    @FindBy(id = "id_state")
    private WebElement stateYA;
    @FindBy(id = "postcode")
    private WebElement postcodeYA;
    @FindBy(id = "phone_mobile")
    private WebElement phone_mobileYA;
    @FindBy(id = "alias")
    private WebElement aliasYA;
    @FindBy(id = "submitAccount")
    private WebElement submitAccount;
    @FindBy(css = "div.header_user_info a.account span")
    private WebElement customer_name;



    public void fillRegistrationFormWithData(PersonData personData) {
        if (personData.getGender() == PersonData.Gender.MALE) radioBtnMrPI.click();
        else radioBtnMrsPI.click();
        firstnamePI.sendKeys(personData.getFirstname());
        lastnamePI.sendKeys(personData.getLastname());
        // auto filled
//        writeText(emailPI, personData.getEmail());
        passwdPI.sendKeys(personData.getPasswd());
        firstNameYA.sendKeys(personData.getFirstname());
        lastnameYA.sendKeys(personData.getLastname());
        address1YA.sendKeys(personData.getAddress());
        cityYA.sendKeys(personData.getCity());
        (new Select(stateYA)).selectByValue(personData.getState());
        postcodeYA.sendKeys(personData.getPostcode());
        phone_mobileYA.sendKeys(personData.getMobilephone());
        // auto filled
//        aliasYA.sendKeys(personData.getPostcode());
        submitAccount.click();
    }


    public boolean verifyRegistration(PersonData personData) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement until = wait.until(
                ExpectedConditions.visibilityOf(customer_name)
        );
        return customer_name.getText().equals(personData.getFirstname() + " " + personData.getLastname());
    }

}
