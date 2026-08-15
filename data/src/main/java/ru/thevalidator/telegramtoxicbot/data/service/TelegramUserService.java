package ru.thevalidator.telegramtoxicbot.data.service;

import org.telegram.telegrambots.meta.api.objects.User;
import ru.thevalidator.telegramtoxicbot.data.model.TelegramUserEntity;
import ru.thevalidator.telegramtoxicbot.data.model.UserState;

public interface TelegramUserService {

    TelegramUserEntity getUserByTelegramId(Long telegramId);

    UserState saveOrUpdate(User telegramUser);

}
