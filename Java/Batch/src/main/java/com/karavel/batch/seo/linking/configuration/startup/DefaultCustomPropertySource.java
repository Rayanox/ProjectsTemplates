package com.karavel.batch.seo.linking.configuration.startup;

import com.karavel.batch.seo.linking.exceptions.ProfileNotSetException;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;

public class DefaultCustomPropertySource extends DefaultPropertySourceFactory {

    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        throw new IOException(new ProfileNotSetException());
    }
}
