package ru.thevalidator.telegramtoxicbot.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.thevalidator.telegramtoxicbot.config.TelegramToxicBotConfig;
import ru.thevalidator.telegramtoxicbot.data.config.FileNameProperties;
import ru.thevalidator.telegramtoxicbot.message.config.NotificationConfig;

@SpringBootApplication
@EnableConfigurationProperties({TelegramToxicBotConfig.class, FileNameProperties.class, NotificationConfig.class})
@ComponentScan(basePackages = {
        "ru.thevalidator.telegramtoxicbot.api",
        "ru.thevalidator.telegramtoxicbot.app",
        "ru.thevalidator.telegramtoxicbot.aspect",
        "ru.thevalidator.telegramtoxicbot.config",
        "ru.thevalidator.telegramtoxicbot.data",
        "ru.thevalidator.telegramtoxicbot.message"
})
@EnableJpaRepositories(basePackages = "ru.thevalidator.telegramtoxicbot.data.repository")
@EntityScan("ru.thevalidator.telegramtoxicbot.data.model")
public class TelegramToxicBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(TelegramToxicBotApplication.class, args);
    }

}
