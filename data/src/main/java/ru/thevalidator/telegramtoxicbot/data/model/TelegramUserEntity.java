package ru.thevalidator.telegramtoxicbot.data.model;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.telegram.telegrambots.meta.api.objects.User;

import java.time.Instant;

@Entity
@Table(name = "telegram_user",
        uniqueConstraints = {
                @UniqueConstraint(name = "idx_unique_current_user", columnNames = {"telegram_id", "is_current"})
        })
@Getter
@Setter
public class TelegramUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "telegram_id", nullable = false)
    private Long telegramId;

    @Type(JsonBinaryType.class)
    @Column(name = "user_data", columnDefinition = "jsonb")
    private User userData;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "version", nullable = false)
    private Integer version;

    @Column(name = "is_current")
    private Boolean isCurrent;

    @Column(name = "user_hash", nullable = false)
    private Integer userHash;

}
