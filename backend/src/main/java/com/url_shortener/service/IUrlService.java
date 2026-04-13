package com.url_shortener.service;

import com.url_shortener.domain.entity.UrlEntity;
import com.url_shortener.rest.dto.PageResponse;
import com.url_shortener.rest.dto.UrlEntityResponse;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IUrlService {

    UrlEntity save(UrlEntity urlEntity);
    Optional<UrlEntity> findByShortCode(String code);
    PageResponse<UrlEntityResponse> findAll(Pageable pageable);
}
