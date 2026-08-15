package ru.thevalidator.telegramtoxicbot.data.service;

import org.telegram.telegrambots.meta.api.objects.User;

public interface TelegramUserHashComputeService {

    int computeHash(User telegramUser);

}
