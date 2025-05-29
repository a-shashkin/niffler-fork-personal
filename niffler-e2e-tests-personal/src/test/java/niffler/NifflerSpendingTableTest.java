package niffler;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class NifflerSpendingTableTest extends BaseTest {

    @Test
    void spendingMustShowInTableTest() {
        open("http://127.0.0.1:9000/login");
        $("input[name='username']").setValue("testuser");
        $("input[name='password'").setValue("testuser");
        $(".form__submit").click();
        $(byText("Обучение в QA.GURU ADVANCED")).shouldBe(Condition.visible);
        $(byText("1000.5 ₽")).shouldBe(Condition.visible);
    }
}
