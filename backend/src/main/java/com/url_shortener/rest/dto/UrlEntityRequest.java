package com.url_shortener.rest.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UrlEntityRequest {

    @NotNull
    private String originalUrl;
}
