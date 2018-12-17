package com.sperasoft.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

abstract class AbstractContainer implements Element{

    private  WebElement wrappedElement;

    public final void init(WebElement wrappedElement) {
        this.wrappedElement = wrappedElement;
    }

    public boolean isDisplayed() {
        return wrappedElement.isDisplayed();
    }

}
