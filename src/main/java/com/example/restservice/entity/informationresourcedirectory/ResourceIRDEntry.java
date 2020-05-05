package com.example.restservice.entity.informationresourcedirectory;

import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public class ResourceIRDEntry {

    @NotNull
    @Field("name")
    private String name;

    @NotNull
    @Field("uri")
    private String uri;

    @NotNull
    @Field("media-type")
    private String mediaType;

    @Field("capabilities")
    private CapabilityEntity capabilityEntity;

    @Field("accepts")
    private String acceptedMediaType;

    @Field("uses")
    private List<String> referencedResources;

    public ResourceIRDEntry(String name, String uri, String mediaType, CapabilityEntity capabilityEntity, String acceptedMediaType, List<String> referencedResources) {
        this.name = name;
        this.uri = uri;
        this.mediaType = mediaType;
        this.capabilityEntity = capabilityEntity;
        this.acceptedMediaType = acceptedMediaType;
        this.referencedResources = referencedResources;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public Optional<CapabilityEntity> getCapabilityEntity() {
        return Optional.ofNullable(capabilityEntity);
    }

    public void setCapabilityEntity(CapabilityEntity capabilityEntity) {
        this.capabilityEntity = capabilityEntity;
    }

    public Optional<String> getAcceptedMediaType() {
        return Optional.ofNullable(acceptedMediaType);
    }

    public void setAcceptedMediaType(String acceptedMediaType) {
        this.acceptedMediaType = acceptedMediaType;
    }

    public Optional<List<String>> getReferencedResources() {
        return Optional.ofNullable(referencedResources);
    }

    public void setReferencedResources(List<String> referencedResources) {
        this.referencedResources = referencedResources;
    }
}
