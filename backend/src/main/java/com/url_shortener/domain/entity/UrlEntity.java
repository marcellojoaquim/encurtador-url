package com.url_shortener.domain.entity;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Table("urls")
public class UrlEntity {

    @Column("id")
    private UUID uuid;

    @PrimaryKey
    @Column("short_code")
    private String shortCode;

    @NotNull
    @Column("original_url")
    private String originalUrl;

    @NotNull
    @Column("created_at")
    private Instant createdAt;

    @Column("updated_at")
    private Instant updatedAt;

    public UrlEntity(UUID uuid, String shortCode, String originalUrl, Instant createdAt, Instant updatedAt) {
        this.uuid = uuid;
        this.shortCode = shortCode;
        this.originalUrl = originalUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
