package ru.thevalidator.telegramtoxicbot.data.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.thevalidator.telegramtoxicbot.data.exception.NoDataFoundException;
import ru.thevalidator.telegramtoxicbot.data.repository.ReplyToBotRepository;
import ru.thevalidator.telegramtoxicbot.data.service.ReplyToBotService;

import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class ReplyToBotServiceImpl implements ReplyToBotService {

    private final ReplyToBotRepository replyToBotRepository;

    private final AtomicInteger index = new AtomicInteger(0);

    @Override
    public String getNextPhrase() {
        var phrases = replyToBotRepository.getAll();
        if (phrases.isEmpty()) {
            throw new NoDataFoundException("No any 'reply to bot message' replies found");
        }
        int nextPhraseIndex = index.getAndSet((index.intValue() + 1) % phrases.size());
        return phrases.get(nextPhraseIndex);
    }

}
