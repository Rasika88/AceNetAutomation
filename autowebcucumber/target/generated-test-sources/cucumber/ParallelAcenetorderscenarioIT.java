import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = {"C:/Users/srama/Documents/GitHub/AceNetAutomation/autowebcucumber/src/test/resources/functionaltest/AceNetOrderScenario.feature:241"},
        plugin = {"com.cucumber.listener.ExtentCucumberFormatter:C:/Users/srama/Documents/GitHub/AceNetAutomation/autowebcucumber/target/cucumber-parallel/14.html"},
        monochrome = true,
        glue = {"stepDefinitions"})
public class ParallelAcenetorderscenarioIT {
}
