package com.url_shortener.rest.controller;

import com.url_shortener.domain.entity.UrlEntity;
import com.url_shortener.rest.dto.UrlEntityRequest;
import com.url_shortener.rest.dto.UrlEntityResponse;
import com.url_shortener.service.UrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.Optional;

@Tag(name = "URLs", description = "Endpoints de gerenciamento e criação de URLs encurtadas")
@RestController
@RequestMapping("/url")
public class UrlEntityController {

    private final UrlService urlService;
    private final ModelMapper modelMapper;

    public UrlEntityController(UrlService urlService, ModelMapper modelMapper) {
        this.urlService = urlService;
        this.modelMapper = modelMapper;
    }

    @Operation(summary = "Encurta uma URL", description = "Recebe uma URL original e retorna o código encurtado")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UrlEntityResponse save(@RequestBody @Valid UrlEntityRequest urlEntityRequest) {
        UrlEntity url = modelMapper.map(urlEntityRequest, UrlEntity.class);
        url = urlService.save(url);
        return modelMapper.map(url, UrlEntityResponse.class);
    }

    @Operation(summary = "Retorna uma url original baseado na encurtada", description = "Recebe uma URL encurtada e retorna a URL original correspondente")
    @GetMapping("find/{code}")
    @ResponseStatus(HttpStatus.OK)
    public UrlEntityResponse findByShortCode(@PathVariable String code) {
        return urlService.findByShortCode(code)
                .map(url -> modelMapper.map(url, UrlEntityResponse.class))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Redireciona para a URL original", description = "Recebe via parametro a URL encurtada e redireciona para a URL original.")
    @GetMapping("{code}")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<Void> redirect(@PathVariable("code") String code) {
        Optional<UrlEntity> originalUrl = Optional.of(urlService.findByShortCode(code)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(originalUrl.get().getOriginalUrl()))
                .build();
    }
}
