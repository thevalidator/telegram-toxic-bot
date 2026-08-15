package ru.thevalidator.telegramtoxicbot.message.service.impl.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendChatAction;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdate;
import ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdateSubType;
import ru.thevalidator.telegramtoxicbot.data.service.ReplyToBotService;
import ru.thevalidator.telegramtoxicbot.message.service.TelegramResponseSender;
import ru.thevalidator.telegramtoxicbot.message.service.TelegramUpdateHandler;

import static org.telegram.telegrambots.meta.api.methods.ActionType.TYPING;
import static ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdateSubType.TEXT_REPLY_TO_BOT;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReplyToBotHandler implements TelegramUpdateHandler {

    private static final String HANDLER_NAME = "reply-to-bot-handler";

    private final TelegramResponseSender responseSender;

    private final ReplyToBotService replyToBotService;

    @Override
    public String getName() {
        return HANDLER_NAME;
    }

    @Override
    public boolean canHandle(TelegramUpdate telegramUpdate) {
        TelegramUpdateSubType subType = telegramUpdate.metaData().subType();
        return subType.equals(TEXT_REPLY_TO_BOT);
    }

    @Override
    public void handle(TelegramUpdate telegramUpdate) {

        Long chatId = telegramUpdate.metaData().chatId();
        Integer messageId = telegramUpdate.update().getMessage().getMessageId();
        String responseText = replyToBotService.getNextPhrase();
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
