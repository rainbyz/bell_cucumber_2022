package selenide.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.switchTo;

public class YandexSearchResultsPage {
    protected String results = "//li[contains(@class, 'serp-item')]";

    public void openLinkByTitle(String title) {
        ElementsCollection temp = $$x(results);
        for (SelenideElement element : temp
        ) {
            if (element.has(text(title))) {
                element.$x(".//a[@href]")
                        .shouldBe(visible)
                        .shouldBe(editable)
                        .click();
            }
        }
        switchTo().window(2);
    }
}
