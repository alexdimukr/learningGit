package com.BROKEN.framework;

public abstract class BasePage {

    public void navigateToHomePage(Global.eLoginDT... user) {
        Browser.get().get(Browser.getURL());
        Browser.waitForAngular();
    }

}