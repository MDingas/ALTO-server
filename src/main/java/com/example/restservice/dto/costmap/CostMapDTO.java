package com.example.restservice.dto.costmap;

import com.example.restservice.dto.networkmap.MetaDataDTO;
import com.example.restservice.dto.networkmap.VersionTagDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Document(collection = "CostMaps")
public class CostMapDTO {

    @JsonIgnore
    @Id
    private String id;

    @NotNull
    @Indexed(unique = true)
    @JsonProperty("meta")
    @Field("meta")
    private MetaDataDTO metaDataDTO;

    @JsonProperty("dependent-vtags")
    @Field("dependent-vtags")
    private List<VersionTagDTO> dependentVersionTagDTOs;

    @NotNull
    @JsonProperty("cost-type")
    @Field("cost-type")
    private CostTypeDTO costTypeDTO;

    @NotNull
    @JsonProperty("cost-map")
    @Field("cost-map")
    private Map<String, Map<String, Integer>> costMappings;

    public CostMapDTO() {

    }

    public CostMapDTO(@NotNull MetaDataDTO metaDataDTO, List<VersionTagDTO> dependentVersionTagDTOs, @NotNull CostTypeDTO costTypeDTO, @NotNull Map<String, Map<String, Integer>> costMappings) {
        this.metaDataDTO = metaDataDTO;
        this.dependentVersionTagDTOs = dependentVersionTagDTOs;
        this.costTypeDTO = costTypeDTO;
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

    public List<VersionTagDTO> getDependentVersionTagDTOs() {
        return dependentVersionTagDTOs;
    }

    public void setDependentVersionTagDTOs(List<VersionTagDTO> dependentVersionTagDTOs) {
        this.dependentVersionTagDTOs = dependentVersionTagDTOs;
    }

    public CostTypeDTO getCostTypeDTO() {
        return costTypeDTO;
    }

    public void setCostTypeDTO(CostTypeDTO costTypeDTO) {
        this.costTypeDTO = costTypeDTO;
    }

    public Map<String, Map<String, Integer>> getCostMappings() {
        return costMappings;
    }

    public void setCostMappings(Map<String, Map<String, Integer>> costMappings) {
        this.costMappings = costMappings;
    }
}
