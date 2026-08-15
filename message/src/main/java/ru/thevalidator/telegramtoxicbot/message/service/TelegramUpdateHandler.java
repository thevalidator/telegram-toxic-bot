package ru.thevalidator.telegramtoxicbot.message.service;

import ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdate;

public interface TelegramUpdateHandler {

    String getName();

    boolean canHandle(TelegramUpdate telegramUpdate);

    void handle(TelegramUpdate telegramUpdate);

}
