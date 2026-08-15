package ru.thevalidator.telegramtoxicbot.data.repository.impl;

import org.springframework.stereotype.Repository;
import ru.thevalidator.telegramtoxicbot.data.repository.YesReplyRepository;

import java.util.Collections;
import java.util.List;

@Repository
public class YesReplyRepositoryImpl implements YesReplyRepository {

    private final List<String> yesReplyPhrases;

    public YesReplyRepositoryImpl(List<String> yesReplyPhrases) {
        Collections.shuffle(yesReplyPhrases);
        this.yesReplyPhrases = Collections.unmodifiableList(yesReplyPhrases);
    }

    @Override
    public List<String> getAll() {
        return yesReplyPhrases;
    }

}
