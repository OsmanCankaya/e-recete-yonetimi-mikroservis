package com.prescriptionmanagement.medication_service.payload.response.error;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private int statusCode;
    private String message;
    private Map<String, Object> properties = new HashMap<>();

    public ErrorResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public void addProperty(String key, Object value) {
        properties.put(key, value);
    }

    @JsonAnyGetter
    public Map<String, Object> getProperties() {
        return properties;
    }
}
