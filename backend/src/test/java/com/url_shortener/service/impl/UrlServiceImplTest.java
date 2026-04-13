package com.url_shortener.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import com.url_shortener.domain.entity.UrlEntity;
import com.url_shortener.domain.repository.UrlEntityRepository;
import com.url_shortener.domain.utils.ShortCodeUtils;
import com.url_shortener.rest.dto.PageResponse;
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
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class UrlServiceImplTest {

    @InjectMocks
    UrlServiceImpl urlService;

    @Mock
    UrlEntityRepository urlEntityRepository;

    @Mock
    Pageable pageable;

    @Mock
    PageRequest pageRequest;

    @Mock
    ModelMapper modelMapper;

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
        pageRequest = PageRequest.of(1, 1, Sort.by("id"));
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
    @DisplayName("Deve retornar urlEntity via shortcode")
    void findByShortCode() {
        var shortCode = "qwerty123qwe";
        when(urlEntityRepository.findByShortCode(anyString())).thenReturn(Optional.of(urlEntity));
        Optional<UrlEntity> result = urlService.findByShortCode(shortCode);

        Mockito.verify(urlEntityRepository, times(1)).findByShortCode(shortCode);
        assertEquals(result.orElseThrow().getUuid(), urlEntity.getUuid());
        assertEquals(result.orElseThrow().getOriginalUrl(), urlEntity.getOriginalUrl());
        assertEquals(result.orElseThrow().getCreatedAt(), urlEntity.getCreatedAt());
        assertNull(result.orElseThrow().getUpdatedAt());
    }

    @Test
    void findAll() {
        pageable = pageRequest;
        List<UrlEntity> list = List.of(urlEntity);
        Page<UrlEntity> page = new PageImpl<>(list, pageable, list.size());

        when(urlEntityRepository.findAll(any(Pageable.class))).thenReturn(page);

        PageResponse<UrlEntityResponse> allUrls = urlService.findAll(pageable);

        assertFalse(allUrls.content().isEmpty());
        assertEquals(1, allUrls.content().size());
    }
}