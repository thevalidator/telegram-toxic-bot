package ru.thevalidator.telegramtoxicbot.message.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.thevalidator.telegramtoxicbot.aspect.TimeLog;
import ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdate;
import ru.thevalidator.telegramtoxicbot.data.service.TelegramUserService;
import ru.thevalidator.telegramtoxicbot.message.exception.NoSuitableUpdateHandlerFoundException;
import ru.thevalidator.telegramtoxicbot.message.service.TelegramUpdateDispatcher;
import ru.thevalidator.telegramtoxicbot.message.service.TelegramUpdateHandler;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramUpdateDispatcherImpl implements TelegramUpdateDispatcher {

    private final List<TelegramUpdateHandler> handlers;

    private final TelegramUserService userService;

    @TimeLog
    @Override
    public void dispatch(TelegramUpdate incomingUpdate) {
        saveUser(incomingUpdate);
        List<String> appliedHandlers = new ArrayList<>();
        for (TelegramUpdateHandler handler: handlers) {
            boolean isHandled = tryToHandle(incomingUpdate, handler);
            if (isHandled) {
                appliedHandlers.add(handler.getName());
            }
        }
        log.debug("Handlers applied: {}", appliedHandlers);
        if (appliedHandlers.isEmpty()) {
            log.warn("No handlers found for type {} with subtype {}",
                    incomingUpdate.metaData().type(), incomingUpdate.metaData().subType());
//            throwException(incomingUpdate);
        }
    }

    private void saveUser(TelegramUpdate incomingUpdate) {
        User user = incomingUpdate.metaData().user();
        if (nonNull(user)) {
            userService.saveOrUpdate(user);
        }
    }

    private boolean tryToHandle(TelegramUpdate incomingUpdate, TelegramUpdateHandler handler) {
        if (handler.canHandle(incomingUpdate)) {
            try {
                handler.handle(incomingUpdate);
                return true;
            } catch (Exception e) {
                log.error("Error handling update with handler '{}': {}", handler.getName(), e.getMessage(), e);
            }
        }
        return false;
    }

    private static void throwException(TelegramUpdate incomingUpdate) {
        log.error("No handler for update:\n{}", incomingUpdate.update());
        throw new NoSuitableUpdateHandlerFoundException(incomingUpdate);
    }

}
