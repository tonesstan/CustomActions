package CustomActionsTest.Tests;

import CustomActionsTest.Pages.Buttons;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.logging.Level;

import static CustomActionsTest.Pages.Buttons.openPage;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static java.util.logging.Logger.getLogger;
import static org.junit.jupiter.api.Assertions.*;

public class ButtonsTest {

    private static Level previousLevel;

    @BeforeAll
    public static void setUp() {
        previousLevel = getLogger("").getLevel();
        getLogger("").setLevel(Level.WARNING);
        Configuration.browser = "firefox";
        Configuration.browserCapabilities = new FirefoxOptions().setPageLoadStrategy(PageLoadStrategy.EAGER)
                .addArguments("--headless", "--window-size=1920,1080", "--disable-notifications", "--disable-gpu", "--disable-dev-tools", "--fastSetValue");
        openPage();
    }

    @AfterAll
    public static void tearDown() {
        closeWebDriver();
        getLogger("").setLevel(previousLevel);
    }

    @ParameterizedTest(name = "Проверка отклика при наведении на элемент {0}")
    @EnumSource(Buttons.class)
    public void hoverOn(Buttons button) {
        System.out.println("Тестируем специальное нажатие на кнопку " + button.name() + ".");
        button.scrollTo();
        String result = button.complexClick();
        assertEquals("Test passed!", result,"\nОшибка: сложный клик по элементу " + button.name() + " не дал отклика!\nТест провален! Подробности:\n" + result);
        System.out.println("\nТест прошёл успешно!");
    }
}