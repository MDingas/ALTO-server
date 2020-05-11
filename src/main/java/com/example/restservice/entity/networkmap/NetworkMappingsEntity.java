package com.example.restservice.entity.networkmap;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document
public class NetworkMappingsEntity {

    private String versionTag;

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
