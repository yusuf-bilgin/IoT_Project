package com.nht.activitytrackerserver.security.converter;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.io.Resource;
import org.springframework.security.converter.RsaKeyConverters;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.ByteArrayInputStream;
import java.security.interfaces.RSAPublicKey;

@Component
public class PublicKeyConverter implements Converter<Resource, RSAPublicKey> {
    @Override
    public RSAPublicKey convert(Resource from) {
        String keyString = loadKey(from);
        return RsaKeyConverters.x509().convert(new ByteArrayInputStream(keyString.getBytes()));
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
