package com.example.restservice.mapper;

import com.example.restservice.dto.CostMetricDTO;
import com.example.restservice.dto.CostModeDTO;
import com.example.restservice.dto.CostTypeDTO;
import com.example.restservice.dto.VersionTagDTO;
import com.example.restservice.dto.costmap.*;
import com.example.restservice.entity.costmap.*;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CostMapMapper implements ALTOResourceMapper<CostMapEntity, CostMapDTO> {

    private Map<String, Map<String, Integer>> buildSrcToDstCostMap(List<FromSrcCostsEntity> fromSrcCostsEntities) {
        Map<String, Map<String, Integer>> srcToDstCostMap = new HashMap<>();

        for (FromSrcCostsEntity fromSrcCostsEntity : fromSrcCostsEntities) {

            Map<String, Integer> dstCosts = new HashMap<>();

            String srcNode = fromSrcCostsEntity.getSrcNode();

            List<DstCostsEntity> dstCostsEntities = fromSrcCostsEntity.getDstCostEntities();

            for (DstCostsEntity dstCostsEntity : dstCostsEntities) {
                String dstNode = dstCostsEntity.getDstNode();
                Integer costValue = dstCostsEntity.getCostValue();
                dstCosts.put(dstNode, costValue);
            }

            srcToDstCostMap.put(srcNode, dstCosts);
        }

        return srcToDstCostMap;
    }

    private CostMapDTO buildCostMapDTO(String resourceId,
                                       String costMode,
                                       String costMetric,
                                       CostMappingsEntity costMappingsEntity) {
        String versionTag = costMappingsEntity.getVersionTag();

        CostTypeDTO costTypeDTO = new CostTypeDTO(CostModeDTO.fromString(costMode), CostMetricDTO.fromString(costMetric), null);
        VersionTagDTO versionTagDTO = new VersionTagDTO(resourceId, versionTag);
        MetaDataDTO metaDataDTO = new MetaDataDTO(versionTagDTO, null, costTypeDTO);
        Map<String, Map<String, Integer>> costMappings = buildSrcToDstCostMap(costMappingsEntity.getFromSrcCostEntities());

        return new CostMapDTO(metaDataDTO, costMappings);
    }

    private List<CostMapDTO> buildCostMapDTOs(String resourceId,
                                              String costMode,
                                              String costMetric,
                                              List<CostMappingsEntity> costMappingsEntities) {
        List<CostMapDTO> costMapDTOList = new ArrayList<>();

        for (CostMappingsEntity costMappingsEntity : costMappingsEntities) {
            costMapDTOList.add(buildCostMapDTO(resourceId, costMode, costMetric, costMappingsEntity));
        }

        return costMapDTOList;
    }

    @Override
    public List<CostMapDTO> mapAllVersions(CostMapEntity costMapEntity) {
        MetaInfoEntity metaInfoEntity = costMapEntity.getMetaInfoEntity();

        String resourceId = metaInfoEntity.getResourceId();
        String costMode = metaInfoEntity.getCostMode();
        String costMetric = metaInfoEntity.getCostMetric();

        List<CostMappingsEntity> costMappingsEntities = costMapEntity.getMappingEntities();
        return buildCostMapDTOs(resourceId, costMode, costMetric, costMappingsEntities);
    }

    @Override
    public CostMapDTO mapVersionAtPosition(CostMapEntity costMapEntity, int index) {
        MetaInfoEntity metaInfoEntity = costMapEntity.getMetaInfoEntity();

        String resourceId = metaInfoEntity.getResourceId();
        String costMode = metaInfoEntity.getCostMode();
        String costMetric = metaInfoEntity.getCostMetric();

        List<CostMappingsEntity> costMappingsEntities = costMapEntity.getMappingEntities();
        CostMappingsEntity firstCostMappingsEntity = costMappingsEntities.get(0);
        return buildCostMapDTO(resourceId, costMode, costMetric, firstCostMappingsEntity);
    }
}
