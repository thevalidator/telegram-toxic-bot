package ru.thevalidator.telegramtoxicbot.data.repository.impl;

import org.springframework.stereotype.Repository;
import ru.thevalidator.telegramtoxicbot.data.repository.AbusiveNamesRepository;

import java.util.Collections;
import java.util.List;

@Repository
public class AbusiveNamesRepositoryImpl implements AbusiveNamesRepository {

    private final List<String> abusiveNamePhrases;

    public AbusiveNamesRepositoryImpl(List<String> abusiveNamePhrases) {
        this.abusiveNamePhrases = Collections.unmodifiableList(abusiveNamePhrases);
    }

    @Override
    public List<String> getAll() {
        return abusiveNamePhrases;
    }

}
