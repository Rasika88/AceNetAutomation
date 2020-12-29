import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = {"C:/Users/srama/Documents/GitHub/AceNetAutomation/autowebcucumber/src/test/resources/functionaltest/SivaTest.feature:220"},
        plugin = {"com.cucumber.listener.ExtentCucumberFormatter:C:/Users/srama/Documents/GitHub/AceNetAutomation/autowebcucumber/target/cucumber-parallel/18.html"},
        monochrome = true,
        glue = {"stepDefinitions"})
public class ParallelSivatestIT {
}
