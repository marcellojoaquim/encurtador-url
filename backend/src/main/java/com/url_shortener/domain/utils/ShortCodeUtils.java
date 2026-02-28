package com.url_shortener.domain.utils;

import java.util.UUID;

public class ShortCodeUtils {

    private ShortCodeUtils() {
        throw new UnsupportedOperationException("Classe utilitaria");
    }

    public static String generateFromUuid(String uuid) {
        return uuid.substring(0, 8);
    }

}
