package com.example.restservice.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "NetworkMaps")
public class NetworkMapEntity implements ALTOResourceEntity {

    @Field("resource-id")
    private String resourceId;

    @Field("uri")
    private String uri;

    @Field("mappings")
    private List<NetworkMappingsEntity> networkMappingsEntities;

    public NetworkMapEntity(String resourceId, String uri, List<NetworkMappingsEntity> networkMappingsEntities) {
        this.resourceId = resourceId;
        this.uri = uri;
        this.networkMappingsEntities = networkMappingsEntities;
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

    public List<NetworkMappingsEntity> getNetworkMappingsEntities() {
        return networkMappingsEntities;
    }

    public void setNetworkMappingsEntities(List<NetworkMappingsEntity> networkMappingsEntities) {
        this.networkMappingsEntities = networkMappingsEntities;
    }
}
