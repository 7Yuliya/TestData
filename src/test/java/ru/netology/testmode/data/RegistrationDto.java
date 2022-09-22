package ru.netology.testmode.data;


import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class RegistrationDto {
    String login;
    String password;
    String status;
}