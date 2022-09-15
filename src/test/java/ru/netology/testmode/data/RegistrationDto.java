package ru.netology.testmode.data;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class RegistrationDto {
    private final String login;
    private final String password;
    private final String status;
}
