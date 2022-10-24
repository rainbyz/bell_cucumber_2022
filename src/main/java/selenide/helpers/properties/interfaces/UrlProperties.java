package selenide.helpers.properties.interfaces;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties",
        "system:env",
        "file:src/main/resources/properties/url.properties"
})
public interface UrlProperties extends Config {
    @Key("yandex.search.home.url")
    String getYandexSearchHomeUrl();
}