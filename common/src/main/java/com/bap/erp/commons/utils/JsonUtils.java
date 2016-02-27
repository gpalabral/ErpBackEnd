package com.bap.erp.commons.utils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.Serializable;

public class JsonUtils {

    private static final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static Serializable jsonToObject(String jsonString, Class clazz) throws JsonGenerationException, JsonMappingException, IOException {

        return (Serializable) mapper.readValue(jsonString, clazz);
    }

    public static Serializable objectToJson(Serializable object) throws JsonProcessingException {

        return mapper.writeValueAsString(object);
    }

    public static <T> T getObject(String jsonString, Class<T> className) {

        try {
            return mapper.readValue(jsonString, className);
        } catch (NullPointerException ex) {
            LogUtil.log("The required data " + jsonString + " is not in property file");
        } catch (IOException e) {
            LogUtil.log("Exception in JSON parsing. Cause: " + e);
        }
        return null;
    }

}
