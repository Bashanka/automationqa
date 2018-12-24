package com.sperasoft.tests;

import com.sperasoft.models.Account;
import com.sperasoft.pages.HomePage;
import com.sperasoft.pages.RegisterPage;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class RegistrationTest extends BaseTest {

    private static final Logger LOGGER = LogManager.getLogger( RegistrationTest.class.getName() );

    @DataProvider( name = "verifyRegistrationWithCorrectData" )
    private Object[][] verifyRegistrationWithCorrectData() {
        return dataPool.getData();
    }

    @Test( dataProvider = "verifyRegistrationWithCorrectData", description = "Test registration form with correct data" )
    @Description( "Test registration form with correct data" )
    public void RegistrationTestWithPersonData(Account account) {
        LOGGER.info("HomePage init");
        HomePage homePage = new HomePage(driver);
        LOGGER.info("HomePage open");
        homePage.open();
        LOGGER.info("HomePage singIn");
        homePage.singIn();
        LOGGER.info("HomePage goToRegisterWithEmail");
        homePage.goToRegisterWithEmail(account.getEmail());
        LOGGER.info("RegisterPage init");
        RegisterPage registerPage = new RegisterPage(driver);
        LOGGER.info("RegisterPage fillRegistrationForm");
        registerPage.fillRegistrationForm(account);
        LOGGER.info("RegisterPage sendRegistrationForm");
        registerPage.sendRegistrationForm();
        LOGGER.info("RegisterPage verifyRegistration");
        Assert.assertTrue(registerPage.verifyRegistration(account));
        if (parameters.get("logout").equals("true")) {
            LOGGER.info("RegisterPage signOut");
            registerPage.signOut();
        }
    }

    @Test( dataProvider = "verifyRegistrationWithCorrectData" )
    public void testGetAccountFromDataFile( Account account ) {

        System.out.println( account );
    }



}
