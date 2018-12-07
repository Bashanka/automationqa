package com.sperasoft.tests;

import com.sperasoft.Models.PersonData;
import com.sperasoft.pages.HomePage;
import com.sperasoft.pages.RegisterPage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTest extends BaseTest {

    @DataProvider( name = "verifyRegistrationWithCorrectData" )
    public static Object[] verifyRegistrationWithCorrectData() {
        return new PersonData[] {
                new PersonData(PersonData.Genre.MALE, createLetterString(), createLetterString(),
                        createNumberLetterString() + "@gmail.com", createNumberLetterString(), createLetterString(),
                        createLetterString(), createState(), createNumberString(5), createNumberString(11),
                        createLetterString())
        };
    }

    @Test( dataProvider = "verifyRegistrationWithCorrectData" )
    public void test1(PersonData personData) {

        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.goToRegisterWithEmail(personData.getEmail());
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.waitUntilPageLoaded();
        registerPage.fillRegistrationFormWithData(personData);
        Assert.assertTrue(registerPage.verifyRegistration());
        WebDriverWait wait = new WebDriverWait(driver, 30);
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
