package ru.thevalidator.telegramtoxicbot.data.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.thevalidator.telegramtoxicbot.data.repository.AbusiveNamesRepository;
import ru.thevalidator.telegramtoxicbot.data.repository.BotMentionReplyRepository;
import ru.thevalidator.telegramtoxicbot.data.service.BotMentionReplyService;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class BotMentionReplyServiceImpl implements BotMentionReplyService {

    private final BotMentionReplyRepository botMentionReplyRepository;

    private final AbusiveNamesRepository abusiveNamesRepository;

    private final SecureRandom random = initRandom();

    private SecureRandom initRandom() {
        SecureRandom r = new SecureRandom();
        byte[] seed = r.generateSeed(55);
        r.setSeed(seed);
        return r;
    }

    @Override
    public String getNextPhrase() {
        int phraseIndex = random.nextInt(botMentionReplyRepository.getAll().size());
        int nameIndex = random.nextInt(abusiveNamesRepository.getAll().size());
        return "%s, %s".formatted(botMentionReplyRepository.getAll().get(phraseIndex),
                abusiveNamesRepository.getAll().get(nameIndex));
    }

}
