package ru.netology.testmode.data;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;


import java.util.Locale;


import static io.restassured.RestAssured.given;


public class DataGenerator {
    private static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();
    private static final Faker faker = new Faker(new Locale("en"));

    private DataGenerator() {
    }

    private static void sendRequest(RegistrationDto user) {
        given()
                .spec(requestSpec)
                .body(user)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

    public static String getRandomLogin() {

        String login = faker.internet().domainName();
        return login;
    }
    // TODO: добавить логику для объявления переменной login и задания её значения, для генерации
    //  случайного логина используйте faker

    public static String getRandomPassword() {

        String password = faker.internet().password();

        return password;
    }
    // TODO: добавить логику для объявления переменной password и задания её значения, для генерации
    //  случайного пароля используйте faker

    public static class Registration {
        private Registration() {
        }

        public static RegistrationDto getUser(String status) {
            return new RegistrationDto(getRandomLogin(), getRandomPassword(), status);
        }

        public static RegistrationDto getRegisteredUser(String status) {
            RegistrationDto registeredUser = getUser(status);
            sendRequest(registeredUser);
            return registeredUser;

        }
    }

}







