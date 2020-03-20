package com.example.restservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Document(collection = "NetworkMaps")
public class NetworkMapDTO {
    @Id
    @JsonIgnore
    private String id;

    @NotNull
    @JsonProperty("meta")
    private MetaDataDTO metaDataDTO;

    @NotNull
    @JsonProperty("network-map")
    private Map<String, IpAggregationsDTO> networkAggregationsDTO;

    public NetworkMapDTO() {

    }

    public NetworkMapDTO(@NotNull MetaDataDTO metaDataDTO, @NotNull Map<String, IpAggregationsDTO> networkAggregationsDTO) {
        this.metaDataDTO = metaDataDTO;
        this.networkAggregationsDTO = networkAggregationsDTO;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MetaDataDTO getMetaDataDTO() {
        return metaDataDTO;
    }

    public void setMetaDataDTO(MetaDataDTO metaDataDTO) {
        this.metaDataDTO = metaDataDTO;
    }

    public Map<String, IpAggregationsDTO> getNetworkAggregationsDTO() {
        return networkAggregationsDTO;
    }

    public void setNetworkAggregationsDTO(Map<String, IpAggregationsDTO> networkAggregationsDTO) {
        this.networkAggregationsDTO = networkAggregationsDTO;
    }
}
