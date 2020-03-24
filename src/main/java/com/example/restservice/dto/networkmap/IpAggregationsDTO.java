package com.example.restservice.dto.networkmap;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Field;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IpAggregationsDTO {

    @Field("ipv4")
    private String[] ipv4Aggregations;

    @Field("ipv6")
    private String[] ipv6Aggregations;

    public IpAggregationsDTO(@JsonProperty(value = "ipv4", required = false) String[] ipv4Aggregations,
                             @JsonProperty(value = "ipv6", required = false) String[] ipv6Aggregations) {
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
