package org.example.saveuserinfoservice.dto.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum HttpMethodDTO {
    POST, DELETE, PATCH;
    @JsonValue
    public String toValue() {
        return this.name();
    }
    @JsonCreator
    public static HttpMethodDTO fromValue(String value) {
        return HttpMethodDTO.valueOf(value.toUpperCase());
    }
}
