package com.example.restservice.dto.costmap;

import com.example.restservice.dto.VersionTagDTO;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MetaDataDTO {

    @NotNull
    @Field("vtag")
    private VersionTagDTO versionTagDTO;

    @Field("dependent-vtags")
    private List<VersionTagDTO> dependentVtagList;

    @Field("cost-type")
    private CostTypeDTO costTypeDTO;

    @JsonCreator
    public MetaDataDTO(@JsonProperty(value = "vtag", required = true) VersionTagDTO versionTagDTO,
                       @JsonProperty(value = "dependent-vtags", required = false) List<VersionTagDTO> dependentVtagList,
                       @JsonProperty(value = "cost-type", required = true) CostTypeDTO costTypeDTO) {
        this.versionTagDTO = versionTagDTO;
        this.dependentVtagList = dependentVtagList;
        this.costTypeDTO = costTypeDTO;
    }

    public VersionTagDTO getVersionTagDTO() {
        return this.versionTagDTO;
    }

    public void setVersionTagDTO(VersionTagDTO versionTagDTO) {
        this.versionTagDTO = versionTagDTO;
    }

    public List<VersionTagDTO> getDependentVtagList() {
        return dependentVtagList;
    }

    public void setDependentVtagList(List<VersionTagDTO> dependentVtagList) {
        this.dependentVtagList = dependentVtagList;
    }

    public CostTypeDTO getCostTypeDTO() {
        return costTypeDTO;
    }

    public void setCostTypeDTO(CostTypeDTO costTypeDTO) {
        this.costTypeDTO = costTypeDTO;
    }
}
