package com.BROKEN.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features/Demo.feature",
        glue = "com.BROKEN.glue",
        format = {"pretty", "json:target/Demo.json"})
public class DemoRunner extends AbstractTestNGCucumberTests {

}