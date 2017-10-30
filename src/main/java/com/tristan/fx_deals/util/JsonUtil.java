package com.tristan.fx_deals.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by tristandiaz on 10/29/17.
 */
public final class JsonUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    private JsonUtil() {

    }

    public static <T> String toJsonString(T object) {

        String value = "{}";

        try {
            value = mapper.writeValueAsString(object);
        } catch (IOException e) {
            LOGGER.error("Error Serializing Object to JSON", e);
        }

        return value;
    }
}
