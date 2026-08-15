package ru.thevalidator.telegramtoxicbot.data.converter;

import org.junit.jupiter.api.Test;

import java.time.Instant;

class UpdateDateToInstantConverterTest {

    @Test
    void toLocalDateTime() {
    }

    @Test
    void toInstant() {
        int date = 1771010568;
        Instant i = Instant.parse("2026-02-13T19:22:48.433Z");
        Instant i2 = Instant.parse("2026-02-13T22:22:48.433+03:00");
        long dd = i2.getEpochSecond();
        Instant i3 = Instant.ofEpochSecond(date);
        System.out.println(date + " = " + i + " = " + i2 + " = " + dd + " = " + i3);
    }

}