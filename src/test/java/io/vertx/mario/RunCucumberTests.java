package io.vertx.mario;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by Sudhir on 26/10/16.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        glue = "io.vertx.mario.bdd",
        plugin = {
                "pretty",
                "html:build/test/cucumber",
                "json:build/test/cucumber.json"
        }
)
public class RunCucumberTests {}
