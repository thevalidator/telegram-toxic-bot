package ru.thevalidator.telegramtoxicbot.app.bot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.thevalidator.telegramtoxicbot.config.TelegramToxicBotConfig;
import ru.thevalidator.telegramtoxicbot.data.converter.TelegramUpdateConverter;
import ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdate;
import ru.thevalidator.telegramtoxicbot.message.service.TelegramUpdateDispatcher;

@Component
@RequiredArgsConstructor
public class TelegramToxicBot implements SpringLongPollingBot, LongPollingSingleThreadUpdateConsumer {

    private final TelegramToxicBotConfig botConfig;

    private final TelegramUpdateDispatcher updateDispatcher;

    private final TelegramUpdateConverter updateConverter;

    @Override
    public String getBotToken() {
        return botConfig.getApiKey();
    }

    @Override
    public LongPollingUpdateConsumer getUpdatesConsumer() {
        return this;
    }

    @Override
    public void consume(Update update) {
        TelegramUpdate telegramUpdate = updateConverter.toTelegramUpdate(update);
        updateDispatcher.dispatch(telegramUpdate);
    }

}
