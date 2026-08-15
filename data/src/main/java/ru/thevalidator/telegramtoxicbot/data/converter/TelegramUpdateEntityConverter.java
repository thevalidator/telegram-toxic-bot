//package ru.thevalidator.cyberdictatorbot.data.converter;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import ru.thevalidator.cyberdictatorbot.data.model.TelegramUpdate;
//import ru.thevalidator.cyberdictatorbot.data.model.TelegramUpdateEntity;
//
//import java.time.Instant;
//import java.util.Optional;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class TelegramUpdateEntityConverter {
//
//    private final DateTimeConverter dateTimeConverter;
//
//    public TelegramUpdateEntity toTelegramUpdateEntity(TelegramUpdate telegramUpdate) {
//        Pair pair = getIdDatePair(telegramUpdate.update());
//        Instant messageCreatedAt = pair.date().map(dateTimeConverter::toInstant).orElse(null);
//        TelegramUpdateEntity entity = new TelegramUpdateEntity();
//        entity.setType(entity.getType());
//        entity.setSubType(entity.getSubType());
//        entity.setUpdateData(telegramUpdate.update());
//        entity.setTelegramId(pair.telegramId());
//        entity.setCreatedAt(messageCreatedAt);
//        entity.setTimestamp(Instant.now());
//        return entity;
//    }
//
//    public Pair getIdDatePair(Update update) {
//        if (update.hasMessage()) {
//            var obj = update.getMessage();
//            return new Pair(obj.getFrom().getId(), Optional.of(obj.getDate()));
//        } else if (update.hasEditedMessage()) {
//            var obj = update.getEditedMessage();
//            return new Pair(obj.getFrom().getId(), Optional.of(obj.getDate()));
//        } else if (update.hasMyChatMember()) {
//            var obj = update.getMyChatMember();
//            return new Pair(obj.getFrom().getId(), Optional.of(obj.getDate()));
//        } else if (update.hasChatMember()) {
//            var obj = update.getChatMember();
//            return new Pair(obj.getFrom().getId(), Optional.of(obj.getDate()));
//        } else if (update.hasChatJoinRequest()) {
//            var obj = update.getChatJoinRequest();
//            return new Pair(obj.getUser().getId(), Optional.of(obj.getDate()));
//        } else if (update.hasChannelPost()) {
//            var obj = update.getChannelPost();
//            return new Pair(obj.getFrom().getId(), Optional.of(obj.getDate()));
//        } else if (update.hasEditedChannelPost()) {
//            var obj = update.getEditedChannelPost();
//            return new Pair(obj.getFrom().getId(), Optional.of(obj.getDate()));
//        } else if (update.hasInlineQuery()) {
//            var obj = update.getInlineQuery();
//            return new Pair(obj.getFrom().getId(), Optional.empty());
//        } else if (update.hasChosenInlineQuery()) {
//            var obj = update.getChosenInlineQuery();
//            return new Pair(obj.getFrom().getId(), Optional.empty());
//        } else if (update.hasCallbackQuery()) {
//            var obj = update.getCallbackQuery();
//            return new Pair(obj.getFrom().getId(), Optional.empty());
//        } else if (update.hasPaidMediaPurchased()) {
//            var obj = update.getPaidMediaPurchased();
//            return new Pair(obj.getUser().getId(), Optional.empty());
//        } else if (update.hasShippingQuery()) {
//            var obj = update.getShippingQuery();
//            return new Pair(obj.getFrom().getId(), Optional.empty());
//        } else if (update.hasPreCheckoutQuery()) {
//            var obj = update.getPreCheckoutQuery();
//            return new Pair(obj.getFrom().getId(), Optional.empty());
//        } else if (update.hasPollAnswer()) {
//            var obj = update.getPollAnswer();
//            return new Pair(obj.getUser().getId(), Optional.empty());
//        } else if (update.hasBusinessMessage()) {
//            var obj = update.getBusinessMessage();
//            return new Pair(obj.getFrom().getId(), Optional.of(obj.getDate()));
//        } else if (update.hasEditedBusinessMessage()) {
//            var obj = update.getEditedBuinessMessage();
//            return new Pair(obj.getFrom().getId(), Optional.of(obj.getDate()));
//        } else if (update.hasBusinessConnection()) {
//            var obj = update.getBusinessConnection();
//            return new Pair(obj.getUser().getId(), Optional.of(obj.getDate()));
/// /        } else if (update.hasPoll()) {
/// /            var obj = update.getPoll();
/// /            return new Pair(obj.get().getId(), Optional.of(obj.getDate()));
/// /        } else if (update.hasDeletedBusinessMessage()) {
/// /            var obj = update.getDeletedBusinessMessages();
/// /            return new Pair(obj.getChat().getId(), Optional.empty());
//        } else {
//            return new Pair(null, Optional.empty());
//        }
//    }
//
//}
//
//record Pair(Long telegramId, Optional<Integer> date) {
//
//}
