package ru.thevalidator.telegramtoxicbot.data.service.impl;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.thevalidator.telegramtoxicbot.data.service.TelegramUserHashComputeService;

import java.util.Objects;

@Service
public class UserEntityHashComputeServiceImpl implements TelegramUserHashComputeService {

    @Override
    public int computeHash(User telegramUser) {
        return Objects.hash(
                telegramUser.getId(),
                telegramUser.getIsBot(),
                telegramUser.getIsPremium(),
                telegramUser.getFirstName(),
                telegramUser.getLastName(),
                telegramUser.getUserName()
        );
    }

}
