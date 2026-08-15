package ru.thevalidator.telegramtoxicbot.data.repository.impl;

import org.springframework.stereotype.Repository;
import ru.thevalidator.telegramtoxicbot.data.repository.ShortNoReplyRepository;

import java.util.Collections;
import java.util.List;

@Repository
public class ShortNoReplyRepositoryImpl implements ShortNoReplyRepository {

    private final List<String> shortNoReplyPhrases;

    public ShortNoReplyRepositoryImpl(List<String> shortNoReplyPhrases) {
        Collections.shuffle(shortNoReplyPhrases);
        this.shortNoReplyPhrases = Collections.unmodifiableList(shortNoReplyPhrases);
    }

    @Override
    public List<String> getAll() {
        return shortNoReplyPhrases;
    }

}
