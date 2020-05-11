package com.example.restservice.service.costmap;

import com.example.restservice.dto.costmap.CostMapDTO;
import com.example.restservice.dto.costmap.CostMapFilterDTO;
import com.example.restservice.dto.CostTypeDTO;
import com.example.restservice.dto.costmap.SrcDstMappingsDTO;
import com.example.restservice.entity.costmap.CostMapEntity;
import com.example.restservice.mapper.CostMapFilterMapper;
import com.example.restservice.mapper.CostMapMapper;
import com.example.restservice.repository.costmap.CostMapProjection;
import com.example.restservice.repository.costmap.CostMapRepository;
import com.example.restservice.service.ALTOGenericResourceRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CostMapServiceImpl extends ALTOGenericResourceRepoService<CostMapEntity,
                                                                       CostMapProjection,
                                                                       CostMapDTO,
                                                                       CostMapRepository,
                                                                       CostMapMapper>
                                implements CostMapService {

    private CostMapFilterMapper costMapFilterMapper;

    @Autowired
    public CostMapServiceImpl(CostMapRepository costMapRepository,
                              CostMapMapper costMapMapper,
                              CostMapFilterMapper costMapFilterMapper) {
        super(costMapRepository, costMapMapper);
        this.costMapFilterMapper = costMapFilterMapper;
    }

    @Override
    public Optional<CostMapDTO> getResourceWithFilter(String resourceId, String resourceVersion, CostMapFilterDTO costMapFilterDTO) {
        CostMapProjection costMapProjection = costMapFilterMapper.mapFrom(costMapFilterDTO);
        Optional<CostMapEntity> optionalCostMapEntity = resourceRepository.findVersionOfResourceWithProjection(resourceId, resourceVersion, costMapProjection);
        return optionalCostMapEntity.map(costMapEntity -> resourceMapper.mapVersionAtPosition(costMapEntity, 0));
    }

    @Override
    public Optional<CostMapDTO> getLatestResourceWithFilter(String resourceId, CostMapFilterDTO costMapFilterDTO) {
        CostMapProjection costMapProjection = costMapFilterMapper.mapFrom(costMapFilterDTO);
        Optional<CostMapEntity> optionalCostMapEntity = resourceRepository.findLatestVersionOfResourceWithProjection(resourceId, costMapProjection);
        return optionalCostMapEntity.map(costMapEntity -> resourceMapper.mapVersionAtPosition(costMapEntity, 0));
    }
}
