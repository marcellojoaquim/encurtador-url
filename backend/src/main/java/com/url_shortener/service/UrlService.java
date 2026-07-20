package com.url_shortener.service;

import com.url_shortener.domain.entity.UrlEntity;

import java.util.Optional;

public interface UrlService {

    UrlEntity save(UrlEntity urlEntity);
    Optional<UrlEntity> findByShortCode(String code);
}
