package com.example.restservice.entity;

import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

public class CostMappingsEntity {

    @Field("version-tag")
    private String versionTag;

    @Field("cost-mode")
    private String costMode;

    @Field("cost-metric")
    private String costMetric;

    @Field("from-src-costs")
    private List<FromSrcCostsEntity> fromSrcCostsEntities;

    public CostMappingsEntity(String versionTag, String costMode, String costMetric, List<FromSrcCostsEntity> fromSrcCostsEntities) {
        this.versionTag = versionTag;
        this.costMode = costMode;
        this.costMetric = costMetric;
        this.fromSrcCostsEntities = fromSrcCostsEntities;
    }

    public String getVersionTag() {
        return versionTag;
    }

    public void setVersionTag(String versionTag) {
        this.versionTag = versionTag;
    }

    public String getCostMode() {
        return costMode;
    }

    public void setCostMode(String costMode) {
        this.costMode = costMode;
    }

    public String getCostMetric() {
        return costMetric;
    }

    public void setCostMetric(String costMetric) {
        this.costMetric = costMetric;
    }

    public List<FromSrcCostsEntity> getFromSrcCostsEntities() {
        return fromSrcCostsEntities;
    }

    public void setFromSrcCostsEntities(List<FromSrcCostsEntity> fromSrcCostsEntities) {
        this.fromSrcCostsEntities = fromSrcCostsEntities;
    }
}
