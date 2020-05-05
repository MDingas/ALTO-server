package com.example.restservice.entity.calendarcostmap;

import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

public class CalendarCostMappingsEntity {

    @Field("version-tag")
    private String versionTag;

    @Field("from-src-costs")
    private List<FromSrcListCostsEntity> fromSrcCostsEntities;

    public CalendarCostMappingsEntity(String versionTag,
                                      List<FromSrcListCostsEntity> fromSrcCostsEntities) {
        this.versionTag = versionTag;
        this.fromSrcCostsEntities = fromSrcCostsEntities;
    }

    public String getVersionTag() {
        return versionTag;
    }

    public void setVersionTag(String versionTag) {
        this.versionTag = versionTag;
    }

    public List<FromSrcListCostsEntity> getFromSrcCostsEntities() {
        return fromSrcCostsEntities;
    }

    public void setFromSrcCostsEntities(List<FromSrcListCostsEntity> fromSrcCostsEntities) {
        this.fromSrcCostsEntities = fromSrcCostsEntities;
    }
}
