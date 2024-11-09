package CustomActionsTest.Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public enum ToolTips {

    Button($("#toolTipButton")),
    Field($("#toolTipTextField")),
    Contrary($x("//a[text()='Contrary']")),
    Digits($x("//a[text()='1.10.32']"));

    private final SelenideElement element;

    ToolTips(SelenideElement element) {this.element = element;}

    public static void openPage () {
        open("https://demoqa.com/tool-tips");
        $x("//h1[@class='text-center' and text()='Tool Tips']").shouldBe(visible);
    }

    public void scrollTo() {executeJavaScript("arguments[0].scrollIntoView({block: 'center'});", this.element);}

    public String hoverOn() {
        try {switch (this.name()) {
            case "Button":
                this.element.hover();
                this.element.shouldHave(attributeMatching("aria-describedby", ".*buttonToolTip.*"));
                break;
            case "Field":
                this.element.hover();
                this.element.shouldHave(attributeMatching("aria-describedby", ".*textFieldToolTip.*"));
                break;
            case "Contrary":
                this.element.hover();
                this.element.shouldHave(attributeMatching("aria-describedby", ".*contraryTexToolTip.*"));
                break;
            case "Digits":
                this.element.hover();
                this.element.shouldHave(attributeMatching("aria-describedby", ".*sectionToolTip.*"));
                break;
            }
            return "Test passed!";
        } catch (AssertionError e) {return e.getMessage();}
    }

}
