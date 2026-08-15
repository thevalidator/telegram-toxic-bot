package ru.thevalidator.telegramtoxicbot.data.config;

import com.vdurmont.emoji.EmojiParser;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import ru.thevalidator.telegramtoxicbot.data.exception.FileNotFoundException;
import ru.thevalidator.telegramtoxicbot.data.exception.FileReadException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class ReplyDataConfig {

    private final ResourceLoader resourceLoader;

    private final FileNameProperties fileNameProperties;

    @Bean
    public List<String> abusiveNamePhrases() {
        try {
            String path = fileNameProperties.abusiveNamePhrasesFile();
            return readTextFile(path);
        } catch (Exception e) {
            throw new FileReadException(e.getMessage(), e);
        }
    }

    @Bean
    public List<String> botMentionReplyPhrases() {
        try {
            String path = fileNameProperties.botMentionReplyPhrasesFile();
            return readTextFile(path);
        } catch (Exception e) {
            throw new FileReadException(e.getMessage(), e);
        }
    }

    @Bean
    public List<String> aahReplyPhrases() {
        try {
            String path = fileNameProperties.aahReplyPhrasesFile();
            return readTextFile(path);
        } catch (Exception e) {
            throw new FileReadException(e.getMessage(), e);
        }
    }

    @Bean
    public List<String> whereReplyPhrases() {
        try {
            String path = fileNameProperties.whereReplyPhrasesFile();
            return readTextFile(path);
        } catch (Exception e) {
            throw new FileReadException(e.getMessage(), e);
        }
    }

    @Bean
    public List<String> yesReplyPhrases() {
        try {
            String path = fileNameProperties.yesReplyPhrasesFile();
            return readTextFile(path);
        } catch (Exception e) {
            throw new FileReadException(e.getMessage(), e);
        }
    }

    @Bean
    public List<String> threeHundredReplyPhrases() {
        try {
            String path = fileNameProperties.threeHundredReplyPhrasesFile();
            return readTextFile(path);
        } catch (Exception e) {
            throw new FileReadException(e.getMessage(), e);
        }
    }

    @Bean
    public List<String> noReplyPhrases() {
        try {
            String path = fileNameProperties.noReplyPhrasesFile();
            return readTextFile(path);
        } catch (Exception e) {
            throw new FileReadException(e.getMessage(), e);
        }
    }

    @Bean
    public List<String> shortNoReplyPhrases() {
        try {
            String path = fileNameProperties.shortNoReplyPhrasesFile();
            return readTextFile(path);
        } catch (Exception e) {
            throw new FileReadException(e.getMessage(), e);
        }
    }

    @Bean
    public List<String> replyToBotPhrases() {
        try {
            String middleFinger = EmojiParser.parseToUnicode("\uD83D\uDD95");
            String path = fileNameProperties.replyToBotReplyPhrasesFile();
            List<String> phrases = readTextFile(path);
            phrases.add(middleFinger);
            return phrases;
        } catch (Exception e) {
            throw new FileReadException(e.getMessage(), e);
        }
    }

    private List<String> readTextFile(String path) throws IOException {
        InputStream is = getFileFromResourceAsStream(path);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            return br.lines().distinct().collect(Collectors.toCollection(ArrayList::new));
        }
    }

    private InputStream getFileFromResourceAsStream(String path) {
        final Resource resource = resourceLoader.getResource(path);
        try {
            return resource.getInputStream();
        } catch (IOException e) {
            throw new FileNotFoundException(e.getMessage());
        }
    }

}
