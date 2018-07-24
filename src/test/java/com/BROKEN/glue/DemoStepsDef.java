package com.DSN.glue;

import com.DSN.pageObjects.GooglePage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.xpath.operations.String;
import org.testng.Assert;

public class DemoStepsDef {

    @Given("^I am navigated to main page$")
    public void navigatedToMainPage() throws Throwable {
        GooglePage gPage = new GooglePage();
        gPage.navigateToHomePage();
    }

    @When("^I am searching for a string pattern (.*)")
    public void iAmSearchingForAStringPatternAutomation(String searchPattern) throws Throwable {
        GooglePage gPage = new GooglePage();
        gPage.searchFor(searchPattern);
    }

    @Then("^I should see at least 1 search result$")
    public void iShouldSeeAtLeastSearchResult() throws Throwable {
        GooglePage gPage = new GooglePage();
        String firstResultLinkAnchor = gPage.getFirstSearchResultMainLinkAnchor();
        Assert.assertFalse(firstResultLinkAnchor.equals(""),"No results found! ");
    }

    @Then("^I should not see any search result$")
    public void iShouldNotSeeAnySearchResult() throws Throwable {
        GooglePage gPage = new GooglePage();
        Assert.assertFalse(gPage.isResultProvided(),"There is a result found! ");
    }
}