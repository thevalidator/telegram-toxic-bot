package ru.thevalidator.telegramtoxicbot.message.service;

import ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdate;

public interface TelegramUpdateDispatcher {

    void dispatch(TelegramUpdate incomingUpdate);

}
