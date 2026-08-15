package ru.thevalidator.telegramtoxicbot.data.repository.impl;

import org.springframework.stereotype.Repository;
import ru.thevalidator.telegramtoxicbot.data.repository.NoReplyRepository;

import java.util.Collections;
import java.util.List;

@Repository
public class NoReplyRepositoryImpl implements NoReplyRepository {

    private final List<String> noReplyPhrases;

    public NoReplyRepositoryImpl(List<String> noReplyPhrases) {
        Collections.shuffle(noReplyPhrases);
        this.noReplyPhrases = Collections.unmodifiableList(noReplyPhrases);
    }

    @Override
    public List<String> getAll() {
        return noReplyPhrases;
    }

}
