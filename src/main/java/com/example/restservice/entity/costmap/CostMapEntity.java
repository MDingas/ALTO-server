package com.example.restservice.entity.costmap;

import com.example.restservice.entity.ALTOResourceEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "CostMaps")
public class CostMapEntity implements ALTOResourceEntity {

    @Field("resource-id")
    private String id;

    @Field("cost-mode")
    private String costMode;

    @Field("cost-metric")
    private String costMetric;

    @Field("mappings")
    private List<CostMappingsEntity> costMappingsEntities;

    public CostMapEntity(String id,
                         String costMode,
                         String costMetric,
                         List<CostMappingsEntity> costMappingsEntities) {
        this.id = id;
        this.costMode = costMode;
        this.costMetric = costMetric;
        this.costMappingsEntities = costMappingsEntities;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<CostMappingsEntity> getCostMappingsEntities() {
        return costMappingsEntities;
    }

    public void setCostMappingsEntities(List<CostMappingsEntity> costMappingsEntities) {
        this.costMappingsEntities = costMappingsEntities;
    }
}
