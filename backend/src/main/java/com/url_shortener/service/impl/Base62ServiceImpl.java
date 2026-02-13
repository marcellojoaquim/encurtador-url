package com.url_shortener.service.impl;

import com.url_shortener.service.Base62Service;
import io.seruco.encoding.base62.Base62;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Base62ServiceImpl implements Base62Service {

    @Autowired
    private Base62 base62 = Base62.createInstanceWithGmpCharacterSet();

    @Override
    public String encode(String code) {
        byte[] encoded = base62.encode(code.getBytes());
        return new String(encoded);
    }
}
