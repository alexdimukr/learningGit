package com.BROKEN.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features/DemoHelloFresh.feature",
        glue = "com.BROKEN.glue",
        plugin = {"pretty", "json:target/DemoHelloFresh.json"})
public class DemoHelloFreshRunner extends AbstractTestNGCucumberTests {


}