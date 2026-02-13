package com.url_shortener.service;

import com.url_shortener.domain.repository.UrlEntityRepository;
import com.url_shortener.rest.dto.UrlEntityRequest;
import com.url_shortener.rest.dto.UrlEntityResponse;

public interface UrlService {

    UrlEntityResponse save(UrlEntityRequest urlEntityRequest);
}
