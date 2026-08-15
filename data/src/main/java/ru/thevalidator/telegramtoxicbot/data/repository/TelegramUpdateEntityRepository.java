package ru.thevalidator.telegramtoxicbot.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.thevalidator.telegramtoxicbot.data.model.TelegramUpdateEntity;

public interface TelegramUpdateEntityRepository extends JpaRepository<TelegramUpdateEntity, Long> {

}
