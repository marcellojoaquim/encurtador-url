package com.url_shortener.rest.controller;

import com.url_shortener.domain.entity.UrlEntity;
import com.url_shortener.rest.dto.PageResponse;
import com.url_shortener.rest.dto.UrlEntityRequest;
import com.url_shortener.rest.dto.UrlEntityResponse;
import com.url_shortener.service.IUrlService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/url")
public class UrlEntityController {

    private final IUrlService urlService;
    private final ModelMapper modelMapper;

    public UrlEntityController(IUrlService urlService, ModelMapper modelMapper) {
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

    @GetMapping("/find")
    public ResponseEntity<PageResponse<UrlEntityResponse>> findAll(
            @PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(urlService.findAll(pageable));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String testeGet() {
        return "valido";
    }
}
