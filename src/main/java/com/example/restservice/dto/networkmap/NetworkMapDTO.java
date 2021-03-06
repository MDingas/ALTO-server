package com.example.restservice.dto.networkmap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Document(collection = "NetworkMaps")
public class NetworkMapDTO {

    @JsonIgnore
    @Id
    private String id;

    @NotNull
    @Indexed(unique = true)
    @Field("meta")
    private MetaDataDTO metaDataDTO;

    @NotNull
    @Field("network-map")
    private Map<String, IpAggregationsDTO> ipAggregationsDTOMap;

    @JsonCreator
    public NetworkMapDTO(@JsonProperty(value = "meta", required = true) MetaDataDTO metaDataDTO,
                         @JsonProperty(value = "network-map", required = true) Map<String, IpAggregationsDTO> ipAggregationsDTOMap) {
        this.metaDataDTO = metaDataDTO;
        this.ipAggregationsDTOMap = ipAggregationsDTOMap;
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

    public Map<String, IpAggregationsDTO> getIpAggregationsDTOMap() {
        return ipAggregationsDTOMap;
    }

    public void setIpAggregationsDTOMap(Map<String, IpAggregationsDTO> ipAggregationsDTOMap) {
        this.ipAggregationsDTOMap = ipAggregationsDTOMap;
    }
}
