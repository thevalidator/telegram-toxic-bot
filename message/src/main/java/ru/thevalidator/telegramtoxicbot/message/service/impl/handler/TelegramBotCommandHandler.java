package ru.thevalidator.telegramtoxicbot.message.service.impl.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import ru.thevalidator.telegramtoxicbot.config.TelegramToxicBotCommand;
import ru.thevalidator.telegramtoxicbot.config.TelegramToxicBotConfig;
import ru.thevalidator.telegramtoxicbot.data.model.TelegramEntityType;
import ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdate;
import ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdateSubType;
import ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdateType;
import ru.thevalidator.telegramtoxicbot.message.service.CommandResponseBuilder;
import ru.thevalidator.telegramtoxicbot.message.service.TelegramResponseSender;
import ru.thevalidator.telegramtoxicbot.message.service.TelegramUpdateHandler;

import java.util.List;
import java.util.Optional;

import static ru.thevalidator.telegramtoxicbot.config.TelegramToxicBotCommand.fromTextValue;

@Service
@RequiredArgsConstructor
public class TelegramBotCommandHandler implements TelegramUpdateHandler {

    private static final String HANDLER_NAME = "bot-command-handler";

    private final TelegramToxicBotConfig botConfig;

    private final CommandResponseBuilder responseBuilder;

    private final TelegramResponseSender responseSender;


    @Override
    public String getName() {
        return HANDLER_NAME;
    }

    @Override
    public boolean canHandle(TelegramUpdate telegramUpdate) {
        return telegramUpdate.metaData().type().equals(TelegramUpdateType.MESSAGE)
                && telegramUpdate.metaData().subType().equals(TelegramUpdateSubType.COMMAND);
    }

    @Override
    public void handle(TelegramUpdate telegramUpdate) {
        Optional<SendMessage> response = getResponse(telegramUpdate);
        response.ifPresent(responseSender::sendResponse);
    }

    private Optional<SendMessage> getResponse(TelegramUpdate update) {
        String receivedTextCommand = getCommand(update);
        final String receivedCommandLowerCase = receivedTextCommand.toLowerCase();
        Optional<TelegramToxicBotCommand> commandOptional = fromTextValue(receivedCommandLowerCase);
        return commandOptional.map(cmd -> responseBuilder.getCmdResponse(cmd, update));
    }

    private String getCommand(TelegramUpdate update) {
        List<MessageEntity> entities = update.update().getMessage().getEntities();
        for (MessageEntity e: entities) {
            if (e.getType().equals(TelegramEntityType.BOT_COMMAND)) {
                String cmdText = e.getText();
                return cmdText.replace('@' + botConfig.getBotName(), "");   //clean bot name tag in chats
            }
        }
        throw new IllegalArgumentException("Bad telegram update");
    }

}
