package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"steps"},
        plugin = {"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm","pretty", "html:target/cucumber-reports.html", "json:target/cucumber-reports/cucumber.json"},
        monochrome = true)
public class TestRunner {
}


//
//@RunWith(Cucumber.class)
//@CucumberOptions
//        (plugin = {"io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm", "pretty", "json:target/cucumber-report/report.json"},
//                monochrome = true,
//                features = "src/test/resources/features",
//                glue = {"com.ee.automation.stepdefinitions"})
//
//public class RunTests {
//}
