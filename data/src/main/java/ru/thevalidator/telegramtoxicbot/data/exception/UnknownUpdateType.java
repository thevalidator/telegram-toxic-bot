package ru.thevalidator.telegramtoxicbot.data.exception;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.Update;

@Getter
public class UnknownUpdateType extends RuntimeException {

    private static final String MESSAGE = "Not supported telegram update type";

    private final Update update;

    public UnknownUpdateType(Update update) {
        super(MESSAGE);
        this.update = update;
    }

}
