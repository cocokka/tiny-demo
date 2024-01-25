package com.tinydemo.democucumber.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = ("feature"), glue = ("com.tinydemo.democucumber.steps"),
		plugin = { "html:target/cucumber-reports", "pretty" })
public class DemoRunner {

}
