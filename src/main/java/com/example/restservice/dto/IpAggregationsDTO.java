package com.example.restservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IpAggregationsDTO {

    @JsonProperty("ipv4")
    private String[] ipv4Aggregations;

    @JsonProperty("ipv6")
    private String[] ipv6Aggregations;

    public IpAggregationsDTO() {
    }

    public IpAggregationsDTO(String[] ipv4Aggregations, String[] ipv6Aggregations) {
        this.ipv4Aggregations = ipv4Aggregations;
        this.ipv6Aggregations = ipv6Aggregations;
    }

    public String[] getIpv4Aggregations() {
        return ipv4Aggregations;
    }

    public void setIpv4Aggregations(String[] ipv4Aggregations) {
        this.ipv4Aggregations = ipv4Aggregations;
    }

    public String[] getIpv6Aggregations() {
        return ipv6Aggregations;
    }

    public void setIpv6Aggregations(String[] ipv6Aggregations) {
        this.ipv6Aggregations = ipv6Aggregations;
    }
}
