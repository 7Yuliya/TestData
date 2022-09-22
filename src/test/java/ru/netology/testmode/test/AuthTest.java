package ru.netology.testmode.test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.testmode.data.DataGenerator.Registration.*;
import static ru.netology.testmode.data.DataGenerator.getRandomLogin;
import static ru.netology.testmode.data.DataGenerator.getRandomPassword;


public class AuthTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successfully login with active registered user")
    void shouldSuccessfulLoginIfRegisteredActiveUser() {
        var registeredUser = getRegisteredUser("active");

        $("span[data-test-id='login'] input").setValue(registeredUser.getLogin());
        $("span[data-test-id='password'] input").setValue(registeredUser.getPassword());

        $("button[data-test-id='action-login']").click();
        $("body div#root h2").shouldHave(text("Личный кабинет"));


    }

    @Test
    @DisplayName("Should get error message if login with not registered user")
    void shouldGetErrorIfNotRegisteredUser() {
        var notRegisteredUser = getUser("active");

        $("span[data-test-id='login'] input").setValue(notRegisteredUser.getLogin());
        $("span[data-test-id='password'] input").setValue(notRegisteredUser.getPassword());

        $("button[data-test-id='action-login']").click();
        $("div[data-test-id='error-notification']").shouldHave(text("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    @DisplayName("Should get error message if login with blocked registered user")
    void shouldGetErrorIfBlockedUser() {
        var blockedUser = getRegisteredUser("blocked");

        $("span[data-test-id='login'] input").setValue(blockedUser.getLogin());
        $("span[data-test-id='password'] input").setValue(blockedUser.getPassword());

        $("button[data-test-id='action-login']").click();
        $("div[data-test-id='error-notification']").shouldHave(text("Ошибка! Пользователь заблокирован"));
    }

    @Test
    @DisplayName("Should get error message if login with wrong login")
    void shouldGetErrorIfWrongLogin() {
        var registeredUser = getRegisteredUser("active");
        var wrongLogin = getRandomLogin();


        $("span[data-test-id='login'] input").setValue(wrongLogin);
        $("span[data-test-id='password'] input").setValue(registeredUser.getPassword());

        $("button[data-test-id='action-login']").click();
        $("div[data-test-id='error-notification']").shouldHave(text("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    @DisplayName("Should get error message if login with wrong password")
    void shouldGetErrorIfWrongPassword() {
        var registeredUser = getRegisteredUser("active");
        var wrongPassword = getRandomPassword();

        $("span[data-test-id='login'] input").setValue(registeredUser.getLogin());
        $("span[data-test-id='password'] input").setValue(wrongPassword);

        $("button[data-test-id='action-login']").click();
        $("div[data-test-id='error-notification']").shouldHave(text("Ошибка! Неверно указан логин или пароль"));
    }

}



