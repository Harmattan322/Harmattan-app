package com.Harmattan.app;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by DadaL1fe on 25.05.2016.
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"stepDefinitions"},
        features = "src/test/java/resources/features/",
        tags = "@DNS")
public class RunCucumberTest {
}
