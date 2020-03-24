package com.example.restservice.dto.costmap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Document(collection = "CostMaps")
public class CostMapDTO {

    @JsonIgnore
    @Id
    private String id;

    @NotNull
    @Indexed(unique = true)
    @Field("meta")
    private MetaDataDTO metaDataDTO;

    @NotNull
    @Field("cost-map")
    private Map<String, Map<String, Integer>> costMappings;

    @JsonCreator
    public CostMapDTO(@JsonProperty(value = "meta", required = true) MetaDataDTO metaDataDTO,
                      @JsonProperty(value = "cost-map", required = true) Map<String, Map<String, Integer>> costMappings) {
        this.metaDataDTO = metaDataDTO;
        this.costMappings = costMappings;
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

    public Map<String, Map<String, Integer>> getCostMappings() {
        return costMappings;
    }

    public void setCostMappings(Map<String, Map<String, Integer>> costMappings) {
        this.costMappings = costMappings;
    }
}
