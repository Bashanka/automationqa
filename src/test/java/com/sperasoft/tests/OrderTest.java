package com.sperasoft.tests;

import com.sperasoft.models.Account;
import com.sperasoft.pages.HomePage;
import com.sperasoft.pages.OrderPage;
import com.sun.org.apache.xpath.internal.operations.Or;
import io.qameta.allure.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class OrderTest extends BaseTest {

    private static final Logger LOGGER = LogManager.getLogger(RegistrationTest.class.getName());

    @DataProvider(name = "activeAccount")
    private Object[][] activeAccount() {
        return dataPool.getData();
    }

    @Test( dataProvider = "activeAccount", description = "Test order" )
    @Description( "Test order" )
    public void testOrder(Account account) {
        HomePage homePage = new HomePage(driver);

        homePage.open();
        LOGGER.info("Account login");
        homePage.singIn();
        homePage.goToPersonalAccount(account.getEmail(), account.getPasswd());
        LOGGER.info("Go to homepage");
        homePage.open();

        OrderPage orderPage = new OrderPage(driver);
        LOGGER.info("Add to card");
        orderPage.addToCard();
        LOGGER.info("Order it");
        orderPage.order();
        LOGGER.info("Go to orders");
        orderPage.goToOrders();
        LOGGER.info("Proof it");
        Assert.assertTrue(orderPage.verifyOrder());
        homePage.signOut();

    }

}
