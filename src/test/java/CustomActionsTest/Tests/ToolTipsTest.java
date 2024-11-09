package CustomActionsTest.Tests;

import CustomActionsTest.Pages.ToolTips;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.logging.Level;

import static CustomActionsTest.Pages.ToolTips.openPage;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static java.util.logging.Logger.getLogger;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToolTipsTest {

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
    @EnumSource(ToolTips.class)
    public void hoverOn(ToolTips toolTip) {
        System.out.println("Тестируем отклик при наведении на элемент " + toolTip.name() + ".");
        toolTip.scrollTo();
        String result = toolTip.hoverOn();
        assertEquals("Test passed!", result, "\nОшибка: наведение на элемент " + toolTip.name() + " не дало отклика!\nТест провален! Подробности:\n" + result);
        System.out.println("\nТест прошёл успешно!");
    }
}
