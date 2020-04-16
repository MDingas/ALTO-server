package com.example.restservice.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "CostMaps")
public class CostMapEntity implements ALTOResourceEntity {

    @Field("resource-id")
    private String resourceId;

    @Field("uri")
    private String uri;

    @Field("mappings")
    private List<CostMappingsEntity> costMappingsEntities;

    public CostMapEntity(String resourceId, String uri, List<CostMappingsEntity> costMappingsEntities) {
        this.resourceId = resourceId;
        this.uri = uri;
        this.costMappingsEntities = costMappingsEntities;
    }

    @Override
    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public List<CostMappingsEntity> getCostMappingsEntities() {
        return costMappingsEntities;
    }

    public void setCostMappingsEntities(List<CostMappingsEntity> costMappingsEntities) {
        this.costMappingsEntities = costMappingsEntities;
    }
}
