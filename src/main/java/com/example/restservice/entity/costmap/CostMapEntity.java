package com.example.restservice.entity.costmap;

import com.example.restservice.entity.ALTOResourceEntity;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "CostMaps")
public class CostMapEntity implements ALTOResourceEntity {

    private MetaInfoEntity metaInfoEntity;

    private List<CostMappingsEntity> mappingEntities;

    public CostMapEntity(MetaInfoEntity metaInfoEntity,
                         List<CostMappingsEntity> mappingEntities) {
        this.metaInfoEntity = metaInfoEntity;
        this.mappingEntities = mappingEntities;
    }

    public MetaInfoEntity getMetaInfoEntity() {
        return metaInfoEntity;
    }

    public void setMetaInfoEntity(MetaInfoEntity metaInfoEntity) {
        this.metaInfoEntity = metaInfoEntity;
    }

    public List<CostMappingsEntity> getMappingEntities() {
        return mappingEntities;
    }

    public void setMappingEntities(List<CostMappingsEntity> mappingEntities) {
        this.mappingEntities = mappingEntities;
    }

    @Override
    public String getId() {
        return metaInfoEntity.getResourceId();
    }
}
