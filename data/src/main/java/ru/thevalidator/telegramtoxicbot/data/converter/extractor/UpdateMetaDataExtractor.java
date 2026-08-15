package ru.thevalidator.telegramtoxicbot.data.converter.extractor;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import ru.thevalidator.telegramtoxicbot.data.converter.UpdateDateToInstantConverter;
import ru.thevalidator.telegramtoxicbot.data.exception.UnknownUpdateType;
import ru.thevalidator.telegramtoxicbot.data.exception.UnsupportedUpdateType;
import ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdateSubType;
import ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdateType;
import ru.thevalidator.telegramtoxicbot.data.model.UpdateMetaData;
import ru.thevalidator.telegramtoxicbot.data.service.TelegramUpdateSubTypeDetectorService;

import java.time.Instant;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

@Component
public class UpdateMetaDataExtractor {

    private final Map<UpdateType, Function<Update, UpdateMetaData>> extractors = new EnumMap<>(UpdateType.class);

    private final TelegramUpdateSubTypeDetectorService subTypeDetectorService;

    private final UpdateDateToInstantConverter updateDateToInstantConverter;

    public UpdateMetaDataExtractor(TelegramUpdateSubTypeDetectorService subTypeDetectorService,
                                   UpdateDateToInstantConverter updateDateToInstantConverter) {
        this.subTypeDetectorService = subTypeDetectorService;
        this.updateDateToInstantConverter = updateDateToInstantConverter;
        initExtractors();
    }

    private void initExtractors() {
        extractors.put(UpdateType.MESSAGE, this::extractFromMessage);
        extractors.put(UpdateType.EDITED_MESSAGE, this::extractFromEditedMessage);
        extractors.put(UpdateType.MY_CHAT_MEMBER, this::extractFromMyChatMember);
        extractors.put(UpdateType.CHAT_MEMBER, this::extractFromChatMember);
        extractors.put(UpdateType.CHAT_JOIN_REQUEST, this::extractFromChatJoinRequest);
        extractors.put(UpdateType.CALLBACK_QUERY, this::extractFromCallbackQuery);
        extractors.put(UpdateType.INLINE_QUERY, this::extractFromInlineQuery);
        extractors.put(UpdateType.CHOSEN_INLINE_QUERY, this::extractFromChosenInlineQuery);
        extractors.put(UpdateType.POLL, this::extractFromPoll);
        extractors.put(UpdateType.POLL_ANSWER, this::extractFromPollAnswer);
        extractors.put(UpdateType.CHANNEL_POST, this::extractFromChannelPost);
        extractors.put(UpdateType.EDITED_CHANNEL_POST, this::extractFromEditedChannelPost);
        extractors.put(UpdateType.BUSINESS_CONNECTION, this::extractFromBusinessConnection);
        extractors.put(UpdateType.BUSINESS_MESSAGE, this::extractFromBusinessMessage);
        extractors.put(UpdateType.EDITED_BUSINESS_MESSAGE, this::extractFromEditedBusinessMessage);
        extractors.put(UpdateType.DELETED_BUSINESS_MESSAGE, this::extractFromDeletedBusinessMessage);
        extractors.put(UpdateType.PRE_CHECKOUT_QUERY, this::extractFromPreCheckoutQuery);
        extractors.put(UpdateType.PAID_MEDIA_PURCHASED, this::extractFromPaidMediaPurchased);
        extractors.put(UpdateType.SHIPPING_QUERY, this::extractFromShippingQuery);
    }

    public UpdateMetaData extractMetaData(Update update) {
        return UpdateType.forUpdate(update)
                .map(type -> extractors.get(type).apply(update))
                .orElseThrow(() -> new UnknownUpdateType(update));
    }

    private UpdateMetaData extractFromMessage(Update update) {
        TelegramUpdateType type = TelegramUpdateType.MESSAGE;
        TelegramUpdateSubType subType = subTypeDetectorService.detectSubType(type, update);
        Message message = update.getMessage();
        User user = message.getFrom();
        Long userId = user.getId();
        Long chatId = message.getChatId();
        Optional<Instant> createdAt = getCreatedAt(message.getDate());
        return new UpdateMetaData(type, subType, userId, chatId, user, createdAt);
    }

    private Optional<Instant> getCreatedAt(Integer date) {
        if (Objects.isNull(date)) {
            return Optional.empty();
        }
        return Optional.of(updateDateToInstantConverter.toInstant(date));
    }

    private UpdateMetaData extractFromEditedMessage(Update update) {
        TelegramUpdateType type = TelegramUpdateType.EDITED_MESSAGE;
        TelegramUpdateSubType subType = subTypeDetectorService.detectSubType(type, update);
        Message message = update.getEditedMessage();
        User user = message.getFrom();
        Long userId = user.getId();
        Long chatId = message.getChatId();
        Optional<Instant> createdAt = getCreatedAt(message.getDate());
        return new UpdateMetaData(type, subType, userId, chatId, user, createdAt);
    }

    private UpdateMetaData extractFromMyChatMember(Update update) {
        throw new UnsupportedUpdateType("Not supported yet");
    }

    private UpdateMetaData extractFromChatMember(Update update) {
        throw new UnsupportedUpdateType("Not supported yet");
    }

    private UpdateMetaData extractFromChatJoinRequest(Update update) {
        throw new UnsupportedUpdateType("Not supported yet");
    }

    private UpdateMetaData extractFromChannelPost(Update update) {
        throw new UnsupportedUpdateType("Not supported yet");
    }

    private UpdateMetaData extractFromEditedChannelPost(Update update) {
        throw new UnsupportedUpdateType("Not supported yet");
    }

    private UpdateMetaData extractFromInlineQuery(Update update) {
        throw new UnsupportedUpdateType("Not supported yet");
    }

    private UpdateMetaData extractFromChosenInlineQuery(Update update) {
        throw new UnsupportedUpdateType("Not supported yet");
    }

    private UpdateMetaData extractFromCallbackQuery(Update update) {
        throw new UnsupportedUpdateType("Not supported yet");
    }

    private UpdateMetaData extractFromPaidMediaPurchased(Update update) {
        throw new UnsupportedUpdateType("Not supported yet");
    }

    private UpdateMetaData extractFromShippingQuery(Update update) {
        throw new UnsupportedUpdateType("Not supported yet");
    }

    private UpdateMetaData extractFromPreCheckoutQuery(Update update) {
        throw new UnsupportedUpdateType("Not supported yet");
    }

    private UpdateMetaData extractFromPollAnswer(Update update) {
        throw new UnsupportedUpdateType("Not supported yet");
    }

    private UpdateMetaData extractFromBusinessMessage(Update update) {
        throw new UnsupportedUpdateType("Not supported yet");
    }

    private UpdateMetaData extractFromEditedBusinessMessage(Update update) {
        throw new UnsupportedUpdateType("Not supported yet");
    }

    private UpdateMetaData extractFromBusinessConnection(Update update) {
        throw new UnsupportedUpdateType("Not supported yet");
    }

    private UpdateMetaData extractFromDeletedBusinessMessage(Update update) {
        throw new UnsupportedUpdateType("Not supported yet");
    }

    private UpdateMetaData extractFromPoll(Update update) {
        throw new UnsupportedUpdateType("Not supported yet");
    }

}

