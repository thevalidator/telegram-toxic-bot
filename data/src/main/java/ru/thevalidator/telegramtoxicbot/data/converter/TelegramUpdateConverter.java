package ru.thevalidator.telegramtoxicbot.data.converter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.thevalidator.telegramtoxicbot.data.converter.extractor.UpdateMetaDataExtractor;
import ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdate;
import ru.thevalidator.telegramtoxicbot.data.model.UpdateMetaData;

@Slf4j
@Component
@RequiredArgsConstructor
public class TelegramUpdateConverter {

    private final JsonConverter jsonConverter;

    private final UpdateMetaDataExtractor dataExtractor;

    public TelegramUpdate toTelegramUpdate(Update update) {
        log.debug("Update to convert: \n{}", jsonConverter.objectToPrettyJson(update));
        UpdateMetaData metaData = dataExtractor.extractMetaData(update);
        return new TelegramUpdate(metaData, update);
    }

}
