package com.DSN.runners;

import com.DSN.framework.Browser;
import com.DSN.framework.Global;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

@CucumberOptions(features = "src/test/resources/features/Demo.feature",
    glue = "com.DSN.glue",
    plugin = {"pretty", "json:target/Demo.json"})
public class DemoRunner extends AbstractTestNGCucumberTests {
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