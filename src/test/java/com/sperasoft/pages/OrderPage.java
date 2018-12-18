package com.sperasoft.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage extends BasePage {

    @FindBy(css = "#homefeatured a.button.ajax_add_to_cart_button")
    private WebElement addToCardLink;
    @FindBy(css = "#layer_cart > div.clearfix > div.layer_cart_cart.col-xs-12.col-md-6 > div.button-container > a")
    private WebElement proceedToCheckout;
    @FindBy(css = "#center_column > p.cart_navigation.clearfix > a.button.btn.btn-default.standard-checkout.button-medium")
    private WebElement proceedToCheckout1;
    @FindBy(css = "#center_column > form > p > button")
    private WebElement proceedToCheckout2;
    @FindBy(css = "input#cgv")
    private WebElement checkbox;
    @FindBy(css = "#form > p > button")
    private WebElement proceedToCheckout3;
    @FindBy(css = "#HOOK_PAYMENT > div:nth-child(1) > div > p > a")
    private WebElement payment;
    @FindBy(css = "#center_column > p > a")
    private WebElement backToOrdersLink;
    @FindBy(id = "order-list")
    private WebElement orderList;

    public OrderPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void addToCard() {
        addToCardLink.click();
    }

    public void order() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(proceedToCheckout));
        proceedToCheckout.click();

        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(proceedToCheckout1));
        proceedToCheckout1.click();

        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(proceedToCheckout2));
        proceedToCheckout2.click();

        checkbox.click();

        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(proceedToCheckout3));
        proceedToCheckout3.click();

        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(payment));
        payment.click();

        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(proceedToCheckout2));
        proceedToCheckout2.click();
    }

    public void goToOrders() {
        backToOrdersLink.click();
    }

    public boolean verifyOrder() {
        return orderList.isDisplayed();
    }
}
