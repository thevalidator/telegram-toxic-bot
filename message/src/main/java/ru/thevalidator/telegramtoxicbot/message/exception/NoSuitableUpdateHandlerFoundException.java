package ru.thevalidator.telegramtoxicbot.message.exception;

import ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdate;

public class NoSuitableUpdateHandlerFoundException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "No suitable handler found for type `%s` and subType `%s`";

    public NoSuitableUpdateHandlerFoundException(TelegramUpdate update) {
        this(DEFAULT_MESSAGE.formatted(update.metaData().type(), update.metaData().subType()));
    }

    public NoSuitableUpdateHandlerFoundException(String message) {
        super(message);
    }

}
