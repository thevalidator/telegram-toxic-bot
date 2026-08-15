package ru.thevalidator.telegramtoxicbot.data.repository.impl;

import org.springframework.stereotype.Repository;
import ru.thevalidator.telegramtoxicbot.data.repository.ThreeHundredReplyRepository;

import java.util.Collections;
import java.util.List;

@Repository
public class ThreeHundredReplyRepositoryImpl implements ThreeHundredReplyRepository {

    private final List<String> threeHundredReplyPhrases;

    public ThreeHundredReplyRepositoryImpl(List<String> threeHundredReplyPhrases) {
        Collections.shuffle(threeHundredReplyPhrases);
        this.threeHundredReplyPhrases = Collections.unmodifiableList(threeHundredReplyPhrases);
    }

    @Override
    public List<String> getAll() {
        return threeHundredReplyPhrases;
    }

}
