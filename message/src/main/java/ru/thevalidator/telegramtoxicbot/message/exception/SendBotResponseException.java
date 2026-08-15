package ru.thevalidator.telegramtoxicbot.message.exception;

public class SendBotResponseException extends RuntimeException {

    public SendBotResponseException(Throwable cause) {
        super("Sending bot response failed", cause);
    }

}
