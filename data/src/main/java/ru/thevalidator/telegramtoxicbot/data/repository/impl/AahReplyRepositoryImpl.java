package ru.thevalidator.telegramtoxicbot.data.repository.impl;

import org.springframework.stereotype.Repository;
import ru.thevalidator.telegramtoxicbot.data.repository.AahReplyRepository;

import java.util.Collections;
import java.util.List;

@Repository
public class AahReplyRepositoryImpl implements AahReplyRepository {

    private final List<String> aahReplyPhrases;

    public AahReplyRepositoryImpl(List<String> aahReplyPhrases) {
        Collections.shuffle(aahReplyPhrases);
        this.aahReplyPhrases = Collections.unmodifiableList(aahReplyPhrases);
    }

    @Override
    public List<String> getAll() {
        return aahReplyPhrases;
    }

}
