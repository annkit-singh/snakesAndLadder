package com.explore.snakesladder.configs;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.io.InputStream;

public class ConfigurationLoader {
    public static GameConfiguration loadConfiguration() throws IOException {
        InputStream inputStream = ConfigurationLoader.class.getClassLoader()
                .getResourceAsStream("gameConfiguration.yaml");
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        GameConfiguration config = objectMapper.readValue(inputStream, GameConfiguration.class);

        return config;
    }
}