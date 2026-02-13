package com.url_shortener.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UrlEntityRequest {

    private String url;
}
