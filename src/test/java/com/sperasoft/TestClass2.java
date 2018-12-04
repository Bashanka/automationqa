package com.sperasoft;

import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

@Listeners( CustomListener.class )
public class TestClass2 {

    @DataProvider( name = "dataProvider" )
    public Object[][] dataProvider() {
        return new Object[][]{
                { new Integer(1), new Integer(1) },
                { new Integer(2), new Integer(3) }
        };
    }

    @Test( dataProvider = "dataProvider" )
    @Parameters({"i", "j"})
    public void test1(Integer i, Integer j) {
        Assert.assertTrue(j - i >= 0);
    }

    @Test( dataProvider = "dataProvider" )
    @Parameters({"i", "j"})
    public void test2(Integer i, Integer j) {
        Assert.assertFalse(j - i >= 0);
    }

    @Test( dependsOnMethods = "test2" )

    public void testSkip(Integer i, Integer j) {
        Assert.assertTrue(j - i >= 0);
    }

    @Test( dataProvider = "dataProvider", groups = "testGroup")
    @Parameters({"i", "j"})
    public void test3(Integer i, Integer j) {
        Assert.assertEquals(i, j);
    }

    @Test( dataProvider = "dataProvider", groups = "testGroup" )
    @Parameters({"i", "j"})
    public void test4(Integer i, Integer j) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(i, j);
        softAssert.assertNotEquals(i, j);
        softAssert.assertAll();
    }
    public void test5() {
        Assert.fail();
    }

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

    @BeforeGroups("testGroup")
    public void beforeGroups() {
        System.out.println("Before Groups");
    }

    @AfterGroups("testGroup")
    public void afterGroups() {
        System.out.println("After Groups");
    }

}
