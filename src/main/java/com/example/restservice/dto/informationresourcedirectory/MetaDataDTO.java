package com.example.restservice.dto.informationresourcedirectory;

import com.example.restservice.dto.costmap.CostTypeDTO;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

public class MetaDataDTO {

    @Field("cost-types")
    private Map<String, CostTypeDTO> costTypeDTOMap;

    @Field("default-alto-network-map")
    private String defaultNetworkMapName;

    @JsonCreator
    public MetaDataDTO(@JsonProperty(value = "cost-types", required = false) Map<String, CostTypeDTO> costTypeDTOMap,
                       @JsonProperty(value = "default-alto-network-map", required = false) String defaultNetworkMapName) {
        this.costTypeDTOMap = costTypeDTOMap;
            this.defaultNetworkMapName = defaultNetworkMapName;
    }

    public Map<String, CostTypeDTO> getCostTypeDTOMap() {
        return costTypeDTOMap;
    }

    public void setCostTypeDTOMap(Map<String, CostTypeDTO> costTypeDTOMap) {
        this.costTypeDTOMap = costTypeDTOMap;
    }

    public String getDefaultNetworkMapName() {
        return defaultNetworkMapName;
    }

    public void setDefaultNetworkMapName(String defaultNetworkMapName) {
        this.defaultNetworkMapName = defaultNetworkMapName;
    }
}
