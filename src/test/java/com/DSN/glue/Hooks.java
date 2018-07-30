package com.DSN.glue;

import com.DSN.framework.Browser;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterMethod;

public class Hooks {
    @Before
    public void beforeScenario() {
        Browser.get().get("http://weirdpage.com");
    }

//  @AfterMethod
//  public void embedScreenshot(Scenario scenario){
//    if (scenario.isFailed()) {
//      final byte[] screenshot = ((TakesScreenshot)Browser.get()).getScreenshotAs(OutputType.BYTES);
//      scenario.embed(screenshot, "image/png");
//    }
//  }

    @After (order = 3)
    public void afterScenario() {
        Browser.close();
    }
}
