import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        features = "src/test/java/features",
        plugin = {"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        glue = {"selenide.test"},
        tags = "@run"
)
public class RunnerTest {
}