package com.example.restservice.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document
public class NetworkMappingsEntity {

    @Field("version-tag")
    private String versionTag;

    @Field("address-aggregations")
    private List<AddressAggregationEntity> addressAggregationEntities;

    public NetworkMappingsEntity(String versionTag, List<AddressAggregationEntity> addressAggregationEntities) {
        this.versionTag = versionTag;
        this.addressAggregationEntities = addressAggregationEntities;
    }

    public String getVersionTag() {
        return versionTag;
    }

    public void setVersionTag(String versionTag) {
        this.versionTag = versionTag;
    }

    public List<AddressAggregationEntity> getAddressAggregationEntities() {
        return addressAggregationEntities;
    }

    public void setAddressAggregationEntities(List<AddressAggregationEntity> addressAggregationEntities) {
        this.addressAggregationEntities = addressAggregationEntities;
    }
}
