package com.url_shortener.service.impl;

import com.url_shortener.domain.entity.UrlEntity;
import com.url_shortener.domain.repository.UrlEntityRepository;
import com.url_shortener.domain.utils.ShortCodeUtils;
import com.url_shortener.excetion.BusinessException;
import com.url_shortener.service.Base62Service;
import com.url_shortener.service.UrlService;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class UrlServiceImpl implements UrlService {

    private final ModelMapper modelMapper;
    private final Base62Service base62Service;
    private final UrlEntityRepository urlEntityRepository;

    public UrlServiceImpl(UrlEntityRepository urlEntityRepository,
                          ModelMapper modelMapper,
                          Base62Service base62Service)
    {
        this.modelMapper = modelMapper;
        this.base62Service = base62Service;
        this.urlEntityRepository = urlEntityRepository;
    }

    @Override
    public UrlEntity save(UrlEntity urlEntity) {
        if(urlEntityRepository.existsByOriginalUrl(urlEntity.getOriginalUrl())) {
            throw new BusinessException("Esta url já exite na base de dados");
        }

        final UUID VAR_UUID = java.util.UUID.randomUUID();
        var code = ShortCodeUtils.generateFromUuid(VAR_UUID.toString());
        var encoded = base62Service.encode(code);

        urlEntity.setShortCode(encoded);
        urlEntity.setUuid(VAR_UUID);
        urlEntity.setCreatedAt(Instant.now());
        urlEntity.setUpdatedAt(null);

        return urlEntityRepository.save(urlEntity);
    }

    @Override
    @Cacheable(value = "codeUrl", key = "#code")
    public Optional<UrlEntity> findByShortCode(String code) {
        return urlEntityRepository.findById(code);
    }

}
