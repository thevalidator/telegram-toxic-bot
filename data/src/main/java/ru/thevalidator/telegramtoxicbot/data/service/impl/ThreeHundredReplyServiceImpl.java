package ru.thevalidator.telegramtoxicbot.data.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.thevalidator.telegramtoxicbot.data.exception.NoDataFoundException;
import ru.thevalidator.telegramtoxicbot.data.repository.ThreeHundredReplyRepository;
import ru.thevalidator.telegramtoxicbot.data.service.ThreeHundredReplyService;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class ThreeHundredReplyServiceImpl implements ThreeHundredReplyService {

    private final ThreeHundredReplyRepository repository;

    private final AtomicInteger index = new AtomicInteger(0);

    @Override
    public String getNextPhrase() {
        List<String> phrases = repository.getAll();
        if (phrases.isEmpty()) {
            throw new NoDataFoundException("No any 'three hundred message' replies found");
        }
        int nextPhraseIndex = index.getAndSet((index.intValue() + 1) % phrases.size());
        return phrases.get(nextPhraseIndex);
    }

}
