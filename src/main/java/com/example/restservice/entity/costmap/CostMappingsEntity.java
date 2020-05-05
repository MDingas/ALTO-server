package com.example.restservice.entity.costmap;

import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

public class CostMappingsEntity {

    @Field("version-tag")
    private String versionTag;

    @Field("from-src-costs")
    private List<FromSrcCostsEntity> fromSrcCostsEntities;

    public CostMappingsEntity(String versionTag,
                              List<FromSrcCostsEntity> fromSrcCostsEntities) {
        this.versionTag = versionTag;
        this.fromSrcCostsEntities = fromSrcCostsEntities;
    }

    public String getVersionTag() {
        return versionTag;
    }

    public void setVersionTag(String versionTag) {
        this.versionTag = versionTag;
    }

    public List<FromSrcCostsEntity> getFromSrcCostsEntities() {
        return fromSrcCostsEntities;
    }

    public void setFromSrcCostsEntities(List<FromSrcCostsEntity> fromSrcCostsEntities) {
        this.fromSrcCostsEntities = fromSrcCostsEntities;
    }
}
