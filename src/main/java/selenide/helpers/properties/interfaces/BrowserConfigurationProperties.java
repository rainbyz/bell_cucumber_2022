package selenide.helpers.properties.interfaces;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"file:src/main/resources/properties/browserConfiguration.properties",
        "system:properties",
        "system:env"})
public interface BrowserConfigurationProperties extends Config {
    @Config.Key("position")
    String browserPosition();

    @Config.Key("size")
    String browserSize();
}