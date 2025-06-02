package niffler.test;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureId;
import niffler.dto.UserModel;
import niffler.jupiter.ScreenshotExtension;
import niffler.jupiter.User;
import niffler.jupiter.UsersExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@ExtendWith({ScreenshotExtension.class, UsersExtension.class})
public class NifflerLoginTest {

    @AllureId("1")
    @Test
    void mainPageMustShowAfterLoginTest(@User(userType = User.UserType.ADMIN) UserModel user) {
        System.out.println("### Test 1 " + user.toString());
        Allure.step("Check login", () -> {
            open("http://127.0.0.1:9000/login");
            $("input[name='username']").setValue("Kaiser");
            $("input[name='password'").setValue("DarkKaiser93");
            $("button[type='submit']").click();
            $(".MuiTypography-root.MuiTypography-h5.css-126ce46")
                    .shouldBe(Condition.visible);
        });
    }

    @AllureId("2")
    @Test
    void mainPageMustShowAfterLoginTest2(@User(userType = User.UserType.ADMIN) UserModel user) {
        System.out.println("### Test 2 " + user.toString());
        Allure.step("Check login", () -> {
            open("http://127.0.0.1:9000/login");
            $("input[name='username']").setValue("Kaiser");
            $("input[name='password'").setValue("DarkKaiser93");
            $("button[type='submit']").click();
            $(".MuiTypography-root.MuiTypography-h5.css-126ce46")
                    .shouldBe(Condition.visible);
        });
    }

    @AllureId("3")
    @Test
    void mainPageMustShowAfterLoginTest3(@User UserModel user) {
        System.out.println("### Test 3 " + user.toString());
        Allure.step("Check login", () -> {
            open("http://127.0.0.1:9000/login");
            $("input[name='username']").setValue("Kaiser");
            $("input[name='password'").setValue("DarkKaiser93");
            $("button[type='submit']").click();
            $(".MuiTypography-root.MuiTypography-h5.css-126ce46")
                    .shouldBe(Condition.visible);
        });
    }

    @AllureId("4")
    @Test
    void mainPageMustShowAfterLoginTest4(@User UserModel user) {
        Allure.step("Check login", () -> {
            System.out.println("### Test 4 " + user.toString());
            open("http://127.0.0.1:9000/login");
            $("input[name='username']").setValue("Kaiser");
            $("input[name='password'").setValue("DarkKaiser93");
            $("button[type='submit']").click();
            $(".MuiTypography-root.MuiTypography-h5.css-126ce46")
                    .shouldBe(Condition.visible);
        });
    }

    @AllureId("5")
    @Test
    void mainPageMustShowAfterLoginTest5(@User UserModel user) {
        Allure.step("Check login", () -> {
            System.out.println("### Test 5 " + user.toString());
            open("http://127.0.0.1:9000/login");
            $("input[name='username']").setValue("Kaiser");
            $("input[name='password'").setValue("DarkKaiser93");
            $("button[type='submit']").click();
            $(".MuiTypography-root.MuiTypography-h5.css-126ce46")
                    .shouldBe(Condition.visible);
        });
    }
}
