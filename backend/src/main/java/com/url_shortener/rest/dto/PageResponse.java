package com.url_shortener.rest.dto;

import java.util.List;

public record PageResponse<T>(
   List<T> content,
   int currentPage,
   long totalElements,
   int totalPages,
   boolean last
) {}
