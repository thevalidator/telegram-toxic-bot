package ru.thevalidator.telegramtoxicbot.message.service;

import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendChatAction;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.message.Message;

import java.io.Serializable;

public interface TelegramResponseSender {

    Message sendTextResponse(SendMessage botResponse);

    void sendChatAction(SendChatAction action, int seconds);

    <T extends Serializable, M extends BotApiMethod<T>> T sendResponse(M method);

}
