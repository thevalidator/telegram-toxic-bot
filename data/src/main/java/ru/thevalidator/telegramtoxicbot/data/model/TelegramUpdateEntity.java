package ru.thevalidator.telegramtoxicbot.data.model;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.Instant;

@Entity
@Table(name = "telegram_update")
@Getter
@Setter
public class TelegramUpdateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TelegramUpdateType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "sub_type")
    private TelegramUpdateSubType subType;

    @Column(name = "telegram_id")
    private Long telegramId;

    @Type(JsonBinaryType.class)
    @Column(name = "update_data", columnDefinition = "jsonb")
    private Update updateData;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "timestamp")
    private Instant timestamp;

}
