package com.BROKEN.glue;

import com.BROKEN.framework.Browser;
import com.BROKEN.framework.Log;
import com.BROKEN.pageObjects.HelloFreshPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class HelloFreshStepsDef {

    @Given("^I go to the main page \"(.*?)\"$")
    public void goToMainPage(String pageUrl) {
        Browser.setURL("http://" + pageUrl);
        HelloFreshPage freshPage = new HelloFreshPage();
        freshPage.navigateToHomePage();
    }

    @When("^I Enter email = \"(.*?)\"$")
    public void enterEmail(String email) {
        HelloFreshPage freshPage = new HelloFreshPage();
        freshPage.enterEmail(email);
    }

    @When("^I Enter password = \"(.*?)\"$")
    public void enterPassword(String password) {
        HelloFreshPage freshPage = new HelloFreshPage();
        freshPage.enterPassword(password);
    }

    @When("^I click \"(.*?)\"")
    public void click() {
        HelloFreshPage freshPage = new HelloFreshPage();
        freshPage.clickCategory("Our plans");
    }

    @Then("^I should see message \"(.*?)\"$")
    public void iShouldSeeLogInWindowWithIncorrectMessage(String message) {
        HelloFreshPage freshPage = new HelloFreshPage();
        Assert.assertEquals(message, freshPage.getIncorrectMessageFromLoginWindow());
    }

    @Then("^I should see Plans page")
    public void iShouldSeePlansPage() {
        HelloFreshPage freshPage = new HelloFreshPage();
        Log.info("Current URL: " + freshPage.getUrl());
        Assert.assertNull("https://www.hellofresh.com/tasty/food-boxes/", freshPage.getUrl());
    }

    @And("^I click \"([^\"]*)\" to submit$")
    public void iClickToSubmit()  {
        HelloFreshPage freshPage = new HelloFreshPage();
        freshPage.clickCategory("Login");
    }
}
