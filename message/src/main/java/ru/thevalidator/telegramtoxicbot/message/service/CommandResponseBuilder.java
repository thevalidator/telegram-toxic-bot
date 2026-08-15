package ru.thevalidator.telegramtoxicbot.message.service;

import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import ru.thevalidator.telegramtoxicbot.config.TelegramToxicBotCommand;
import ru.thevalidator.telegramtoxicbot.data.converter.JsonConverter;
import ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdate;
import ru.thevalidator.telegramtoxicbot.message.converter.DateTimeConverter;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static ru.thevalidator.telegramtoxicbot.message.util.DateTimeFormatterUtil.BUILD_FORMATTER;
import static ru.thevalidator.telegramtoxicbot.message.util.TelegramMessageEntityType.CODE_BLOCK;

@Service
public class CommandResponseBuilder {

    private final JsonConverter jsonConverter;

    private final DateTimeConverter dateTimeConverter;

    private final BuildProperties buildProperties;

    private final Map<TelegramToxicBotCommand, Function<TelegramUpdate, SendMessage>> commandHandlers;

    public CommandResponseBuilder(JsonConverter jsonConverter,
                                  DateTimeConverter dateTimeConverter,
                                  BuildProperties buildProperties) {
        this.commandHandlers = Map.of(
                TelegramToxicBotCommand.INFO, this::infoResponse,
                TelegramToxicBotCommand.START, this::startResponse,
                TelegramToxicBotCommand.ABOUT, this::aboutResponse,
                TelegramToxicBotCommand.COMMAND_LIST, this::commandListResponse,
                TelegramToxicBotCommand.ID, this::idResponse,
                TelegramToxicBotCommand.MY_INFO, this::myInfoResponse,
                TelegramToxicBotCommand.VOICE, this::voiceResponse
        );
        this.jsonConverter = jsonConverter;
        this.dateTimeConverter = dateTimeConverter;
        this.buildProperties = buildProperties;
    }

    public SendMessage getCmdResponse(TelegramToxicBotCommand cmd, TelegramUpdate update) {
        return commandHandlers.get(cmd).apply(update);
    }

    private SendMessage infoResponse(TelegramUpdate update) {
        return aboutResponse(update);
    }

    private SendMessage startResponse(TelegramUpdate update) {
        String name = update.update().getMessage().getFrom().getFirstName();
        return SendMessage.builder()
                .chatId(update.metaData().chatId())
                .text("Бонжорно, %s".formatted(name))
                .build();
    }

    private SendMessage aboutResponse(TelegramUpdate update) {
        LocalDateTime buildLocalDateTime = dateTimeConverter.getLocalDateTime(buildProperties.getTime());
        String buildTime = buildLocalDateTime.format(BUILD_FORMATTER);
        String appVer = buildProperties.getVersion();
        String text = "Telegram Toxic Bot%nVer: %s%nBuild: %s".formatted(appVer, buildTime);
        return SendMessage.builder()
                .chatId(update.metaData().chatId())
                .text(text)
                .build();
    }

    private SendMessage commandListResponse(TelegramUpdate update) {
        String list = Arrays.stream(TelegramToxicBotCommand.values()).toList().toString();
        return SendMessage.builder()
                .chatId(update.metaData().chatId())
                .text(list)
                .build();
    }

    private SendMessage idResponse(TelegramUpdate update) {
        return SendMessage.builder()
                .chatId(update.metaData().chatId())
                .text("Telegram ID: %d".formatted(update.metaData().userId()))
                .build();
    }

    private SendMessage myInfoResponse(TelegramUpdate update) {
        String responseText = jsonConverter.objectToPrettyJson(update.update().getMessage().getFrom());
        MessageEntity codeEntity = new MessageEntity(CODE_BLOCK.textValue(), 0, responseText.length());
        return SendMessage.builder()
                .chatId(update.metaData().chatId())
                .text(responseText)
                .entities(List.of(codeEntity))
                .build();
    }

    private SendMessage voiceResponse(TelegramUpdate update) {
        return SendMessage.builder()
                .chatId(update.metaData().chatId())
                .text("Гав-гав")
                .build();
    }

}
