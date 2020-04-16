package com.example.restservice.dto.costmap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;

public class CostMapFilterDTO {

    @NotNull
    @Field("cost-type")
    private CostTypeDTO costTypeDTO;

    @NotNull
    @Field("pids")
    private SrcDstMappingsDTO srcDstMappings;

    @JsonCreator
    public CostMapFilterDTO(@JsonProperty(value = "cost-type", required = true) CostTypeDTO costTypeDTO,
                            @JsonProperty(value = "pids", required = true) SrcDstMappingsDTO srcDstMappingsDTO) {
        this.costTypeDTO = costTypeDTO;
        this.srcDstMappings = srcDstMappingsDTO;
    }

    public CostTypeDTO getCostTypeDTO() {
        return costTypeDTO;
    }

    public void setCostTypeDTO(CostTypeDTO costTypeDTO) {
        this.costTypeDTO = costTypeDTO;
    }

    public SrcDstMappingsDTO getSrcDstMappings() {
        return srcDstMappings;
    }

    public void setSrcDstMappings(SrcDstMappingsDTO srcDstMappings) {
        this.srcDstMappings = srcDstMappings;
    }
}
