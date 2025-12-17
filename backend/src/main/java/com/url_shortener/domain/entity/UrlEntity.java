package com.url_shortener.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.Instant;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Table("urls")
public class UrlEntity {

    @PrimaryKey
    private String shortCode;

    @Column("original_url")
    private String originalUrl;

    @Column("created_at")
    private Instant createdAt;
}
