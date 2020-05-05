package com.example.restservice.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Identifies what a cost represents
 *
 * Further defined in https://www.rfc-editor.org/rfc/rfc7285.txt chapter 6.1.1
 * and https://www.ietf.org/id/draft-ietf-alto-performance-metrics-09.txt
 */

public enum CostMetricDTO {
    GENERIC_ROUTING_COST("routingcost"),
    ONE_WAY_DELAY("owdelay"),
    ROUND_TRIP_DELAY("rtt"),
    PACKET_DELAY_VARIATION("pdv"),
    HOP_COUNT("hopcount"),
    PACKET_LOSS("pktloss"),
    THROUGHPUT("throughput"),
    MAX_RESERVABLE_BANDWIDTH("maxresbw"),
    RESIDUE_BANDWIDTH("residuebw");

    private final String value;
    private static final Map<String, CostMetricDTO> ENUM_MAP;

    static {
        Map<String, CostMetricDTO> valueToEnumMap = new HashMap<>();
        for (CostMetricDTO costMetricDTO : CostMetricDTO.values()) {
            valueToEnumMap.put(costMetricDTO.getValue(), costMetricDTO);
        }
        ENUM_MAP = Collections.unmodifiableMap(valueToEnumMap);
    }

    private CostMetricDTO(String value) {
        this.value = value;
    }

    @JsonCreator
    public static CostMetricDTO fromString(String value) {
        if (ENUM_MAP.containsKey(value)) {
            return ENUM_MAP.get(value);
        } else {
            throw new IllegalArgumentException("Could not find CostMetricDTO instance with value " + value);
        }
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}