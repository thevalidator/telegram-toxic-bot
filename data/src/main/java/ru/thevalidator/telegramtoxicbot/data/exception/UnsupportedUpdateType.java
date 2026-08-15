package ru.thevalidator.telegramtoxicbot.data.exception;

public class UnsupportedUpdateType extends RuntimeException {

    public UnsupportedUpdateType(String message) {
        super(message);
    }

}
