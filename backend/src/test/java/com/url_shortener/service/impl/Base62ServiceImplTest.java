package com.url_shortener.service.impl;

import io.seruco.encoding.base62.Base62;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class Base62ServiceImplTest {

    @Spy
    private Base62 base62 = Base62.createInstanceWithInvertedCharacterSet();

    @InjectMocks
    private Base62ServiceImpl base62Service;

    @Test
    @DisplayName("Deve codificar uma string com sucesso para Base62")
    void shouldEncodeString() {
        String input = "123456789";

        String result = base62Service.encode(input);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertNotEquals(input, result);
    }

    @Test
    @DisplayName("Deve gerar o mesmo código sempre")
    void shouldReturnSameOutputForSameInput() {
        String input = "https://google.com";

        String encodedFirstTime = base62Service.encode(input);
        String encodedSecondTime = base62Service.encode(input);

        assertEquals(encodedFirstTime, encodedSecondTime);
    }
}