package CustomActionsTest.Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public enum Buttons {

    DoubleClickButton($("#doubleClickBtn")),
    RightClickButton($("#rightClickBtn")),
    ClickButton($x("//button[text()='Click Me']"));

    private final SelenideElement element;

    Buttons(SelenideElement element) {
        this.element = element;
    }

    public static void openPage () {
        open("https://demoqa.com/buttons");
        $x("//h1[@class='text-center' and text() = 'Buttons']").shouldBe(visible);
    }

    public void scrollTo() {executeJavaScript("arguments[0].scrollIntoView({block: 'center'});", this.element);}

    public String complexClick() {
        try {switch (this.name()) {
            case "DoubleClickButton":
                this.element.doubleClick();
                $("#doubleClickMessage").shouldBe(visible);
                break;
            case "RightClickButton":
                this.element.contextClick();
                $("#rightClickMessage").shouldBe(visible);
                break;
            case "ClickButton":
                this.element.click();
                $("#dynamicClickMessage").shouldBe(visible);
                break;
            }
            return "Test passed!";
        } catch (AssertionError e) {return e.getMessage();}
    }

}
