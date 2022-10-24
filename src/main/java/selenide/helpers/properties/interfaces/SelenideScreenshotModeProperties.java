package selenide.helpers.properties.interfaces;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"file:src/main/resources/properties/selenideScreenshotMode.properties",
        "system:properties",
        "system:env"})
public interface SelenideScreenshotModeProperties extends Config {
    @Config.Key("type")
    String getType();
}