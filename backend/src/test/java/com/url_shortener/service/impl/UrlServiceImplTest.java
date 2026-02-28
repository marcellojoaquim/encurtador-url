package com.url_shortener.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import com.url_shortener.domain.entity.UrlEntity;
import com.url_shortener.domain.repository.UrlEntityRepository;
import com.url_shortener.domain.utils.ShortCodeUtils;
import com.url_shortener.rest.dto.UrlEntityRequest;
import com.url_shortener.rest.dto.UrlEntityResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class UrlServiceImplTest {

    @InjectMocks
    UrlServiceImpl urlService;

    @Mock
    UrlEntityRepository urlEntityRepository;

    @Spy
    Base62ServiceImpl base62Service;

    private UrlEntity urlEntity;
    private UrlEntityResponse urlResponse;
    private ShortCodeUtils shortCodeUtils;
    private final Instant createdAt = Instant.now();

    @BeforeEach
    void setUp() {
        UUID uuid = UUID.randomUUID();
        String encoded = base62Service.encode(ShortCodeUtils.generateFromUuid(uuid.toString()));
        UrlEntityRequest urlRequest = UrlEntityRequest.builder()
                .originalUrl("example.com/long-url-Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec volutpat enim cursus tortor ullamcorper, vel porta diam egestas. Fusce fermentum ipsum vel venenatis ornare. Ut at tortor in lectus luctus interdum. Maecenas iaculis convallis ex, dapibus posuere sapien posuere vitae. Suspendisse convallis arcu ac consequat luctus. Integer id condimentum orci.")
                .build();

        urlEntity = new UrlEntity();
        urlEntity.setUuid(uuid);
        urlEntity.setCreatedAt(createdAt);
        urlEntity.setUpdatedAt(null);
        urlEntity.setOriginalUrl(urlRequest.getOriginalUrl());
        urlEntity.setShortCode(encoded);
    }

    @Test
    @DisplayName("Deve salvar com sucesso")
    void save() {
        when(urlEntityRepository.save(any(UrlEntity.class))).thenReturn(urlEntity);
        UrlEntity result = urlService.save(urlEntity);

        Mockito.verify(urlEntityRepository, times(1)).save(urlEntity);
        assertEquals(result.getOriginalUrl(), urlEntity.getOriginalUrl());
        assertNotNull(urlEntity.getUuid());
        assertNotNull(urlEntity.getShortCode());
    }

    @Test
    void findByShortCode() {
    }

    @Test
    void findAll() {
    }
}