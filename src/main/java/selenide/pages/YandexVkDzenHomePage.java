package selenide.pages;

import com.codeborne.selenide.ex.UIAssertionError;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;
import static selenide.helpers.Properties.timeProperties;

public class YandexVkDzenHomePage {
    protected String searchFrame = "(//iframe)[1]";
    protected String inputField = "//input[@name = 'text']";
    protected String popup = "//div[@class = 'zen-ui-modal__content']";
    protected String popupButton = "//button[contains(string(), 'Продолжить')]";

    public void searchForKeyword(String searchKeyword) {
        if (infoBannerAppears()) {
            $x(popupButton).shouldBe(visible)
                    .shouldBe(editable)
                    .click();
        }

        switchTo().frame($x(searchFrame).toWebElement());
        $x(inputField).shouldBe(visible)
                .shouldBe(editable)
                .setValue(searchKeyword)
                .pressEnter();
        switchTo().window(1);
    }

    private boolean infoBannerAppears() {
        try {
            $x(popup).should(appear, Duration.ofMillis(timeProperties.getDefaultTimeout()));
            return true;
        } catch (UIAssertionError e) {
            return false;
        }
    }
}