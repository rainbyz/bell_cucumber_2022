package selenide.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import selenide.helpers.CustomAllureSelenide;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static selenide.helpers.Properties.browserConfigurationProperties;
import static selenide.helpers.Properties.timeProperties;

public class Hooks {
    @Before(order = 1)
    public void setListener() {
        SelenideLogger.addListener("AllureSelenide",
                new CustomAllureSelenide().screenshots(true).savePageSource(true));
    }

    @Before(order = 2)
    public void setOptions() {
        Configuration.timeout = timeProperties.getSmallTimeout();

        Configuration.browserPosition = browserConfigurationProperties.browserPosition();
        // Configuration.browserSize = browserConfigurationProperties.browserSize();
        Configuration.reopenBrowserOnFail = true;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY,
                new ChromeOptions()
                        .addArguments("--start-maximized")
                        .addArguments("--disable-extensions"));
        Configuration.browserCapabilities = capabilities;
    }

    @After
    public void closeBrowser() {
        closeWebDriver();
    }
}