package ru.netology.testmode.test;


import lombok.val;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.testmode.data.DataGenerator;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class AuthTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successfully login with active registered user")
    void shouldSuccessfulLoginIfRegisteredActiveUser() {

        val user = DataGenerator.Registration.getRegisteredUser("en", "active");
        $("span[data-test-id='login'] input").setValue("vasya");
        $("span[data-test-id='password'] input").setValue("password");

        $("button[data-test-id='action-login']").click();
        $("body div#root h2").shouldHave(text("Личный кабинет"));
    }



    @Test
    @DisplayName("Should get error message if login with not registered user")
    void shouldGetErrorIfNotRegisteredUser() {

        val user = DataGenerator.Registration.getRegisteredUser("en", "active");
        $("span[data-test-id='login'] input").setValue("wrongLogin");
        $("span[data-test-id='password'] input").setValue("wrongPassword");

        $("button[data-test-id='action-login']").click();
        $("div[data-test-id='error-notification']").shouldHave(text("Неверно указан логин или пароль"));
    }

    @Test
    @DisplayName("Should get error message if login with blocked registered user")
    void shouldGetErrorIfBlockedUser() {

        val user = DataGenerator.Registration.getBlockedUser("en", "blocked");
        $("span[data-test-id='login'] input").setValue("vasya");
        $("span[data-test-id='password'] input").setValue("password");

        $("button[data-test-id='action-login']").click();
        $("div[data-test-id='error-notification']").shouldHave(text("Пользователь заблокирован"));
    }




    @Test
    @DisplayName("Should get error message if login with wrong login")
    void shouldGetErrorIfWrongLogin() {


        val user = DataGenerator.Registration.getRegisteredUser("en", "active");
        $("span[data-test-id='login'] input").setValue(user.getLogin() + "wrongLogin");
        $("span[data-test-id='password'] input").setValue(user.getPassword());

        $("button[data-test-id='action-login']").click();
        $("div[data-test-id='error-notification']").shouldHave(text("Неверно указан логин или пароль"));
    }



    @Test
    @DisplayName("Should get error message if login with wrong password")
    void shouldGetErrorIfWrongPassword() {

        val user = DataGenerator.Registration.getRegisteredUser("en", "active");
        $("span[data-test-id='login'] input").setValue(user.getLogin());
        $("span[data-test-id='password'] input").setValue(user.getPassword() + "wrongPassword");

        $("button[data-test-id='action-login']").click();
        $("div[data-test-id='error-notification']").shouldHave(text("Неверно указан логин или пароль"));
    }

}



