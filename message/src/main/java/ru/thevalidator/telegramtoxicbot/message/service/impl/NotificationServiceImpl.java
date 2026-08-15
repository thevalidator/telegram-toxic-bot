package ru.thevalidator.telegramtoxicbot.message.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.thevalidator.telegramtoxicbot.message.config.NotificationConfig;
import ru.thevalidator.telegramtoxicbot.message.service.NotificationService;
import ru.thevalidator.telegramtoxicbot.message.service.TelegramResponseSender;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final TelegramResponseSender responseSender;

    private final NotificationConfig notificationConfig;

    @Override
    public void sendNotification(String message) {
        for (Long userId: notificationConfig.getUsers()) {
            SendMessage msg = SendMessage.builder()
                    .chatId(userId)
                    .text(message)
                    .build();
            responseSender.sendTextResponse(msg);
        }
    }

}
