package com.DSN.glue;

import com.DSN.framework.Browser;
import com.DSN.framework.Log;
import com.DSN.pageObjects.HelloFreshPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class HelloFreshStepsDef {

    @Given("^I go to the main page \"(.*?)\"$")
    public void goToMainPage(String pageUrl) throws Throwable {
        Browser.setURL("http://" + pageUrl);
        HelloFreshPage freshPage = new HelloFreshPage();
        freshPage.navigateToHomePage();
    }

    @When("^I Enter email = \"(.*?)\"$")
    public void enterEmail(String email) throws Throwable {
        HelloFreshPage freshPage = new HelloFreshPage();
        freshPage.enterEmail(email);
    }

    @When("^I Enter password = \"(.*?)\"$")
    public void enterPassword(String password) throws Throwable {
        HelloFreshPage freshPage = new HelloFreshPage();
        freshPage.enterEmail(password);
    }

    @When("^I click \"(.*?)\"")
    public void click(String category) throws Throwable {
        HelloFreshPage freshPage = new HelloFreshPage();
        freshPage.clickCategory(category);
    }

    @Then("^I should see message \"(.*?)\"$")
    public void iShouldSeeLogInWindowWithIncorrectMessage(String message) throws Throwable {
        HelloFreshPage freshPage = new HelloFreshPage();
        Assert.assertEquals(message, freshPage.getIncorrectMessageFromLoginWindow());
    }

    @Then("^I should see Plans page")
    public void iShouldSeePlansPage() throws Throwable {
        HelloFreshPage freshPage = new HelloFreshPage();
        Log.info("Current URL: " + freshPage.getUrl());
        Assert.assertNull("https://www.hellofresh.com/tasty/food-boxes/", freshPage.getUrl());
    }
}
