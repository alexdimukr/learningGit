package com.DSN.locators;

import org.openqa.selenium.By;

public enum HelloFreshLocators {
    PLANS_LOCATOR(By.id("id-nav-Our_Plans-1")),
    LOGIN_BUTTON_LOCATOR(By.id("login-button")),
    EMAIL_LOCATOR(By.id("email-input")),
    PASSWORD_LOCATOR(By.id("password-input")),
    SUBMIT_BUTTON_LOCATOR(By.id("submit-login-button")),
    INCORRECT_MESSAGE_LOCATOR(By.cssSelector("#authModal > div > div > div > span > hf-login > form > div.modal-body > small"));

    private By locator;

    HelloFreshLocators(By locator) {
        this.locator = locator;
    }

    public By getLocator(){
        return locator;
    }

}
