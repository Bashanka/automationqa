package com.sperasoft;

import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class TestClass {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Before Suite method");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("After Suite method");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("Before Test method");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("After Test method");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("Before Class method");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("After Class method");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Before Method");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("After Method");
    }

    @BeforeGroups
    public void beforeGroups() {
        System.out.println("Before Groups");
    }

    @AfterGroups
    public void afterGroups() {
        System.out.println("After Groups");
    }


    @Test( groups = "group1" )
    @Parameters( {"a", "b"} )
    public void testNGTask1Test1(int a, int b) {
        Assert.assertEquals(a, b);
        Assert.assertNotEquals(5, 5);
        Assert.assertTrue(a > 0);
        Assert.assertFalse(b < 10);
        Assert.fail();
    }

    @Test( groups = "group1" )
    @Parameters( {"a", "b"} )
    public void testNGTask1Test2(int a, int b) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(5, 5);
        softAssert.assertTrue(a < b);
        softAssert.assertAll();
    }

}
