package ru.thevalidator.telegramtoxicbot.message.util;

import java.time.format.DateTimeFormatter;

public class DateTimeFormatterUtil {

    public static final DateTimeFormatter BUILD_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd.HHmmss");

    public static final DateTimeFormatter START_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd 'at' HH:mm.ss");

    private DateTimeFormatterUtil() {
    }

}
