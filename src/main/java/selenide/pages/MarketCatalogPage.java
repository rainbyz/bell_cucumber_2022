package selenide.pages;

import com.codeborne.selenide.SelenideElement;
import selenide.helpers.Assertions;
import selenide.helpers.Properties;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class MarketCatalogPage extends MarketHomePage {
    protected String filters = "//fieldset";
    protected String expandFilterButton = ".//span[@role = 'button']";
    protected String searchField = ".//input[@type = 'text']";
    protected String resetSearchButton = ".//button[@title='Очистить']";
    protected String checkboxLocator = ".//label[contains(string(), '%s')]";
    protected String loader = "//span[@role='progressbar']";
    protected String showMoreButton = "//button[@data-auto = 'pager-more' and contains(string(), 'Показать ещё')]";
    protected String resultsLoader = "//*[@data-grabber='SearchSerp']//span[@role='progressbar']";
    protected String results = "(//*[@data-zone-name = 'SearchSerp'])[1]//article//h3//a[@title]";
    protected String resultsBottom = "//div[@data-zone-name = 'SearchPager']";

    public void checkResults(String keyWord) {
        loadAllResults();
        Assertions.assertTrue("В результатах поиска есть не только \"" + keyWord + "\"!",
                $$x(results)
                        .asDynamicIterable()
                        .stream()
                        .allMatch(title -> title.toString().contains(keyWord)));
    }

    private void loadAllResults() {
        $x(resultsBottom).scrollTo();

        long start = System.currentTimeMillis();
        while ($x(showMoreButton).exists()) {
            $x(showMoreButton)
                    .scrollTo()
                    .shouldBe(visible)
                    .shouldBe(editable)
                    .click();
            waitForLoader(loader);

            Assertions.assertTrue("Цикл загрузки результатов поиска превысил допустимое время работы",
                    System.currentTimeMillis() - start < Properties.timeProperties.getLoopTimeout());
        }
    }

    public void setCheckbox(String filterTitle, String checkboxTitle) {
        SelenideElement filter = $$x(filters).find(text(filterTitle)).shouldBe(visible);
        if (filter.$x(expandFilterButton).exists()) {
            filter.$x(expandFilterButton)
                    .shouldBe(visible)
                    .shouldBe(editable)
                    .click();
            waitForLoaderDisappear(loader);
        }

        if (filter.$x(searchField).exists()) {
            if (filter.$x(resetSearchButton).exists())
                filter.$x(resetSearchButton)
                        .shouldBe(visible)
                        .shouldBe(editable)
                        .click();
            filter.$x(searchField)
                    .shouldBe(visible)
                    .shouldBe(editable)
                    .setValue(checkboxTitle)
                    .pressEnter();
        }


        String fullCheckboxLocator = String.format(checkboxLocator, checkboxTitle);
        SelenideElement temp;
        temp = filter.$x(fullCheckboxLocator);
        temp
                .should(appear, Duration.ofMillis(Properties.timeProperties.getSmallTimeout()))
                .shouldBe(editable);
        if (!temp.isSelected()) {
            temp.click();
            waitForLoader(resultsLoader);
        }
    }

    private void waitForLoader(String locator) {
        $x(locator).should(appear,
                Duration.ofMillis(Properties.timeProperties.getDefaultTimeout()));
        $x(locator).should(disappear,
                Duration.ofMillis(Properties.timeProperties.getDefaultTimeout()));
    }

    private void waitForLoaderDisappear(String locator) {
        $x(locator).should(disappear,
                Duration.ofMillis(Properties.timeProperties.getDefaultTimeout()));
    }
}