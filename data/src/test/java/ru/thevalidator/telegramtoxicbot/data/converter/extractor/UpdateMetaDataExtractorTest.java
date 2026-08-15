package ru.thevalidator.telegramtoxicbot.data.converter.extractor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.thevalidator.telegramtoxicbot.data.converter.UpdateDateToInstantConverter;
import ru.thevalidator.telegramtoxicbot.data.exception.UnknownUpdateType;
import ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdateSubType;
import ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdateType;
import ru.thevalidator.telegramtoxicbot.data.model.UpdateMetaData;
import ru.thevalidator.telegramtoxicbot.data.service.TelegramUpdateSubTypeDetectorService;
import ru.thevalidator.telegramtoxicbot.data.service.impl.TelegramUpdateSubTypeDetectorServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UpdateMetaDataExtractorTest {

    private ObjectMapper jsonMapper = new ObjectMapper();

    @Test
    void shouldThrowWhenExtractFromEmptyUpdate() {
        TelegramUpdateSubTypeDetectorService subTypeDetectorService = new TelegramUpdateSubTypeDetectorServiceImpl(null);
        UpdateMetaDataExtractor extractor = new UpdateMetaDataExtractor(subTypeDetectorService, new UpdateDateToInstantConverter());
        var update = new Update();
        assertThrows(UnknownUpdateType.class, () -> extractor.extractMetaData(update));
    }

    @Test
    void shouldExtractFromMessageUpdate() throws JsonProcessingException {
        String json = "{\n" +
                "  \"update_id\" : 271082449,\n" +
                "  \"message\" : {\n" +
                "    \"message_id\" : 17,\n" +
                "    \"from\" : {\n" +
                "      \"id\" : 312898894,\n" +
                "      \"first_name\" : \"ɪʟʟᴜꜱɪᴏɴ\",\n" +
                "      \"is_bot\" : false,\n" +
                "      \"username\" : \"thevalidator\",\n" +
                "      \"language_code\" : \"ru\",\n" +
                "      \"is_premium\" : true\n" +
                "    },\n" +
                "    \"date\" : 1768858462,\n" +
                "    \"chat\" : {\n" +
                "      \"id\" : 312898894,\n" +
                "      \"type\" : \"private\",\n" +
                "      \"first_name\" : \"ɪʟʟᴜꜱɪᴏɴ\",\n" +
                "      \"username\" : \"thevalidator\"\n" +
                "    },\n" +
                "    \"text\" : \"нет\"\n" +
                "  }\n" +
                "}";

        var update = jsonMapper.readValue(json, Update.class);
        TelegramUpdateSubTypeDetectorService subTypeDetectorService = new TelegramUpdateSubTypeDetectorServiceImpl(null);
        UpdateMetaDataExtractor extractor = new UpdateMetaDataExtractor(subTypeDetectorService, new UpdateDateToInstantConverter());
        UpdateMetaData result = extractor.extractMetaData(update);

        assertEquals(TelegramUpdateType.MESSAGE, result.type());
        assertEquals(TelegramUpdateSubType.NO_MESSAGE, result.subType());
        assertEquals(312898894L, result.userId());
        assertEquals(312898894L, result.chatId());
        assertTrue(result.createdAt().isPresent(), "No createdAt data found");
    }

}