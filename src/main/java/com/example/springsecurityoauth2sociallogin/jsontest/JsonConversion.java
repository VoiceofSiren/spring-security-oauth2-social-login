package com.example.springsecurityoauth2sociallogin.jsontest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConversion {

    /**
     *  Object -> JSON
     */
    public String test1() throws JsonProcessingException {
        Star star = new Star("Robbie", 95);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(star);

        return json;
    }

    /**
     *  JSON -> Object
     */
    public Star test2() throws JsonProcessingException {
        String json = "{\"name\":\"Robbie\", \"age\":95}";

        ObjectMapper objectMapper = new ObjectMapper();
        Star star = objectMapper.readValue(json, Star.class);

        return star;
    }
}
