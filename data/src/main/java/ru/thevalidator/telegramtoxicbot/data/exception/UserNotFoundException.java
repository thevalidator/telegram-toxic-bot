package ru.thevalidator.telegramtoxicbot.data.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long telegramId) {
        super("User with id=%d not found".formatted(telegramId));
    }

}
