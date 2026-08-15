package ru.thevalidator.telegramtoxicbot.message.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendChatAction;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import ru.thevalidator.telegramtoxicbot.message.exception.SendBotResponseException;
import ru.thevalidator.telegramtoxicbot.message.service.TelegramResponseSender;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class TelegramResponseSenderImpl implements TelegramResponseSender {

    private final TelegramClient telegramClient;

    @Override
    public Message sendTextResponse(SendMessage botResponse) {
        try {
            return telegramClient.execute(botResponse);
        } catch (TelegramApiException e) {
            throw new SendBotResponseException(e);
        }
    }

    @Override
    public <T extends Serializable, M extends BotApiMethod<T>> T sendResponse(M botResponse) {
        try {
            return telegramClient.execute(botResponse);
        } catch (TelegramApiException e) {
            throw new SendBotResponseException(e);
        }
    }

    @Override
    public void sendChatAction(SendChatAction action, int seconds) {
        try {
            telegramClient.execute(action);
            TimeUnit.SECONDS.sleep(seconds);
        } catch (TelegramApiException | InterruptedException e) {
            throw new SendBotResponseException(e);
        }
    }

}
