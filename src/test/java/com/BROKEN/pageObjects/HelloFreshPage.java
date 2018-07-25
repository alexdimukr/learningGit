package com.BROKEN.pageObjects;

import com.BROKEN.framework.BasePage;
import com.BROKEN.framework.Browser;
import com.BROKEN.locators.HelloFreshLocators;
import org.openqa.selenium.WebElement;

public class HelloFreshPage extends BasePage {

    public void clickCategory(String category) {
        switch (category) {
            case "Our plans":
                Browser.get().findElement(HelloFreshLocators.PLANS_LOCATOR.getLocator()).click();
                break;
            case "Log in":
                Browser.get().findElement(HelloFreshLocators.LOGIN_BUTTON_LOCATOR.getLocator()).click();
                break;
            case "Login":
                Browser.get().findElement(HelloFreshLocators.SUBMIT_BUTTON_LOCATOR.getLocator()).click();
                break;
        }

        Browser.sleep(1);
    }

    public void enterEmail(String email) {
        WebElement emailElement = Browser.get().findElement(HelloFreshLocators.EMAIL_LOCATOR.getLocator());
        emailElement.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement emailElement = Browser.get().findElement(HelloFreshLocators.PASSWORD_LOCATOR.getLocator());
        emailElement.sendKeys(password);
    }

    public String getIncorrectMessageFromLoginWindow() {
        WebElement warningElement = Browser.get().findElement(HelloFreshLocators.INCORRECT_MESSAGE_LOCATOR.getLocator());
        Browser.sleep(1);
        return warningElement.getText();
    }

    public String getUrl() {
        return Browser.get().getCurrentUrl();
    }

}
