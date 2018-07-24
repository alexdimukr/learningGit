package com.DSN.runners;

import com.DSN.framework.Browser;
import com.DSN.framework.Global;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

@CucumberOptions(features = "src/test/resources/features/DemoHelloFresh.feature",
        glue = "com.DSN.glue",
        plugin = {"pretty", "json:target/DemoHelloFresh.json"})
public class DemoHelloFreshRunner extends AbstractTestNGCucumberTests {
}