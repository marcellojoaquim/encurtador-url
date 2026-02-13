package com.url_shortener.service.impl;

import com.url_shortener.domain.entity.UrlEntity;
import com.url_shortener.domain.repository.UrlEntityRepository;
import com.url_shortener.rest.dto.UrlEntityRequest;
import com.url_shortener.rest.dto.UrlEntityResponse;
import com.url_shortener.service.Base62Service;
import com.url_shortener.service.UrlService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class UrlServiceImpl implements UrlService {

    private final ModelMapper modelMapper;
    private final Base62Service base62Service;
    private final UrlEntityRepository urlEntityRepository;

    public UrlServiceImpl(UrlEntityRepository urlEntityRepository, ModelMapper modelMapper, Base62Service base62Service) {
        this.modelMapper = modelMapper;
        this.base62Service = base62Service;
        this.urlEntityRepository = urlEntityRepository;
    }

    @Override
    public UrlEntityResponse save(UrlEntityRequest urlEntityRequest) {
        final UUID VAR_UUID = java.util.UUID.randomUUID();
        var code = getSubString(VAR_UUID.toString());
        var encoded = base62Service.encode(code);


        UrlEntity urlEntity = new UrlEntity();
        urlEntity.setOriginalUrl(urlEntityRequest.getUrl());
        urlEntity.setShortCode(encoded);
        urlEntity.setUuid(VAR_UUID);
        urlEntity.setCreatedAt(Instant.now());
        urlEntity.setUpdatedAt(null);

        urlEntityRepository.save(urlEntity);

        return new UrlEntityResponse(urlEntity.getOriginalUrl(), urlEntity.getShortCode());
    }

    private static String getSubString(String uuid){

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<8;i++){
            sb.append(uuid.charAt(i));
        }
        return sb.toString();
    }
}
