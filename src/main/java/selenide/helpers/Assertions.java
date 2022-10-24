package selenide.helpers;

import io.qameta.allure.Step;
import org.junit.Assert;

public class Assertions {
    @Step("Проверка условия")
    public static void assertTrue(String message, boolean condition) {
        Assert.assertTrue(message, condition);
    }
}