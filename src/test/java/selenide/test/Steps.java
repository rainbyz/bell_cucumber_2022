package selenide.test;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import selenide.pages.MarketCatalogPage;
import selenide.pages.MarketHomePage;
import selenide.pages.YandexSearchResultsPage;
import selenide.pages.YandexVkDzenHomePage;

import static com.codeborne.selenide.Selenide.open;
import static selenide.helpers.Properties.urlProperties;

public class Steps {
    @Дано("Открыть главную страница «Яндекс.Поиска» \\(перенаправляет на новый «Дзен»)")
    public void openYandexVkDzen() {
        open(urlProperties.getYandexSearchHomeUrl());
    }

    @Когда("^Выполнить поиск в «Яндекс.Поиске» по ключевому слову '(.*)'$")
    public void searchForKeyword(String searchKeyword) {
        YandexVkDzenHomePage yandexVkDzenHomePage = new YandexVkDzenHomePage();
        yandexVkDzenHomePage.searchForKeyword(searchKeyword);
    }

    @И("^Открыть страницу с заголовком '(.*)'$")
    public void openLinkByTitle(String title) {
        YandexSearchResultsPage yandexSearchResultsPage = new YandexSearchResultsPage();
        yandexSearchResultsPage.openLinkByTitle(title);
    }

    @И("Открыть окно «Каталога»")
    public void openCatalogPopup() {
        MarketHomePage marketHomePage = new MarketHomePage();
        marketHomePage.openCatalogPopup();
    }

    @И("^Выбрать раздел/категорию '(.*)' и перейти на подраздел/подкатегорию '(.*)'$")
    public void selectCategoryAndSubcategory(String category, String subcategory) {
        MarketHomePage marketHomePage = new MarketHomePage();
        marketHomePage.selectCategoryAndSubcategory(category, subcategory);
    }

    @И("^Указать параметр «Производитель»: (.*)$")
    public void setManufacturer(String manufacturer) {
        MarketCatalogPage marketCatalogPage = new MarketCatalogPage();
        marketCatalogPage.setCheckbox("Производитель", manufacturer);
    }

    @Тогда("^Проверить, что каждый результат поиска содержит ключевое слово: (.*)$")
    public void checkResults(String keyWord) {
        MarketCatalogPage marketCatalogPage = new MarketCatalogPage();
        marketCatalogPage.checkResults(keyWord);
    }
}