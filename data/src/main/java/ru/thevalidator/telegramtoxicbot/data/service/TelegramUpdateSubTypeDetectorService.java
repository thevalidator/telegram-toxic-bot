package ru.thevalidator.telegramtoxicbot.data.service;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdateSubType;
import ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdateType;

public interface TelegramUpdateSubTypeDetectorService {

    TelegramUpdateSubType detectSubType(TelegramUpdateType type, Update update);

}
