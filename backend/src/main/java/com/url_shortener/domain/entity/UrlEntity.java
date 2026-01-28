package com.url_shortener.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import java.time.Instant;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "urls")
public class UrlEntity {

    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "url_seq")
    @SequenceGenerator(name = "url_seq", sequenceName = "seq_url", initialValue = 10000)
    private Long id;

    @Column(name = "short_code", unique = true)
    private String shortCode;

    @Column(name = "original_url", unique = true)
    private String originalUrl;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}
