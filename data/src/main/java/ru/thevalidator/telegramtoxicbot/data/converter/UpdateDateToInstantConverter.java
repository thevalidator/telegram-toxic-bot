package ru.thevalidator.telegramtoxicbot.data.converter;

import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class UpdateDateToInstantConverter {

    public Instant toInstant(Integer value) {
        return Instant.ofEpochSecond(value);
    }

}
