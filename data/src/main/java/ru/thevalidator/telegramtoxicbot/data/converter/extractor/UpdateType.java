package ru.thevalidator.telegramtoxicbot.data.converter.extractor;

import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

enum UpdateType {

    MESSAGE(Update::hasMessage),
    EDITED_MESSAGE(Update::hasEditedMessage),
    MY_CHAT_MEMBER(Update::hasMyChatMember),
    CHAT_MEMBER(Update::hasChatMember),
    CHAT_JOIN_REQUEST(Update::hasChatJoinRequest),
    CALLBACK_QUERY(Update::hasCallbackQuery),
    INLINE_QUERY(Update::hasInlineQuery),
    CHOSEN_INLINE_QUERY(Update::hasChosenInlineQuery),
    POLL(Update::hasPoll),
    POLL_ANSWER(Update::hasPollAnswer),
    CHANNEL_POST(Update::hasChannelPost),
    EDITED_CHANNEL_POST(Update::hasEditedChannelPost),
    BUSINESS_CONNECTION(Update::hasBusinessConnection),
    BUSINESS_MESSAGE(Update::hasBusinessMessage),
    EDITED_BUSINESS_MESSAGE(Update::hasEditedBusinessMessage),
    DELETED_BUSINESS_MESSAGE(Update::hasDeletedBusinessMessage),
    PRE_CHECKOUT_QUERY(Update::hasPreCheckoutQuery),
    PAID_MEDIA_PURCHASED(Update::hasPaidMediaPurchased),
    SHIPPING_QUERY(Update::hasShippingQuery);

//    if (update.hasEditedMessage()) {
//        var obj = update.getEditedMessage();
//        return new Pair(obj.getFrom().getId(), Optional.of(obj.getDate()));
//    } else if (update.hasMyChatMember()) {
//        var obj = update.getMyChatMember();
//        return new Pair(obj.getFrom().getId(), Optional.of(obj.getDate()));
//    } else if (update.hasChatMember()) {
//        var obj = update.getChatMember();
//        return new Pair(obj.getFrom().getId(), Optional.of(obj.getDate()));
//    } else if (update.hasChatJoinRequest()) {
//        var obj = update.getChatJoinRequest();
//        return new Pair(obj.getUser().getId(), Optional.of(obj.getDate()));
//    } else if (update.hasChannelPost()) {
//        var obj = update.getChannelPost();
//        return new Pair(obj.getFrom().getId(), Optional.of(obj.getDate()));
//    } else if (update.hasEditedChannelPost()) {
//        var obj = update.getEditedChannelPost();
//        return new Pair(obj.getFrom().getId(), Optional.of(obj.getDate()));
//    } else if (update.hasInlineQuery()) {
//        var obj = update.getInlineQuery();
//        return new Pair(obj.getFrom().getId(), Optional.empty());
//    } else if (update.hasChosenInlineQuery()) {
//        var obj = update.getChosenInlineQuery();
//        return new Pair(obj.getFrom().getId(), Optional.empty());
//    } else if (update.hasCallbackQuery()) {
//        var obj = update.getCallbackQuery();
//        return new Pair(obj.getFrom().getId(), Optional.empty());
//    } else if (update.hasPaidMediaPurchased()) {
//        var obj = update.getPaidMediaPurchased();
//        return new Pair(obj.getUser().getId(), Optional.empty());
//    } else if (update.hasShippingQuery()) {
//        var obj = update.getShippingQuery();
//        return new Pair(obj.getFrom().getId(), Optional.empty());
//    } else if (update.hasPreCheckoutQuery()) {
//        var obj = update.getPreCheckoutQuery();
//        return new Pair(obj.getFrom().getId(), Optional.empty());
//    } else if (update.hasPollAnswer()) {
//        var obj = update.getPollAnswer();
//        return new Pair(obj.getUser().getId(), Optional.empty());
//    } else if (update.hasBusinessMessage()) {
//        var obj = update.getBusinessMessage();
//        return new Pair(obj.getFrom().getId(), Optional.of(obj.getDate()));
//    } else if (update.hasEditedBusinessMessage()) {
//        var obj = update.getEditedBuinessMessage();
//        return new Pair(obj.getFrom().getId(), Optional.of(obj.getDate()));
//    } else if (update.hasBusinessConnection()) {
//        var obj = update.getBusinessConnection();
//        return new Pair(obj.getUser().getId(), Optional.of(obj.getDate()));
//        } else if (update.hasPoll()) {
//            var obj = update.getPoll();
//            return new Pair(obj.get().getId(), Optional.of(obj.getDate()));
//        } else if (update.hasDeletedBusinessMessage()) {
//            var obj = update.getDeletedBusinessMessages();
//            return new Pair(obj.getChat().getId(), Optional.empty());
//    } else {
//        return new Pair(null, Optional.empty());
//    }

    private final Predicate<Update> detector;

    UpdateType(Predicate<Update> detector) {
        this.detector = detector;
    }

    static Optional<UpdateType> forUpdate(Update update) {
        return Arrays.stream(values())
                .filter(type -> type.detector.test(update))
                .findFirst();
    }
}
