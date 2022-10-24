package selenide.pages;

import com.codeborne.selenide.ex.UIAssertionError;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static selenide.helpers.Properties.timeProperties;

public class MarketHomePage {
    protected String catalogButton = "//button[@id = 'catalogPopupButton']";
    protected String catalogPopup = "//*[@id = 'catalogPopup']";
    protected String categories = "//li[@data-zone-name = 'category-link']";
    protected String alsoButton = "//li//span[contains(text(), 'Ещё')]";
    protected String subcategories = "//ul[@data-autotest-id = 'subItems']//a";
    protected String promoBanner = "//div[@data-auto = 'yandexPlusPromoTooltip']";

    public void openCatalogPopup() {
        $x(catalogButton).shouldBe(visible)
                .shouldBe(editable)
                .click();
        $x(catalogPopup).shouldBe(visible);
    }

    public void selectCategoryAndSubcategory(String category, String subcategory) {
        $$x(categories).shouldBe(sizeGreaterThan(1),
                Duration.ofMillis(timeProperties.getDefaultTimeout()));

        if (promoBannerAppears())
            $x(promoBanner).$x("./following-sibling::button").shouldBe(visible)
                    .shouldBe(editable)
                    .click();

        $$x(categories).find(text(category))
                .shouldBe(visible)
                .shouldBe(editable)
                .hover();

        $$x(alsoButton)
                .asFixedIterable()
                .stream()
                .forEach(button -> button
                        .shouldBe(visible)
                        .shouldBe(editable)
                        .click());

        $$x(subcategories).find(text(subcategory))
                .shouldBe(visible)
                .shouldBe(editable)
                .click();
    }

    private boolean promoBannerAppears() {
        try {
            $x(promoBanner).should(appear, Duration.ofMillis(timeProperties.getDefaultTimeout()));
            return true;
        } catch (UIAssertionError e) {
            return false;
        }
    }
}