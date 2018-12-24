package com.sperasoft.pages;

import com.sperasoft.models.Account;
import com.sperasoft.models.Address;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

public class RegisterPage extends BasePage{


    public static ArrayList<String> errors = new ArrayList<>();

    static {
        errors.add("You must register at least one phone number.");
        errors.add("phone is invalid.");
        errors.add("phone_mobile is invalid.");
        errors.add("lastname is required.");
        errors.add("lastname is invalid.");
        errors.add("firstname is required.");
        errors.add("firstname is invalid.");
        errors.add("email is invalid.");
        errors.add("email is required.");
        errors.add("passwd is required.");
        errors.add("address1 is required.");
        errors.add("city is required.");
        errors.add("The Zip/Postal code you've entered is invalid. It must follow this format: 00000");
        errors.add("This country requires you to choose a State.");
    }

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
        (new WebDriverWait(driver, TIMEOUT)).until(
                ExpectedConditions.visibilityOf(submitAccount)
        );

        //Personal information
        if (account.getGender() == Account.Gender.MALE) radioBtnMrPI.click();
        else radioBtnMrsPI.click();
        firstnamePI.sendKeys(account.getFirstname());
        lastnamePI.sendKeys(account.getLastname());
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

    public boolean verifyRegistrationError(ArrayList<String> expectedErrors) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(alertDanger));
        if (!alertDanger.isDisplayed()) return false;
        List<WebElement> errs = driver.findElements(By.cssSelector(".alert-danger li"));
        ArrayList<String> actualErrors = new ArrayList<>();
        for (WebElement err : errs) {
            actualErrors.add(err.getText());
        }

        return compareArrays(expectedErrors, actualErrors);
    }

    @FindBy(className = "nav")
    private WebElement nav;
    public void signOut() {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(nav));
        PageFactory.initElements(driver, NavBar.class).singOut();
    }

    public SoftAssert verifyPageElements() {
        SoftAssert softAssert = new SoftAssert();
        waitForPageLoaded(driver);

        softAssert.assertTrue(radioBtnMrPI.isEnabled(), "Radiobutton Mr is not displayed");
        softAssert.assertTrue(radioBtnMrsPI.isEnabled(), "Radiobutton Mrs is not displayed");
        softAssert.assertTrue(firstnamePI.isDisplayed(), "Input First Name is not displayed");
        softAssert.assertTrue(lastnamePI.isDisplayed(), "Input Last Name is not displayed");
        softAssert.assertTrue(emailPI.isDisplayed(), "Input Email is not displayed");
        softAssert.assertTrue(passwdPI.isDisplayed(), "Input Password is not displayed");
        softAssert.assertTrue(bDayDaysPI.isEnabled(), "Select Day of Birth is not displayed");
        softAssert.assertTrue(bDayMonthsPI.isEnabled(), "Select Month of Birth is not displayed");
        softAssert.assertTrue(bDayYearsPI.isEnabled(), "Select Year of Birth is not displayed");
        softAssert.assertTrue(newsletterPI.isEnabled(), "Checkbox News Letter is not displayed");
        softAssert.assertTrue(optinPI.isEnabled(), "Checkbox Receive special offers is not displayed");
        softAssert.assertTrue(firstNameYA.isDisplayed(), "Input First Name is not displayed (Address)");
        softAssert.assertTrue(lastnameYA.isDisplayed(), "Input Last Name is not displayed (Address)");
        softAssert.assertTrue(address1YA.isDisplayed(), "Input Address is not displayed (Address)");
        softAssert.assertTrue(address2YA.isDisplayed(), "Input Address (Line 2) is not displayed (Address)");
        softAssert.assertTrue(cityYA.isDisplayed(), "Input City is not displayed (Address)");
        softAssert.assertTrue(postcodeYA.isDisplayed(), "Input Postcode is not displayed (Address)");
        softAssert.assertTrue(stateYA.isEnabled(), "Select State is not displayed (Address)");
        softAssert.assertTrue(otherYA.isDisplayed(), "Textarea Additional Information is not displayed (Address)");
        softAssert.assertTrue(phoneYA.isDisplayed(), "Input Home Phone is not displayed (Address)");
        softAssert.assertTrue(phone_mobileYA.isDisplayed(), "Input Mobile Phone is not displayed (Address)");
        softAssert.assertTrue(aliasYA.isDisplayed(), "Input Assign an Address Alias is not displayed (Address)");
        softAssert.assertTrue(submitAccount.isDisplayed(), "Submit button is not displayed");

        return softAssert;
    }



    public static boolean compareArrays(ArrayList<String> arr1, ArrayList<String> arr2) {
        if (arr1.size() != arr2.size()) return false;
        for (int i = 0; i < arr1.size(); i++) {
            boolean flag = false;
            for (int j = 0; j < arr2.size(); j++) {
                System.out.println(arr1.get(i) + " | " + arr2.get(j));
                if (arr1.get(i).equals(arr2.get(j))) {
                    flag = true;
                    break;
                }
            }
            if (!flag) return flag;
        }
        return true;
    }

}
