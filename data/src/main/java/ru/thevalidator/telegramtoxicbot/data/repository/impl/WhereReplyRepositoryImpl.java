package ru.thevalidator.telegramtoxicbot.data.repository.impl;

import org.springframework.stereotype.Repository;
import ru.thevalidator.telegramtoxicbot.data.repository.WhereReplyRepository;

import java.util.Collections;
import java.util.List;

@Repository
public class WhereReplyRepositoryImpl implements WhereReplyRepository {

    private final List<String> whereReplyPhrases;

    public WhereReplyRepositoryImpl(List<String> whereReplyPhrases) {
        Collections.shuffle(whereReplyPhrases);
        this.whereReplyPhrases = Collections.unmodifiableList(whereReplyPhrases);
    }

    @Override
    public List<String> getAll() {
        return whereReplyPhrases;
    }

}
