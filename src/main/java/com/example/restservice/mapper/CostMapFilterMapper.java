package com.example.restservice.mapper;

import com.example.restservice.dto.costmap.CostMapFilterDTO;
import com.example.restservice.dto.costmap.SrcDstMappingsDTO;
import com.example.restservice.repository.costmap.CostMapProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.example.restservice.repository.costmap.CostMapProjection.CostMapProjectionBuilder;

@Component
public class CostMapFilterMapper implements ALTOFilterMapper<CostMapFilterDTO, CostMapProjection> {
    private CostMapProjectionBuilder costMapProjectionBuilder;

    @Autowired
    public CostMapFilterMapper(CostMapProjectionBuilder costMapProjectionBuilder) {
        this.costMapProjectionBuilder = costMapProjectionBuilder;

    }

    @Override
    public CostMapProjection mapFrom(CostMapFilterDTO costMapFilter) {
        String costMode = costMapFilter.getCostTypeDTO().getCostMode().getValue();
        String costMetric = costMapFilter.getCostTypeDTO().getCostMetric().getValue();

        costMapProjectionBuilder.setCostMode(costMode);
        costMapProjectionBuilder.setCostMetric(costMetric);

        Optional<SrcDstMappingsDTO> optionalSrcDstMappings = costMapFilter.getSrcDstMappings();

        if (optionalSrcDstMappings.isPresent()) {
            SrcDstMappingsDTO srcDstMappings = optionalSrcDstMappings.get();

            List<String> srcPIDs = srcDstMappings.getSrcPIDs();

            if (!srcPIDs.isEmpty()) {
                costMapProjectionBuilder.setSrcPIDs(srcPIDs);
            }

            List<String> dstPIDs = srcDstMappings.getDstPIDs();

            if (!dstPIDs.isEmpty()) {
                costMapProjectionBuilder.setDstPIDs(dstPIDs);
            }
        }

        CostMapProjection costMapProjection = costMapProjectionBuilder.build();

        costMapProjectionBuilder.clear();

        return costMapProjection;
    }
}
