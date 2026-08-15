package ru.thevalidator.telegramtoxicbot.message.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.thevalidator.telegramtoxicbot.message.converter.DateTimeConverter;
import ru.thevalidator.telegramtoxicbot.message.service.NotificationService;

import java.time.Instant;
import java.time.LocalDateTime;

import static ru.thevalidator.telegramtoxicbot.message.util.DateTimeFormatterUtil.BUILD_FORMATTER;
import static ru.thevalidator.telegramtoxicbot.message.util.DateTimeFormatterUtil.START_FORMATTER;

@Slf4j
@Component
@RequiredArgsConstructor
public class StartupNotifier {

    private final BuildProperties buildProperties;

    private final NotificationService notificationService;

    private final DateTimeConverter dateTimeConverter;

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        try {
            String text = getStartupText();
            notificationService.sendNotification(text);
        } catch (Exception e) {
            log.error("Failed to send startup notification", e);
        }
    }

    private String getStartupText() {
        LocalDateTime buildLocalDateTime = dateTimeConverter.getLocalDateTime(buildProperties.getTime());
        String buildTime = buildLocalDateTime.format(BUILD_FORMATTER);
        LocalDateTime now = dateTimeConverter.getLocalDateTime(Instant.now());
        String startTime = now.format(START_FORMATTER);
        String appVer = buildProperties.getVersion();
        return "Bot started %s.%nVer: %s%nBuild: %s".formatted(startTime, appVer, buildTime);
    }

}
