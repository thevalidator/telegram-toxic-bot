package ru.thevalidator.telegramtoxicbot.config;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum TelegramToxicBotCommand {

    START("/start"),
    INFO("/info"),
    ABOUT("/about"),
    COMMAND_LIST("/commands"),
    ID("/id"),
    MY_INFO("/myinfo"),
    VOICE("/voice");

    private final String textValue;

    TelegramToxicBotCommand(String textValue) {
        this.textValue = textValue;
    }

    public static Optional<TelegramToxicBotCommand> fromTextValue(String textCommand) {
        return Arrays.stream(TelegramToxicBotCommand.values())
                .filter(cmd -> cmd.getTextValue().equals(textCommand))
                .findFirst();
    }

}
