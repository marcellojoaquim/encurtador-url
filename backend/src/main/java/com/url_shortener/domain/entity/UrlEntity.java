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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "short_code")
    private String shortCode;

    @Column(name = "original_url")
    private String originalUrl;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}
