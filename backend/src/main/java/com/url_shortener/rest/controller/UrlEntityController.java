package com.url_shortener.rest.controller;

import com.url_shortener.rest.dto.UrlEntityRequest;
import com.url_shortener.rest.dto.UrlEntityResponse;
import com.url_shortener.service.UrlService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/url")
public class UrlEntityController {

    private final UrlService urlService;

    public UrlEntityController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UrlEntityResponse cadastrar(@RequestBody @Valid UrlEntityRequest urlEntityRequest) {
        return urlService.save(urlEntityRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String testeGet() {
        return "valido";
    }
}
