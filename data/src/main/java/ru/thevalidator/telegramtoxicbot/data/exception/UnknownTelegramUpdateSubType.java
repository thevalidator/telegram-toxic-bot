package ru.thevalidator.telegramtoxicbot.data.exception;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.Update;

@Getter
public class UnknownTelegramUpdateSubType extends RuntimeException {

    private static final String MESSAGE = "Not supported telegram update sub type";

    private final Update update;

    public UnknownTelegramUpdateSubType(Update update) {
        super(MESSAGE);
        this.update = update;
    }

}
