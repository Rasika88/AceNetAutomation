import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = {"C:/AceHardware/OSAF_Acenet/autowebcucumber/autowebcucumber/src/test/resources/functionaltest/SivaTest.feature:34"},
        plugin = {"com.cucumber.listener.ExtentCucumberFormatter:C:/AceHardware/OSAF_Acenet/autowebcucumber/autowebcucumber/target/cucumber-parallel/10.html"},
        monochrome = true,
        glue = {"stepDefinitions"})
public class ParallelSivatestIT {
}
