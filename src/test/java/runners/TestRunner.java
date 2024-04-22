package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        tags = "@tags",
        features = "src/test/resources/features",
        glue = "src/test/java/stepdefs",
        plugin = {"pretty", "html:target/cucumber-reports.html"}
)

public class TestRunner {
}
