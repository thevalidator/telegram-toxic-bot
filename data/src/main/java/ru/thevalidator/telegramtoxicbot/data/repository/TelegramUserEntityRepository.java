package ru.thevalidator.telegramtoxicbot.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.thevalidator.telegramtoxicbot.data.model.TelegramUserEntity;

import java.util.Optional;

@Repository
public interface TelegramUserEntityRepository extends JpaRepository<TelegramUserEntity, Long> {

    Optional<TelegramUserEntity> findByTelegramIdAndIsCurrentTrue(Long telegramId);

}
