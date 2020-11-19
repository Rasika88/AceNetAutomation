import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = {"C:/AceHardware/OSAF_Acenet/autowebcucumber/autowebcucumber/src/test/resources/functionaltest/AceNetOrderScenario.feature:36"},
        plugin = {"com.cucumber.listener.ExtentCucumberFormatter:C:/AceHardware/OSAF_Acenet/autowebcucumber/autowebcucumber/target/cucumber-parallel/5.html"},
        monochrome = true,
        glue = {"stepDefinitions"})
public class ParallelAcenetorderscenarioIT {
}
