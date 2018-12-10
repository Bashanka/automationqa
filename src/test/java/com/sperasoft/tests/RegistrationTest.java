package com.sperasoft.tests;

import com.sperasoft.models.PersonData;
import com.sperasoft.pages.HomePage;
import com.sperasoft.pages.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class RegistrationTest extends BaseTest {

    private static final Logger LOGGER = LogManager.getLogger( RegistrationTest.class.getName() );

    @DataProvider( name = "verifyRegistrationWithCorrectData" )
    public static Object[] verifyRegistrationWithCorrectData() {
        return new PersonData[] {
                new PersonData(PersonData.Gender.MALE, createLetterString(), createLetterString(),
                        createNumberLetterString() + "@gmail.com", createNumberLetterString(), createLetterString(),
                        createLetterString(), createState(), createNumberString(5), createNumberString(11),
                        createLetterString())
        };
    }

    @Test( dataProvider = "verifyRegistrationWithCorrectData" )
    public void RegistrationTestWithPersonData(PersonData personData) {

        HomePage homePage = new HomePage(driver);
        LOGGER.info("HomePage init");
        homePage.open();
        LOGGER.info("HomePage open");
        homePage.goToRegisterWithEmail(personData.getEmail());
        LOGGER.info("HomePage goToRegisterWithEmail");
        RegisterPage registerPage = new RegisterPage(driver);
        LOGGER.info("RegisterPage init");
        registerPage.waitUntilPageLoaded();
        LOGGER.info("RegisterPage waitUntilPageLoaded");
        registerPage.fillRegistrationFormWithData(personData);
        LOGGER.info("RegisterPage fillRegistrationFormWithData");
        Assert.assertTrue(registerPage.verifyRegistration(personData));
        LOGGER.info("RegisterPage success verifyRegistration");
    }

    private static String createLetterString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 15) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    private static String createNumberLetterString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 15) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

    private static String createNumberString(int n) {
        String SALTCHARS = "1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < n) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

    private static String createState() {
        return Integer.toString((new Random()).nextInt(50) + 1);
    }

}
