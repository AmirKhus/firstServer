package example.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
//loginUser.feature
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"example.cucumber.step"},
        features = {"src/test/java/resources/features"})
public class CucumberTestOptions {

}
