package com.example.restservice.service.costmap;

import com.example.restservice.dto.costmap.CostMapDTO;
import com.example.restservice.dto.costmap.CostMapFilterDTO;
import com.example.restservice.dto.CostTypeDTO;
import com.example.restservice.dto.costmap.SrcDstMappingsDTO;
import com.example.restservice.entity.costmap.CostMapEntity;
import com.example.restservice.mapper.CostMapMapper;
import com.example.restservice.repository.costmap.CostMapRepository;
import com.example.restservice.service.ALTOResourceGenericRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CostMapServiceImpl extends ALTOResourceGenericRepoService<CostMapEntity,
                                                                       CostMapDTO,
                                                                       CostMapRepository,
                                                                       CostMapMapper>
                                implements CostMapService {

    @Autowired
    public CostMapServiceImpl(CostMapRepository costMapRepository, CostMapMapper costMapMapper) {
        super(costMapRepository, costMapMapper);
    }

    @Override
    public Optional<CostMapDTO> getResourceWithFilter(String resourceId, String resourceVersion, CostMapFilterDTO costMapFilterDTO) {
        SrcDstMappingsDTO srcDstMappingsDTO = costMapFilterDTO.getSrcDstMappings();
        List<String> srcPIDs = srcDstMappingsDTO.getSrcPIDs();
        List<String> dstPIDs = srcDstMappingsDTO.getDstPIDs();

        CostTypeDTO costTypeDTO = costMapFilterDTO.getCostTypeDTO();
        String costMode = costTypeDTO.getCostMode().getValue();
        String costMetric = costTypeDTO.getCostMetric().getValue();

        Optional<CostMapEntity> optionalCostMapEntity = resourceRepository.findVersionOfResource(resourceId, resourceVersion, srcPIDs, dstPIDs, costMode, costMetric);

        return optionalCostMapEntity.map(costMapEntity -> resourceMapper.mapVersionAtPosition(costMapEntity, 0));
    }

    @Override
    public Optional<CostMapDTO> getLatestResourceWithFilter(String resourceId, CostMapFilterDTO costMapFilterDTO) {
        SrcDstMappingsDTO srcDstMappingsDTO = costMapFilterDTO.getSrcDstMappings();
        List<String> srcPIDs = srcDstMappingsDTO.getSrcPIDs();
        List<String> dstPIDs = srcDstMappingsDTO.getDstPIDs();

        CostTypeDTO costTypeDTO = costMapFilterDTO.getCostTypeDTO();
        String costMode = costTypeDTO.getCostMode().getValue();
        String costMetric = costTypeDTO.getCostMetric().getValue();

        Optional<CostMapEntity> optionalCostMapEntity = resourceRepository.findLatestVersionOfResource(resourceId, srcPIDs, dstPIDs, costMode, costMetric);

        return optionalCostMapEntity.map(costMapEntity -> resourceMapper.mapVersionAtPosition(costMapEntity, 0));
    }
}
