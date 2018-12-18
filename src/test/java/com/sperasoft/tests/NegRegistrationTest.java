package com.sperasoft.tests;

import com.sperasoft.models.Account;
import com.sperasoft.pages.HomePage;
import com.sperasoft.pages.RegisterPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class NegRegistrationTest extends BaseTest {

    private static final Logger LOGGER = LogManager.getLogger( NegRegistrationTest.class.getName() );

    @DataProvider( name = "verifyRegistrationWithCorrectData" )
    private Object[][] verifyRegistrationWithCorrectData() {
        return dataPool.getData();
    }

    @Test( dataProvider = "verifyRegistrationWithCorrectData" )
    public void RegistrationTestWithNotCorrectFirstName(Account account) {
        account.setFirstname("1111");
        RegistrationTestWithPersonData(account);
    }

    @Test( dataProvider = "verifyRegistrationWithCorrectData" )
    public void RegistrationTestWithNotCorrectLastName(Account account) {
        account.setLastname("1111");
        RegistrationTestWithPersonData(account);
    }

    @Test( dataProvider = "verifyRegistrationWithCorrectData" )
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

    }

    @Test( dataProvider = "verifyRegistrationWithCorrectData" )
    public void RegistrationTestWithNotCorrectPostcode(Account account) {
        account.getAddresses().get(0).setPostcode("1111");
        RegistrationTestWithPersonData(account);
    }

    @Test( dataProvider = "verifyRegistrationWithCorrectData" )
    public void RegistrationTestWithNotCorrectHomePhone(Account account) {
        account.getAddresses().get(0).setHomephone("serw");
        RegistrationTestWithPersonData(account);
    }

    @Test( dataProvider = "verifyRegistrationWithCorrectData" )
    public void RegistrationTestWithNotCorrectMobilePhone(Account account) {
        account.setMobilephone("serw");
        RegistrationTestWithPersonData(account);
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
        LOGGER.info("Verify Registration Error");
        Assert.assertTrue(registerPage.verifyRegistrationError());
    }



}
