package com.sperasoft.pages;

import com.sperasoft.Models.PersonData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage extends BasePage{

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void waitUntilPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(
                ExpectedConditions.presenceOfElementLocated(submitAccount)
        );
    }

    private By radioBtnMrPI = By.id("id_gender1");
    private By radioBtnMrsPI = By.id("id_gender2");
    private By firstnamePI = By.id("customer_firstname");
    private By lastnamePI = By.id("customer_lastname");
    private By emailPI = By.id("email");
    private By passwdPI = By.id("passwd");
    private By firstNameYA = By.id("firstname");
    private By lastnameYA = By.id("lastname");
    private By address1YA = By.id("address1");
    private By cityYA = By.id("city");
    private By stateYA = By.id("id_state");
    private By postcodeYA = By.id("postcode");
    private By phone_mobileYA = By.id("phone_mobile");
    private By aliasYA = By.id("alias");
    private By submitAccount = By.id("submitAccount");
    private By customer_name = By.cssSelector("div.header_user_info a.account span");

    private PersonData personData;


    public void fillRegistrationFormWithData(PersonData personData) {
        this.personData = personData;
        if (personData.getGenre() == PersonData.Genre.MALE) click(radioBtnMrPI);
        else click(radioBtnMrsPI);
        writeText(firstnamePI, personData.getFirstname());
        writeText(lastnamePI, personData.getLastname());
        // auto filled
//        writeText(emailPI, personData.getEmail());
        writeText(passwdPI, personData.getPasswd());
        writeText(firstNameYA, personData.getFirstname());
        writeText(lastnameYA, personData.getLastname());
        writeText(address1YA, personData.getAddress());
        writeText(cityYA, personData.getCity());
        select(stateYA, personData.getState());
        writeText(postcodeYA, personData.getPostcode());
        writeText(phone_mobileYA, personData.getMobilephone());
        writeText(aliasYA, personData.getPostcode());
        click(submitAccount);
    }


    public boolean verifyRegistration() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(
                ExpectedConditions.presenceOfElementLocated(customer_name)
        );
        return readText(customer_name).equals(personData.getFirstname() + " " + personData.getLastname());
    }

}
