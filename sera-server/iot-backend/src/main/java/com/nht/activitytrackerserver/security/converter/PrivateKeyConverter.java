package com.nht.activitytrackerserver.security.converter;

import org.springframework.core.io.Resource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.converter.RsaKeyConverters;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.ByteArrayInputStream;
import java.security.interfaces.RSAPrivateKey;

@Component
public class PrivateKeyConverter implements Converter<Resource, RSAPrivateKey> {
    @Override
    public RSAPrivateKey convert(Resource from) {
        String keyString = loadKey(from);
        return RsaKeyConverters.pkcs8().convert(new ByteArrayInputStream(keyString.getBytes()));
    }

    private String loadKey(Resource resource) {
        try {
            byte[] keyBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
            return new String(keyBytes, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("Failed to load key from resource: " + resource, e);
        }
    }
}
