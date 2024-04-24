package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    monochrome = true,
    //        tags = "@ListInstruments",
//    tags = "@RetrieveInstruments",
            tags = "@RetrieveOptions",
    //    tags = "@RetrieveOptionsExp",
    //        tags = "@SearchInstrumentsFilter"
    //            tags = "@auth",
    features = "src/test/resources/features",
    glue = {"stepdefs"},
    plugin = {"pretty", "html:target/cucumber-reports.html"})
public class TestRunner {}
