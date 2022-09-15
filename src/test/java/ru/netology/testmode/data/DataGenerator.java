package ru.netology.testmode.data;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;


import java.util.Locale;

import static io.restassured.RestAssured.given;


public class DataGenerator {

    private DataGenerator() {
    }

    public static class Registration {
        private static final RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setPort(9999)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        private Registration() {
        }

        public static RegistrationDto getUser(String locale, String status) {


            Faker faker = new Faker(new Locale("en"));
            return new RegistrationDto(faker.name().username(),
                    faker.internet().password(),
                    status
            );
        }


        public static RegistrationDto getRegisteredUser(String locale, String status) {
            RegistrationDto user = getUser(locale, status);
            // сам запрос
            given() // "дано"
                    .spec(requestSpec) // указываем, какую спецификацию используем
                    .body(new RegistrationDto("vasya", "password", "active")) // передаём в теле объект, который будет преобразован в JSON
                    .body(new RegistrationDto("misha", "passwordM", "blocked"))
                    .when() // "когда"
                    .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                    .then() // "тогда ожидаем"
                    .statusCode(200); // код 200 OK
            return user;
        }

    }
}














