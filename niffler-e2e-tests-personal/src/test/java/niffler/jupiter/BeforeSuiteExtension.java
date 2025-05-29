package niffler.jupiter;

import com.codeborne.selenide.LocalStorage;
import com.codeborne.selenide.Selenide;
import niffler.retrofit.SpendApiClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.prompt;

public class BeforeSuiteExtension implements AroundAllTestsExtension {

    @Override
    public void beforeAllTests() {
        //AroundAllTestsExtension.super.beforeAllTests();
        //System.out.println("BEFORE SUITE!!!");

        SpendApiClient spendApiClient;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://127.0.0.1:8090/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        spendApiClient = retrofit.create(SpendApiClient.class);

        // здесь нужно дописать вызов метода /api/spends/add с телом из JSON-файла
        // через Retrofit с авторизацией через Bearer token

        Selenide.open("http://127.0.0.1:9000/login");
        $("input[name='username']").setValue("testuser");
        $("input[name='password']").setValue("testuser");
        $(".form__submit").click();

        LocalStorage localStorage = Selenide.localStorage();
        String bearerToken = localStorage.getItem("id_token");



        $("svg[data-testid='PersonIcon']").click();
        $(byText("Sign out")).click();
        $(byText("Log out")).click();
    }

    @Override
    public void afterAllTests() {
        //AroundAllTestsExtension.super.afterAllTests();
        System.out.println("AFTER SUITE!!!");
    }
}
