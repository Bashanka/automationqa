package com.sperasoft.pages;

import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

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
    @FindBy(css = "td.history_invoice a")
    private WebElement downLoadPdfLink;

    public OrderPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void addToCard() {
        addToCardLink.click();
    }

    public void order() {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(proceedToCheckout));
        proceedToCheckout.click();

        wait.until(ExpectedConditions.visibilityOf(proceedToCheckout1));
        proceedToCheckout1.click();

        wait.until(ExpectedConditions.visibilityOf(proceedToCheckout2));
        proceedToCheckout2.click();

        checkbox.click();

        wait.until(ExpectedConditions.visibilityOf(proceedToCheckout3));
        proceedToCheckout3.click();

        wait.until(ExpectedConditions.visibilityOf(payment));
        payment.click();

        wait.until(ExpectedConditions.visibilityOf(proceedToCheckout2));
        proceedToCheckout2.click();
    }

    public void goToOrders() {
        backToOrdersLink.click();
    }

    public boolean verifyOrder() {
        if (!orderList.isDisplayed()) return false;
        driver.get(downLoadPdfLink.getAttribute("href"));
        waitForFileDownload(driver);
        return isFileDownloaded();
    }

    private boolean isFileDownloaded() {
        boolean flag = false;
        File dir = new File(DOWNLOADDIR);
        File[] dir_contents = dir.listFiles();

        for (int i = 0; i < dir_contents.length; i++) {
            if (dir_contents[i].getName().contains(".pdf"))
                return flag=true;
        }

        return flag;
    }

    private void waitForFileDownload(WebDriver driver){
        int totalTimeoutInMillis = TIMEOUT*1000;

        FluentWait<WebDriver> wait = new FluentWait(driver)
                .withTimeout(totalTimeoutInMillis, TimeUnit.MILLISECONDS)
                .pollingEvery(200, TimeUnit.MILLISECONDS);

        wait.until((WebDriver wd) -> isFileDownloaded());
    }
}
