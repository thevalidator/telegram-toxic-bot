package ru.thevalidator.telegramtoxicbot.data.model;

import org.telegram.telegrambots.meta.api.objects.Update;

public record TelegramUpdate(UpdateMetaData metaData, Update update) {

}
