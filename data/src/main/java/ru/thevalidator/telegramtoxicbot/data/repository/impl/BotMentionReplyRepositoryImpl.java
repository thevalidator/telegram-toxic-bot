package ru.thevalidator.telegramtoxicbot.data.repository.impl;

import org.springframework.stereotype.Repository;
import ru.thevalidator.telegramtoxicbot.data.repository.BotMentionReplyRepository;

import java.util.Collections;
import java.util.List;

@Repository
public class BotMentionReplyRepositoryImpl implements BotMentionReplyRepository {

    private final List<String> botMentionReplyPhrases;

    public BotMentionReplyRepositoryImpl(List<String> botMentionReplyPhrases) {
        this.botMentionReplyPhrases = Collections.unmodifiableList(botMentionReplyPhrases);
    }

    @Override
    public List<String> getAll() {
        return botMentionReplyPhrases;
    }

}
