package com.example.restservice.entity.costmap;

import java.util.List;

public class CostMappingsEntity {

    private String versionTag;

    private List<FromSrcCostsEntity> fromSrcCostEntities;

    public CostMappingsEntity(String versionTag,
                              List<FromSrcCostsEntity> fromSrcCostEntities) {
        this.versionTag = versionTag;
        this.fromSrcCostEntities = fromSrcCostEntities;
    }

    public String getVersionTag() {
        return versionTag;
    }

    public void setVersionTag(String versionTag) {
        this.versionTag = versionTag;
    }

    public List<FromSrcCostsEntity> getFromSrcCostEntities() {
        return fromSrcCostEntities;
    }

    public void setFromSrcCostEntities(List<FromSrcCostsEntity> fromSrcCostEntities) {
        this.fromSrcCostEntities = fromSrcCostEntities;
    }
}
