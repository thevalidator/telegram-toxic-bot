package ru.thevalidator.telegramtoxicbot.data.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.thevalidator.telegramtoxicbot.data.exception.UserNotFoundException;
import ru.thevalidator.telegramtoxicbot.data.model.TelegramUserEntity;
import ru.thevalidator.telegramtoxicbot.data.model.UserState;
import ru.thevalidator.telegramtoxicbot.data.repository.TelegramUserEntityRepository;
import ru.thevalidator.telegramtoxicbot.data.service.TelegramUserHashComputeService;
import ru.thevalidator.telegramtoxicbot.data.service.TelegramUserService;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TelegramUserServiceImpl implements TelegramUserService {

    private final TelegramUserEntityRepository repository;

    private final TelegramUserHashComputeService hashComputeService;

    @Transactional(readOnly = true)
    @Override
    public TelegramUserEntity getUserByTelegramId(Long telegramId) {
        return repository.findByTelegramIdAndIsCurrentTrue(telegramId)
                .orElseThrow(() -> new UserNotFoundException(telegramId));
    }

    @Transactional
    @Override
    public UserState saveOrUpdate(User telegramUser) {
        Optional<TelegramUserEntity> fromDB = repository.findByTelegramIdAndIsCurrentTrue(telegramUser.getId());
        int userHash = hashComputeService.computeHash(telegramUser);
        if (fromDB.isPresent()) {
            TelegramUserEntity entityFromDb = fromDB.get();
            if (entityFromDb.getUserHash() != userHash) {
                entityFromDb.setIsCurrent(false);
                repository.saveAndFlush(entityFromDb);
                int newVersion = entityFromDb.getVersion() + 1;
                TelegramUserEntity updatedEntity = createTelegramUserEntity(telegramUser, userHash, newVersion);
                repository.save(updatedEntity);
                return UserState.UPDATED;
            } else {
                return UserState.EXISTS;
            }
        } else {
            TelegramUserEntity newEntity = createTelegramUserEntity(telegramUser, userHash, 1);
            repository.save(newEntity);
            return UserState.NEW;
        }
    }

    private TelegramUserEntity createTelegramUserEntity(User telegramUser, int userHash, int version) {
        TelegramUserEntity entity = new TelegramUserEntity();
        entity.setCreatedAt(Instant.now());
        entity.setIsCurrent(true);
        entity.setTelegramId(telegramUser.getId());
        entity.setUserData(telegramUser);
        entity.setUserHash(userHash);
        entity.setVersion(version);
        return entity;
    }

}
