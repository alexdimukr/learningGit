package com.DSN.locators;

import org.openqa.selenium.By;

public enum HelloFreshLocators {
    PLANS_LOCATOR(By.xpath("//*[@id='main-navigation']/a[contains(text(), 'Our Plans')]")),
    LOGIN_BUTTON_LOCATOR(By.xpath("//div/section/section[2]/header/div[2]/div[2]/a/span/span")),
    EMAIL_LOCATOR(By.id("email-input")),
    PASSWORD_LOCATOR(By.id("password-input")),
    SUBMIT_BUTTON_LOCATOR(By.id("submit-login-button")),
    INCORRECT_MESSAGE_LOCATOR(By.xpath("//form[@name='loginForm']/div/small"));

    private By locator;

    HelloFreshLocators(By locator) {
        this.locator = locator;
    }

    public By getLocator(){
        return locator;
    }

}
