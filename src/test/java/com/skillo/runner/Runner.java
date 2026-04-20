package com.skillo.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources",
    glue = {"com.skillo.stepDefinations", "com.skillo.hooks"},
    plugin = {
        "pretty",
        "html:target/cucumber-report.html",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        
    },
    monochrome = true
)
public class Runner extends AbstractTestNGCucumberTests {
}