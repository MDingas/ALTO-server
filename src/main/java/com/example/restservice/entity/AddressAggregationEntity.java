package com.example.restservice.entity;

import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public class AddressAggregationEntity {

    @Field("pid")
    @NotNull
    private String pid;

    @Field("ipv4-addresses")
    private List<String> ipv4AddressList;

    @Field("ipv6-addresses")
    private List<String> ipv6AddressList;

    public AddressAggregationEntity(String pid, List<String> ipv4AddressList, List<String> ipv6AddressList) {
        this.pid = pid;
        this.ipv4AddressList = ipv4AddressList;
        this.ipv6AddressList = ipv6AddressList;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Optional<List<String>> getIpv4AddressList() {
        return Optional.ofNullable(ipv4AddressList);
    }

    public void setIpv4AddressList(List<String> ipv4AddressList) {
        this.ipv4AddressList = ipv4AddressList;
    }

    public Optional<List<String>> getIpv6AddressList() {
        return Optional.ofNullable(ipv6AddressList);
    }

    public void setIpv6AddressList(List<String> ipv6AddressList) {
        this.ipv6AddressList = ipv6AddressList;
    }
}
