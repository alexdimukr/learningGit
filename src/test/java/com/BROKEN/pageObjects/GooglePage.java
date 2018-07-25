package com.BROKEN.pageObjects;

import com.BROKEN.framework.BasePage;
import com.BROKEN.framework.Browser;
import com.BROKEN.framework.Log;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class GooglePage extends BasePage {
    private static final String PATH_URL = "";
    private static final String PAGE_ID  = "";

    private static final By     SEARCH_FIELD_LOCATOR            = By.id("lst-ib");
    private static final String SEARCH_RESULT_LOCATOR           = "//*[@class='g'][%s]";
    private static final By     SEARCH_RESULT_MAINLINK_LOCATOR  = By.xpath(".//h3[@class='r']/a");

    public void searchFor (String searchPattern){
        WebElement searchField = Browser.get().findElement(SEARCH_FIELD_LOCATOR);
        searchField.sendKeys(searchPattern + Keys.ENTER);
    }

    public WebElement searchResultItem(int searchResultItemNum){
        return Browser.get().findElement(By.xpath(String.format(SEARCH_RESULT_LOCATOR, searchResultItemNum)));
    }

    public String getFirstSearchResultMainLinkAnchor (){
        WebElement el = searchResultItem(1);
        String linkText = el.findElement(SEARCH_RESULT_MAINLINK_LOCATOR).getText();
        Log.info("First Search result main link anchor: " + linkText);
        return linkText;
    }

    public boolean isResultProvided (){
        return Browser.hasElement(By.xpath(String.format(SEARCH_RESULT_LOCATOR, 1)));
    }

    public void navigateToHomePage() {
        Browser.get().get(Browser.getURL());
        Browser.waitForAngular();
    }
}
