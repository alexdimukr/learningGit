package com.DSN.runners;

import com.DSN.framework.Browser;
import com.DSN.framework.Global;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

@CucumberOptions(features = "src/test/resources/features/DemoHelloFresh.feature",
    glue = "com.DSN.glue",
    format = {"pretty", "json:target/DemoHelloFresh.json"})
public class DemoHelloFreshRunner extends AbstractTestNGCucumberTests {
  @BeforeClass
  public static void setUp() {
    Browser.setURL(Global.DEF_URL);
    Browser.setUSER(Global.eLoginDT.admin);
  }

  @AfterClass
  public static void tearDown(){
    Browser.close();
  }
}