package com.possr.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.IOException;
import java.util.Properties;

public class YamlPropertySourceFactory implements PropertySourceFactory {
    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
        factory.setResources(resource.getResource());
        Properties properties = factory.getObject();

        // Asegurar que properties no sea null
        Properties nonNullProperties = properties != null ? properties : new Properties();

        // Usar el nombre proporcionado, o el nombre del archivo, o un nombre por defecto
        String sourceName = name != null ? name : resource.getResource().getFilename();
        if (sourceName == null) {
            sourceName = "yaml-resource";
        }

        return new PropertiesPropertySource(sourceName, nonNullProperties);
    }
}
