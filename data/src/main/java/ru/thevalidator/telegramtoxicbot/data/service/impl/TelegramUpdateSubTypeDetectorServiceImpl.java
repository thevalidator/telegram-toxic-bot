package ru.thevalidator.telegramtoxicbot.data.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import ru.thevalidator.telegramtoxicbot.config.TelegramToxicBotConfig;
import ru.thevalidator.telegramtoxicbot.data.model.TelegramEntityType;
import ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdateSubType;
import ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdateType;
import ru.thevalidator.telegramtoxicbot.data.service.TelegramUpdateSubTypeDetectorService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.isNull;
import static ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdateSubType.AAH_MESSAGE;
import static ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdateSubType.BOT_MENTION;
import static ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdateSubType.COMMAND;
import static ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdateSubType.COMMON;
import static ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdateSubType.MENTION;
import static ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdateSubType.NOT_A_MESSAGE;
import static ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdateSubType.NO_MESSAGE;
import static ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdateSubType.PHOTO_MESSAGE;
import static ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdateSubType.PHOTO_MESSAGE_REPLY;
import static ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdateSubType.PHOTO_MESSAGE_REPLY_TO_BOT;
import static ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdateSubType.SHORT_NO_MESSAGE;
import static ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdateSubType.SOFT_NO_MESSAGE;
import static ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdateSubType.TEXT_REPLY;
import static ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdateSubType.TEXT_REPLY_TO_BOT;
import static ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdateSubType.THREE_HUNDRED_MESSAGE;
import static ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdateSubType.VOICE_MESSAGE;
import static ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdateSubType.VOICE_MESSAGE_REPLY;
import static ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdateSubType.VOICE_MESSAGE_REPLY_TO_BOT;
import static ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdateSubType.WHERE_MESSAGE;
import static ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdateSubType.YES_MESSAGE;
import static ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdateType.EDITED_MESSAGE;
import static ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdateType.MESSAGE;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramUpdateSubTypeDetectorServiceImpl implements TelegramUpdateSubTypeDetectorService {

    private final TelegramToxicBotConfig botConfig;

    @Override
    public TelegramUpdateSubType detectSubType(TelegramUpdateType type, Update update) {
        TelegramUpdateSubType subType;
        if (type.equals(MESSAGE) && update.getMessage().hasText()) {
            subType = detectTextMessageSubType(update);
        } else if (type.equals(EDITED_MESSAGE) && update.getEditedMessage().hasText()) {
            subType = detectTextMessageSubType(update);
        } else if (type.equals(MESSAGE) && update.getMessage().hasVoice()) {
            subType = detectVoiceMessageSubType(update);
        } else if (type.equals(MESSAGE) && update.getMessage().hasPhoto()) {
            subType = detectPhotoMessageSubType(update);
        } else {
            subType = COMMON;
        }
        log.debug("Subtype detected: {}", subType);
        return subType;
    }

    private TelegramUpdateSubType detectPhotoMessageSubType(Update update) {
        Message message = update.getMessage();
        if (message.isReply()) {
            return isReplyToBot(message) ? PHOTO_MESSAGE_REPLY_TO_BOT : PHOTO_MESSAGE_REPLY;
        }
        return PHOTO_MESSAGE;
    }

    private TelegramUpdateSubType detectVoiceMessageSubType(Update update) {
        Message message = update.getMessage();
        if (message.isReply()) {
            return isReplyToBot(message) ? VOICE_MESSAGE_REPLY_TO_BOT : VOICE_MESSAGE_REPLY;
        }
        return VOICE_MESSAGE;
    }

    private TelegramUpdateSubType detectTextMessageSubType(Update update) {
        Optional<TelegramUpdateSubType> optionalSubType = detectRhymeMessageType(update);
        return optionalSubType.orElseGet(() -> getSubType(update));
    }

    private Optional<TelegramUpdateSubType> detectRhymeMessageType(Update update) {
        String originalText = update.getMessage().getText();
        String text = originalText.replaceAll("([^a-zA-Zа-яА-Я]*)", "").toLowerCase();
        log.debug("Cleared message text: {}", text);
        if (text.matches("^[нhn][нетethnьъ]+$")) {
            char last = text.charAt(text.length() - 1);
            return switch (last) {
                case 'e', 'е' -> Optional.of(SHORT_NO_MESSAGE);
                case 'ь' -> Optional.of(SOFT_NO_MESSAGE);
                default -> Optional.of(NO_MESSAGE);
            };
        } else if (text.matches("^т[риста]+$") ||
                originalText.replaceAll("([^a-zA-Zа-яА-Я0-9]*)", "").toLowerCase().matches("^[3з][0оo]{2}$")) {
            return Optional.of(THREE_HUNDRED_MESSAGE);
        } else if (text.matches("^[aа]+$")) {
            return Optional.of(AAH_MESSAGE);
        } else if (text.matches("^[дd][aа]+$")) {
            return Optional.of(YES_MESSAGE);
        } else if (text.matches("^[gг][деde]+$")) {
            return Optional.of(WHERE_MESSAGE);
        } else {
            return Optional.empty();
        }
    }

    private TelegramUpdateSubType getSubType(Update update) {
        Message message = update.getMessage();
        if (isNull(message)) {
            return NOT_A_MESSAGE;
        }
        List<MessageEntity> messageEntities = message.getEntities();
        if (Objects.nonNull(messageEntities)) {
            for (MessageEntity e: messageEntities) {
                Optional<TelegramUpdateSubType> opt = detectSubTypeByMessageEntity(e);
                if (opt.isPresent()) {
                    return opt.get();
                }
            }
        }
        if (message.isReply()) {
            return isReplyToBot(message) ? TEXT_REPLY_TO_BOT : TEXT_REPLY;
        }
        return COMMON;
    }

    private boolean isReplyToBot(Message message) {
        return Objects.equals(message.getReplyToMessage().getFrom().getId(), botConfig.getBotId());
    }

    private Optional<TelegramUpdateSubType> detectSubTypeByMessageEntity(MessageEntity e) {
        String type = e.getType();
        if (type.equals(TelegramEntityType.BOT_COMMAND)) {
            String[] cmdData = e.getText().split("@");
            if (cmdData.length == 1 || (cmdData.length > 1 && cmdData[1].equals(botConfig.getBotName()))) {
                return Optional.of(COMMAND);
            }
        }
        if (type.equals(TelegramEntityType.MENTION) || type.equals(TelegramEntityType.TEXT_MENTION)) {
            String name = e.getText().substring(1); //clear '@' before mention text
            log.debug("Mention name: {}, bot name: {}", name, botConfig.getBotName());
            TelegramUpdateSubType subType = name.equals(botConfig.getBotName()) ? BOT_MENTION : MENTION;
            return Optional.of(subType);
        }
        return Optional.empty();
    }

}
