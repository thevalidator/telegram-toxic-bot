package ru.thevalidator.telegramtoxicbot.data.model;

import org.telegram.telegrambots.meta.api.objects.User;

import java.time.Instant;
import java.util.Optional;

public record UpdateMetaData(TelegramUpdateType type,
                             TelegramUpdateSubType subType,
                             Long userId,
                             Long chatId,
                             User user,
                             Optional<Instant> createdAt) {

}
