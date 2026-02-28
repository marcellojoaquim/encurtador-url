package com.url_shortener.rest.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlEntityResponse {

    @NotNull
    public String originalUrl;

    @NotNull
    public String shortCode;
}
