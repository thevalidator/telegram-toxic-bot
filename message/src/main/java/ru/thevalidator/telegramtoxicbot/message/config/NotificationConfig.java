package ru.thevalidator.telegramtoxicbot.message.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties("notification")
@Setter
@Getter
public class NotificationConfig {

    private List<Long> users;

}
