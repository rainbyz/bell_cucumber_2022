package selenide.helpers.properties.interfaces;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"file:src/main/resources/properties/time.properties",
        "system:properties",
        "system:env"})
public interface TimeProperties extends Config {
    @Key("small.timeout")
    int getSmallTimeout();

    @Key("default.timeout")
    int getDefaultTimeout();

    @Key("loop.timeout")
    int getLoopTimeout();
}