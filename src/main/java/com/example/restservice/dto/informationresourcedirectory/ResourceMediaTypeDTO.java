package com.example.restservice.dto.informationresourcedirectory;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum ResourceMediaTypeDTO {
    NETWORK_MAP("application/alto-networkmap+json"),
    COST_MAP("application/alto-costmap+json"),
    INFORMATION_RESOURCE_DIRECTORY("application/alto-directory+json"),
    ENDPOINT_PROPERTY_MAP("application/alto-endpointprop+json"),
    ENDPOINT_COST_MAP("application/alto-endpointcost+json");

    private final String value;
    private static final Map<String, ResourceMediaTypeDTO> ENUM_MAP;

    static {
        Map<String, ResourceMediaTypeDTO> valueToEnumMap = new HashMap<>();
        for (ResourceMediaTypeDTO mediaTypeDTO : ResourceMediaTypeDTO.values()) {
            valueToEnumMap.put(mediaTypeDTO.getValue(), mediaTypeDTO);
        }
        ENUM_MAP = Collections.unmodifiableMap(valueToEnumMap);
    }

    private ResourceMediaTypeDTO(String value) {
        this.value = value;
    }

    @JsonCreator
    public static ResourceMediaTypeDTO fromString(String value) {
        if (ENUM_MAP.containsKey(value)) {
            return ENUM_MAP.get(value);
        } else {
            throw new IllegalArgumentException("Could not find ResourceMediaTypeDTO instance with value " + value);
        }
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
