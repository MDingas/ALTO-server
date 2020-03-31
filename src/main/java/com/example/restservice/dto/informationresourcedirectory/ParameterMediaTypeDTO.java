package com.example.restservice.dto.informationresourcedirectory;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum ParameterMediaTypeDTO {
    ENDPOINT_PROPERTY_PARAMETER("application/alto-endpointpropparams+json"),
    ENDPOINT_COST_MAP_PARAMETER("application/alto-endpointcostparams+json"),
    NETWORK_MAP_PARAMETER("application/alto-networkmapfilter+json"),
    COST_MAP_PARAMETER("application/alto-costmapfilter+json");

    private final String value;
    private static final Map<String, ParameterMediaTypeDTO> ENUM_MAP;

    static {
        Map<String, ParameterMediaTypeDTO> valueToENumMap = new HashMap<>();
        for (ParameterMediaTypeDTO parameterMediaTypeDTO : ParameterMediaTypeDTO.values()) {
            valueToENumMap.put(parameterMediaTypeDTO.getValue(), parameterMediaTypeDTO);
        }
        ENUM_MAP = Collections.unmodifiableMap(valueToENumMap);
    }

    private ParameterMediaTypeDTO(String value) {
        this.value = value;
    }

    @JsonCreator
    public static ParameterMediaTypeDTO fromString(String value) {
        if (ENUM_MAP.containsKey(value)) {
            return ENUM_MAP.get(value);
        } else {
            throw new IllegalArgumentException("Could not find ParameterMediaTypeDTO instance with value " + value);
        }
    }

    @JsonValue
    public String getValue() {
        return value;
    }

}
