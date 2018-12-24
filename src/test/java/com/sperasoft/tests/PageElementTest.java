package com.sperasoft.tests;

import com.sperasoft.models.Account;
import com.sperasoft.pages.HomePage;
import com.sperasoft.pages.RegisterPage;
import io.qameta.allure.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class PageElementTest extends BaseTest {

    private static final Logger LOGGER = LogManager.getLogger( NegRegistrationTest.class.getName() );

    @DataProvider( name = "verifyRegistrationWithCorrectData" )
    private Object[][] verifyRegistrationWithCorrectData() {
        return dataPool.getData();
    }

    @Test( dataProvider = "verifyRegistrationWithCorrectData", description = "Test elements on registration form" )
    @Description( "Test elements on registration form" )
    public void RegistrationPageElementsTest(Account account) {
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
        LOGGER.info("Check Page Elements");
        registerPage.verifyPageElements().assertAll();
    }

}
