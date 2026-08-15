//package ru.thevalidator.cyberdictatorbot.message.service.impl.handler;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import ru.thevalidator.cyberdictatorbot.data.converter.JsonConverter;
//import ru.thevalidator.cyberdictatorbot.data.model.TelegramUpdate;
//import ru.thevalidator.cyberdictatorbot.message.service.TelegramUpdateHandler;
//
//import static java.util.Objects.nonNull;
//
//@Service
//@RequiredArgsConstructor
//public class SaveTelegramUpdateHandler implements TelegramUpdateHandler {
//
//    private static final String HANDLER_NAME = "save-update-handler";
//
//    private final JsonConverter jsonConverter;
//
//    @Override
//    public String getName() {
//        return HANDLER_NAME;
//    }
//
//    @Override
//    public boolean canHandle(TelegramUpdate telegramUpdate) {
//        return nonNull(telegramUpdate);
//    }
//
//    @Override
//    public void handle(TelegramUpdate telegramUpdate) {
//        System.out.println(jsonConverter.objectToPrettyJson(telegramUpdate));
/// /        throw new UnsupportedOperationException("Not supported yet");
//    }
//
//}
