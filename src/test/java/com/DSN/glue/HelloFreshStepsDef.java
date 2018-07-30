package com.DSN.glue;

import com.DSN.framework.BasePage;
import com.DSN.framework.Browser;
import com.DSN.framework.Log;
import com.DSN.pageObjects.HelloFreshPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class HelloFreshStepsDef extends BasePage {

    @Given("^I go to the main page \"(.*?)\"$")
    public void goToMainPage(String pageUrl){
        Browser.setURL("http://" + pageUrl);
        HelloFreshPage freshPage = new HelloFreshPage();
        freshPage.navigateToHomePage();
    }

    @When("^I Enter email = \"(.*?)\"$")
    public void enterEmail(String email){
        HelloFreshPage freshPage = new HelloFreshPage();
        freshPage.enterEmail(email);
    }

    @When("^I Enter password = \"(.*?)\"$")
    public void enterPassword(String password){
        HelloFreshPage freshPage = new HelloFreshPage();
        freshPage.enterPassword(password);
    }

    @And("^I click Login to submit$")
    public void iClickToSubmit(){
      HelloFreshPage freshPage = new HelloFreshPage();
      freshPage.clickSubmit();
    }

    @When("^I click \"(.*?)\"$")
    public void click(String category){
      HelloFreshPage freshPage = new HelloFreshPage();
      freshPage.clickCategory(category);
    }

    @Then("^I should see message \"(.*?)\"$")
    public void iShouldSeeLogInWindowWithIncorrectMessage(String message){
        HelloFreshPage freshPage = new HelloFreshPage();
        Assert.assertEquals(freshPage.getIncorrectMessageFromLoginWindow(), message, "Test is passed but actual results are wrong. Incorrect message.");
    }

    @Then("^I should see Plans page")
    public void iShouldSeePlansPage(){
        HelloFreshPage freshPage = new HelloFreshPage();
        Log.info("Current URL: " + freshPage.getUrl());
        Assert.assertTrue(freshPage.getUrl().contains("www.hellofresh.com/plans"), "We are not on Our Plans page");
    }

}
