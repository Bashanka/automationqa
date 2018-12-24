package com.sperasoft.tests;

import com.sperasoft.models.Account;
import com.sperasoft.pages.HomePage;
import com.sperasoft.pages.RegisterPage;
import io.qameta.allure.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;


public class NegRegistrationTest extends BaseTest {

    private static final Logger LOGGER = LogManager.getLogger( NegRegistrationTest.class.getName() );

    @DataProvider( name = "verifyRegistrationWithCorrectData" )
    private Object[][] verifyRegistrationWithCorrectData() {
        return dataPool.getData();
    }

    @Test( dataProvider = "verifyRegistrationWithCorrectData", description = "Registration with not correct firstname" )
    @Description( "Registration with not correct firstname" )
    public void RegistrationTestWithNotCorrectFirstName(Account account) {
        account.setFirstname("1111");
        RegistrationTestWithPersonData(account);
        LOGGER.info("Verify Registration Error");
        RegisterPage registerPage = new RegisterPage(driver);
        ArrayList<String> arr = new ArrayList<>();
        arr.add("firstname is invalid.");
        Assert.assertTrue(registerPage.verifyRegistrationError(arr));
    }

    @Test( dataProvider = "verifyRegistrationWithCorrectData", description = "Registration with not correct lastname" )
    @Description( "Registration with not correct lastname" )
    public void RegistrationTestWithNotCorrectLastName(Account account) {
        account.setLastname("1111");
        RegistrationTestWithPersonData(account);
        LOGGER.info("Verify Registration Error");
        RegisterPage registerPage = new RegisterPage(driver);
        ArrayList<String> arr = new ArrayList<>();
        arr.add("lastname is invalid.");
        Assert.assertTrue(registerPage.verifyRegistrationError(arr));
    }

    @Test( dataProvider = "verifyRegistrationWithCorrectData", description = "Registration with not correct email" )
    @Description( "Registration with not correct email" )
    public void RegistrationTestWithNotCorrectEmail(Account account) {
        account.setEmail("1111");
        LOGGER.debug("HomePage init");
        HomePage homePage = new HomePage(driver);
        LOGGER.info("HomePage open");
        homePage.open();
        LOGGER.info("Sing In");
        homePage.singIn();
        LOGGER.info("Go To Register With Email");
        homePage.goToRegisterWithEmail(account.getEmail());
        LOGGER.info("Check Email Error");
        homePage.goToRegisterWithEmailError();
        LOGGER.info("Verify Registration Error");
        RegisterPage registerPage = new RegisterPage(driver);
        ArrayList<String> arr = new ArrayList<>();
        arr.add("email is required.");
        Assert.assertTrue(registerPage.verifyRegistrationError(arr));

    }

    @Test( dataProvider = "verifyRegistrationWithCorrectData", description = "Registration with not correct postcode" )
    @Description( "Registration with not correct postcode" )
    public void RegistrationTestWithNotCorrectPostcode(Account account) {
        account.getAddresses().get(0).setPostcode("1111");
        RegistrationTestWithPersonData(account);
        LOGGER.info("Verify Registration Error");
        RegisterPage registerPage = new RegisterPage(driver);
        ArrayList<String> arr = new ArrayList<>();
        arr.add("The Zip/Postal code you've entered is invalid. It must follow this format: 00000");
        Assert.assertTrue(registerPage.verifyRegistrationError(arr));
    }

    @Test( dataProvider = "verifyRegistrationWithCorrectData", description = "Registration with not correct home phone" )
    @Description( "Registration with not correct home phone" )
    public void RegistrationTestWithNotCorrectHomePhone(Account account) {
        account.getAddresses().get(0).setHomephone("serw");
        RegistrationTestWithPersonData(account);
        LOGGER.info("Verify Registration Error");
        RegisterPage registerPage = new RegisterPage(driver);
        ArrayList<String> arr = new ArrayList<>();
        arr.add("phone is invalid.");
        Assert.assertTrue(registerPage.verifyRegistrationError(arr));
    }

    @Test( dataProvider = "verifyRegistrationWithCorrectData", description = "Registration with not correct mobile phone" )
    @Description( "Registration with not correct mobile phone" )
    public void RegistrationTestWithNotCorrectMobilePhone(Account account) {
        account.setMobilephone("serw");
        RegistrationTestWithPersonData(account);
        LOGGER.info("Verify Registration Error");
        RegisterPage registerPage = new RegisterPage(driver);
        ArrayList<String> arr = new ArrayList<>();
        arr.add("phone_mobile is invalid.");
        Assert.assertTrue(registerPage.verifyRegistrationError(arr));
    }



    private void RegistrationTestWithPersonData(Account account) {
        LOGGER.debug("HomePage init");
        HomePage homePage = new HomePage(driver);
        LOGGER.info("HomePage open");
        homePage.open();
        LOGGER.info("Sing In");
        homePage.singIn();
        LOGGER.info("Go To Register With Email");
        homePage.goToRegisterWithEmail(account.getEmail());
        LOGGER.debug("RegisterPage init");
        RegisterPage registerPage = new RegisterPage(driver);
        LOGGER.info("Fill Registration Form");
        registerPage.fillRegistrationForm(account);
        LOGGER.info("Send Registration Form");
        registerPage.sendRegistrationForm();
    }



}
