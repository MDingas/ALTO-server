package com.example.restservice.dto.costmap;

import com.example.restservice.dto.CostTypeDTO;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public class CostMapFilterDTO {

    @NotNull
    @Field("cost-type")
    private CostTypeDTO costTypeDTO;

    @NotNull
    @Field("pids")
    private SrcDstMappingsDTO srcDstMappings;

    @JsonCreator
    public CostMapFilterDTO(@JsonProperty(value = "cost-type", required = true) CostTypeDTO costTypeDTO,
                            @JsonProperty(value = "pids", required = false) SrcDstMappingsDTO srcDstMappingsDTO) {
        this.costTypeDTO = costTypeDTO;
        this.srcDstMappings = srcDstMappingsDTO;
    }

    public CostTypeDTO getCostTypeDTO() {
        return costTypeDTO;
    }

    public void setCostTypeDTO(CostTypeDTO costTypeDTO) {
        this.costTypeDTO = costTypeDTO;
    }

    public Optional<SrcDstMappingsDTO> getSrcDstMappings() {
        return Optional.ofNullable(srcDstMappings);
    }

    public void setSrcDstMappings(SrcDstMappingsDTO srcDstMappings) {
        this.srcDstMappings = srcDstMappings;
    }
}
