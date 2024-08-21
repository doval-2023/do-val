package com.dov.sbapp.rest.response.helper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class JsonResponseHelper {

    public static ResponseEntity<Object> generateJsonResponse(String message,
                                                              HttpStatus status,
                                                              Object responseObject){
        Map<String, Object> responseMap = new HashMap<String, Object>();
        responseMap.put("message", message);
        responseMap.put("status", status.value());
        responseMap.put("data", responseObject);
        return new ResponseEntity<Object>(responseMap, status);
    }

}
