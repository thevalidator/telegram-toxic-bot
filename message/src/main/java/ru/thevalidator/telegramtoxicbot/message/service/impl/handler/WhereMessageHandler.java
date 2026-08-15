package ru.thevalidator.telegramtoxicbot.message.service.impl.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendChatAction;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdate;
import ru.thevalidator.telegramtoxicbot.data.service.WhereReplyService;
import ru.thevalidator.telegramtoxicbot.message.service.TelegramResponseSender;
import ru.thevalidator.telegramtoxicbot.message.service.TelegramUpdateHandler;

import static org.telegram.telegrambots.meta.api.methods.ActionType.TYPING;
import static ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdateSubType.WHERE_MESSAGE;

@Slf4j
@Service
@RequiredArgsConstructor
public class WhereMessageHandler implements TelegramUpdateHandler {

    private static final String HANDLER_NAME = "where-message-handler";

    private final TelegramResponseSender responseSender;

    private final WhereReplyService whereReplyService;

    @Override
    public String getName() {
        return HANDLER_NAME;
    }

    @Override
    public boolean canHandle(TelegramUpdate telegramUpdate) {
        return telegramUpdate.metaData().subType().equals(WHERE_MESSAGE);
    }

    @Override
    public void handle(TelegramUpdate telegramUpdate) {
        Long chatId = telegramUpdate.metaData().chatId();
        Integer messageId = telegramUpdate.update().getMessage().getMessageId();
        String responseText = whereReplyService.getNextPhrase();
        log.debug("Response text: {}", responseText);
        SendChatAction action = SendChatAction.builder()
                .chatId(chatId)
                .action(TYPING.toString())
                .build();
        responseSender.sendChatAction(action, 1);
        SendMessage response = SendMessage.builder()
                .chatId(chatId)
                .replyToMessageId(messageId)
                .text(responseText)
                .build();
        responseSender.sendTextResponse(response);
    }

}
