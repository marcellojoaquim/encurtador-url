package com.url_shortener.config;

import io.seruco.encoding.base62.Base62;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Base62Config {

    @Bean
    Base62 base62(){
        return Base62.createInstanceWithInvertedCharacterSet();
    }
}
