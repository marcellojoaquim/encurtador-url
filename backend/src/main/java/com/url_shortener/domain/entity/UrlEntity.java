package com.url_shortener.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "urls")
@Entity
public class UrlEntity {

    @Id()
    private UUID uuid;

    @NotNull
    @Column(name = "short_code")
    private String shortCode;

    @NotNull
    @Column(name = "original_url", unique = true, columnDefinition = "text")
    private String originalUrl;

    @NotNull
    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    public UrlEntity(UUID uuid, String shortCode, String originalUrl, Instant createdAt, Instant updatedAt) {
        this.uuid = uuid;
        this.shortCode = shortCode;
        this.originalUrl = originalUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
