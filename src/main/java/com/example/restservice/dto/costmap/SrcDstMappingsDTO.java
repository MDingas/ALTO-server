package com.example.restservice.dto.costmap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.util.List;

public class SrcDstMappingsDTO {

    @NotNull
    @Field("srcs")
    private List<String> srcPIDs;

    @NotNull
    @Field("dsts")
    private List<String> dstPIDs;

    @JsonCreator
    public SrcDstMappingsDTO(@JsonProperty(value = "srcs", required = true) List<String> srcPIDs,
                             @JsonProperty(value = "dsts", required = true) List<String> dstPIDs) {
        this.srcPIDs = srcPIDs;
        this.dstPIDs = dstPIDs;
    }

    public List<String> getSrcPIDs() {
        return srcPIDs;
    }

    public void setSrcPIDs(List<String> srcPIDs) {
        this.srcPIDs = srcPIDs;
    }

    public List<String> getDstPIDs() {
        return dstPIDs;
    }

    public void setDstPIDs(List<String> dstPIDs) {
        this.dstPIDs = dstPIDs;
    }
}
