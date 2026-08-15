package ru.thevalidator.telegramtoxicbot.data.repository.impl;

import org.springframework.stereotype.Repository;
import ru.thevalidator.telegramtoxicbot.data.repository.ReplyToBotRepository;

import java.util.Collections;
import java.util.List;

@Repository
public class ReplyToBotRepositoryImpl implements ReplyToBotRepository {

    private final List<String> replyToBotPhrases;

    public ReplyToBotRepositoryImpl(List<String> replyToBotPhrases) {
        Collections.shuffle(replyToBotPhrases);
        this.replyToBotPhrases = Collections.unmodifiableList(replyToBotPhrases);
    }

    @Override
    public List<String> getAll() {
        return replyToBotPhrases;
    }

}
