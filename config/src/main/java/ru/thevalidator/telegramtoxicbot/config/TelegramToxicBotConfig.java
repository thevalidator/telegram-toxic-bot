package ru.thevalidator.telegramtoxicbot.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("bot")
@Setter
@Getter
public class TelegramToxicBotConfig {

    private String apiKey;

    private Long botId;

    private String botName;

}
