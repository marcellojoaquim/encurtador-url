package com.url_shortener.rest.controller;

import com.url_shortener.domain.entity.UrlEntity;
import com.url_shortener.rest.dto.UrlEntityRequest;
import com.url_shortener.rest.dto.UrlEntityResponse;
import com.url_shortener.service.UrlService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping("/url")
public class UrlEntityController {

    private final UrlService urlService;
    private final ModelMapper modelMapper;

    public UrlEntityController(UrlService urlService, ModelMapper modelMapper) {
        this.urlService = urlService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UrlEntityResponse save(@RequestBody @Valid UrlEntityRequest urlEntityRequest) {
        UrlEntity url = modelMapper.map(urlEntityRequest, UrlEntity.class);
        url = urlService.save(url);
        return modelMapper.map(url, UrlEntityResponse.class);
    }

    @GetMapping("find/{code}")
    @ResponseStatus(HttpStatus.OK)
    public UrlEntityResponse findByShortCode(@PathVariable String code) {
        return urlService.findByShortCode(code)
                .map(url -> modelMapper.map(url, UrlEntityResponse.class))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

//    @GetMapping("/find")
//    public ResponseEntity<PageResponse<UrlEntityResponse>> findAll(
//            @PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
//        return ResponseEntity.ok(urlService.findAll(pageable));
//    }

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
