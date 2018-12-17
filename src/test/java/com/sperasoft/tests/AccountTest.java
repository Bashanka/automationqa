package com.sperasoft.tests;

import com.sperasoft.models.Account;
import com.sperasoft.pages.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.asserts.SoftAssert;

public class AccountTest extends BaseTest{

    private static final Logger LOGGER = LogManager.getLogger( RegistrationTest.class.getName() );

    @DataProvider( name = "activeAccount" )
    private Object[][] activeAccount() {
        return dataPool.getData();
    }

    @Test(dataProvider = "activeAccount")
    public void verifyPersonInformation( Account account ) {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        if (parameters.get("login").equals("true")) {
            LOGGER.info("Account login");
            homePage.singIn();
            homePage.goToPersonalAccount(account.getEmail(), account.getPasswd());
        }
        AccountPage accountPage = new AccountPage(driver);
        LOGGER.info("Account goToPersonalInfo");
        accountPage.goToPersonalInfo();
        LOGGER.info("Account create PersonalInfoPage");
        PersonalInfoPage personalInfoPage = new PersonalInfoPage(driver);
        LOGGER.info("Account verifyPersonalInfo");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(personalInfoPage.verifyPersonalInfo(account));
        if (parameters.get("logout").equals("true")) {
            personalInfoPage.signOut();
        }
        softAssert.assertAll();
    }

    @Test(dataProvider = "activeAccount")
        public void changePersonInformation( Account account ) {

        HomePage homePage = new HomePage(driver);
        homePage.open();
        if (parameters.get("login").equals("true")) {
            LOGGER.info("Account login");
            homePage.singIn();
            homePage.goToPersonalAccount(account.getEmail(), account.getPasswd());
        }

        AccountPage accountPage = new AccountPage(driver);
        LOGGER.info("Account goToPersonalInfo");
        accountPage.goToPersonalInfo();

        LOGGER.info("Account create PersonalInfoPage");
        PersonalInfoPage personalInfoPage = new PersonalInfoPage(driver);

        LOGGER.info("Account changePersonalInfo");
        account.setFirstname(account.getFirstname() + "a");
        account.setLastname(account.getLastname() + "a");
        personalInfoPage.changePersonalName(account);

        LOGGER.info("Account goToAccount");
        (new PersonalInfoPage(driver)).goToAccount();
        LOGGER.info("Account goToPersonalInfo");
        (new AccountPage(driver)).goToPersonalInfo();


        SoftAssert softAssert = new SoftAssert();
        LOGGER.info("Account verifyPersonalInfo");
        softAssert.assertTrue((new PersonalInfoPage(driver)).verifyPersonalInfo(account));

        if (parameters.get("logout").equals("true")) {
            LOGGER.info("Account signOut");
            personalInfoPage.signOut();
        }
        softAssert.assertAll();
    }

    @Test(dataProvider = "activeAccount")
    public void verifyAddresses( Account account ) {

        HomePage homePage = new HomePage(driver);
        homePage.open();
        if (parameters.get("login").equals("true")) {
            LOGGER.info("Account login");
            homePage.singIn();
            homePage.goToPersonalAccount(account.getEmail(), account.getPasswd());
        }

        AccountPage accountPage = new AccountPage(driver);
        LOGGER.info("Account goToAddresses");
        accountPage.goToAddresses();


        AddressPage addressPage = new AddressPage(driver);
        SoftAssert softAssert = new SoftAssert();
        LOGGER.info("Account verifyAddress");
        softAssert.assertTrue(addressPage.verifyAddress(account));
        if (parameters.get("logout").equals("true")) {
            homePage.signOut();
        }
        softAssert.assertAll();

    }

    @Test(dataProvider = "activeAccount")
    public void changeAddress( Account account ) {

        HomePage homePage = new HomePage(driver);
        homePage.open();
        if (parameters.get("login").equals("true")) {
            LOGGER.info("Account login");
            homePage.singIn();
            homePage.goToPersonalAccount(account.getEmail(), account.getPasswd());
        }

        AccountPage accountPage = new AccountPage(driver);
        LOGGER.info("Account goToAddresses");
        accountPage.goToAddresses();

        AddressPage addressPage = new AddressPage(driver);

        account.getAddresses().get(0).setHomephone("123456789");
        LOGGER.info("Account changeAddressHomePhone");
        addressPage.changeAddressHomePhone(account.getAddresses().get(0).getHomephone());

        LOGGER.info("Account goToAddresses");
        (new AccountPage(driver)).goToAddresses();


        SoftAssert softAssert = new SoftAssert();
        LOGGER.info("Account verifyAddress");
        softAssert.assertTrue(( new AddressPage(driver)).verifyAddress(account));
        if (parameters.get("logout").equals("true")) {
            homePage.signOut();
        }
        softAssert.assertAll();

    }

}
