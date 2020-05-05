package com.example.restservice.entity.informationresourcedirectory;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.util.List;

@Document(collection = "InformationResourceDirectories")
public class InformationResourceDirectoryEntity {

    @NotNull
    @Field("cost-types")
    private List<CostTypeIRDEntry> costTypeIRDEntries;

    @NotNull
    @Field("default-alto-network-map")
    private String defaultNetworkMapName;

    @NotNull
    @Field("resources")
    private List<ResourceIRDEntry> resourceIRDEntries;

    public InformationResourceDirectoryEntity(List<CostTypeIRDEntry> costTypeIRDEntries, String defaultNetworkMapName, List<ResourceIRDEntry> resourceIRDEntries) {
        this.costTypeIRDEntries = costTypeIRDEntries;
        this.defaultNetworkMapName = defaultNetworkMapName;
        this.resourceIRDEntries = resourceIRDEntries;
    }

    public List<CostTypeIRDEntry> getCostTypeIRDEntries() {
        return costTypeIRDEntries;
    }

    public void setCostTypeIRDEntries(List<CostTypeIRDEntry> costTypeIRDEntries) {
        this.costTypeIRDEntries = costTypeIRDEntries;
    }

    public String getDefaultNetworkMapName() {
        return defaultNetworkMapName;
    }

    public void setDefaultNetworkMapName(String defaultNetworkMapName) {
        this.defaultNetworkMapName = defaultNetworkMapName;
    }

    public List<ResourceIRDEntry> getResourceIRDEntries() {
        return resourceIRDEntries;
    }

    public void setResourceIRDEntries(List<ResourceIRDEntry> resourceIRDEntries) {
        this.resourceIRDEntries = resourceIRDEntries;
    }
}
