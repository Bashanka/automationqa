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

public class AddressPage extends BasePage {

    @FindBy(id = "firstname")
    private WebElement firstname;
    @FindBy(id = "lastname")
    private WebElement lastname;
    @FindBy(id = "address1")
    private WebElement address1;
    @FindBy(id = "address2")
    private WebElement address2;
    @FindBy(id = "city")
    private WebElement city;
    @FindBy(id = "id_state")
    private WebElement id_state;
    @FindBy(id = "postcode")
    private WebElement postcode;
    @FindBy(id = "phone")
    private WebElement phone;
    @FindBy(id = "phone_mobile")
    private WebElement phone_mobile;
    @FindBy(id = "other")
    private WebElement other;
    @FindBy(id = "alias")
    private WebElement alias;
    @FindBy(id = "submitAddress")
    private WebElement submitAddress;


    public AddressPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean verifyAddress(Account account) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(firstname));
        return account.getFirstname().equals(firstname.getAttribute("value")) &&
                account.getLastname().equals(lastname.getAttribute("value")) &&
                account.getAddresses().get(0).getAddress1().equals(address1.getAttribute("value")) &&
                account.getAddresses().get(0).getAddress2().equals(address2.getAttribute("value")) &&
                account.getAddresses().get(0).getCity().equals(city.getAttribute("value")) &&
                account.getAddresses().get(0).getState().equals((new Select(id_state)).getFirstSelectedOption().getAttribute("value")) &&
                account.getAddresses().get(0).getPostcode().equals(postcode.getAttribute("value")) &&
                account.getAddresses().get(0).getHomephone().equals(phone.getAttribute("value")) &&
                account.getMobilephone().equals(phone_mobile.getAttribute("value")) &&
                account.getAddresses().get(0).getAddInfo().equals(other.getAttribute("value")) &&
                account.getAddresses().get(0).getTitle().equals(alias.getAttribute("value"));
    }

    public void changeAddressHomePhone(String phonenumber) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(firstname));
        phone.clear();
        phone.sendKeys(phonenumber);
        submitAddress.click();
    }

}
