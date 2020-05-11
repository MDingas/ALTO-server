package com.example.restservice.entity.networkmap;

import com.example.restservice.entity.ALTOResourceEntity;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "NetworkMaps")
public class NetworkMapEntity implements ALTOResourceEntity {

    private MetaInfoEntity metaInfoEntity;

    private List<NetworkMappingsEntity> mappingEntities;

    public NetworkMapEntity(MetaInfoEntity metaInfoEntity, List<NetworkMappingsEntity> mappingEntities) {
        this.metaInfoEntity = metaInfoEntity;
        this.mappingEntities = mappingEntities;
    }

    public MetaInfoEntity getMetaInfoEntity() {
        return metaInfoEntity;
    }

    public void setMetaInfoEntity(MetaInfoEntity metaInfoEntity) {
        this.metaInfoEntity = metaInfoEntity;
    }

    public List<NetworkMappingsEntity> getMappingEntities() {
        return mappingEntities;
    }

    public void setMappingEntities(List<NetworkMappingsEntity> mappingEntities) {
        this.mappingEntities = mappingEntities;
    }

    @Override
    public String getId() {
        return metaInfoEntity.getResourceId();
    }
}
