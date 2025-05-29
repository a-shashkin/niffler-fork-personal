package niffler;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class NifflerLoginTest {

    @Test
    void mainPageMustShowAfterLoginTest() {
        open("http://127.0.0.1:9000/login");
        $("input[name='username']").setValue("Kaiser");
        $("input[name='password'").setValue("DarkKaiser93");
        $("button[type='submit']").click();
        $(".MuiTypography-root.MuiTypography-h5.css-126ce46")
                .shouldBe(Condition.visible);
    }
}
