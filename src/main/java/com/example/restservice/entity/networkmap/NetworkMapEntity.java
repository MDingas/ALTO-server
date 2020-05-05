package com.example.restservice.entity.networkmap;

import com.example.restservice.entity.ALTOResourceEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "NetworkMaps")
public class NetworkMapEntity implements ALTOResourceEntity {

    @Field("resource-id")
    private String id;

    @Field("mappings")
    private List<NetworkMappingsEntity> networkMappingsEntities;

    public NetworkMapEntity(String id, List<NetworkMappingsEntity> networkMappingsEntities) {
        this.id = id;
        this.networkMappingsEntities = networkMappingsEntities;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<NetworkMappingsEntity> getNetworkMappingsEntities() {
        return networkMappingsEntities;
    }

    public void setNetworkMappingsEntities(List<NetworkMappingsEntity> networkMappingsEntities) {
        this.networkMappingsEntities = networkMappingsEntities;
    }
}
