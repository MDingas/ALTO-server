package com.example.restservice.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * How to interpret the values of a given cost map
 *
 * Further defined in https://www.rfc-editor.org/rfc/rfc7285.txt chapter 6.1.2
 */
public enum CostModeDTO {
    NUMERICAL("numerical"),
    ORDINAL("ordinal"),
    NOMINAL("nominal");

    private final String value;

    private CostModeDTO(String value) {
        this.value = value;
    }

    @JsonCreator
    public static CostModeDTO fromString(String value) {
        return value == null ? null : CostModeDTO.valueOf(value.toUpperCase());
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
