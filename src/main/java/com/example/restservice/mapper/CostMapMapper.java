package com.example.restservice.mapper;

import com.example.restservice.dto.CostMetricDTO;
import com.example.restservice.dto.CostModeDTO;
import com.example.restservice.dto.CostTypeDTO;
import com.example.restservice.dto.VersionTagDTO;
import com.example.restservice.dto.costmap.*;
import com.example.restservice.entity.costmap.CostMapEntity;
import com.example.restservice.entity.costmap.CostMappingsEntity;
import com.example.restservice.entity.costmap.DstCostsEntity;
import com.example.restservice.entity.costmap.FromSrcCostsEntity;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CostMapMapper implements ALTOMapper<CostMapEntity, CostMapDTO> {

    private Map<String, Map<String, Integer>> buildSrcToDstCostMap(List<FromSrcCostsEntity> fromSrcCostsEntities) {
        Map<String, Map<String, Integer>> srcToDstCostMap = new HashMap<>();

        for (FromSrcCostsEntity fromSrcCostsEntity : fromSrcCostsEntities) {

            Map<String, Integer> dstCosts = new HashMap<>();

            String srcNode = fromSrcCostsEntity.getSrcNode();

            List<DstCostsEntity> dstCostsEntities = fromSrcCostsEntity.getDstCostsEntities();

            for (DstCostsEntity dstCostsEntity : dstCostsEntities) {
                String dstNode = dstCostsEntity.getDstNode();
                Integer costValue = dstCostsEntity.getValue();
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
        Map<String, Map<String, Integer>> costMappings = buildSrcToDstCostMap(costMappingsEntity.getFromSrcCostsEntities());

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
        String resourceId = costMapEntity.getId();
        String costMode = costMapEntity.getCostMode();
        String costMetric = costMapEntity.getCostMetric();

        List<CostMappingsEntity> costMappingsEntities = costMapEntity.getCostMappingsEntities();
        return buildCostMapDTOs(resourceId, costMode, costMetric, costMappingsEntities);
    }

    @Override
    public CostMapDTO mapVersionAtPosition(CostMapEntity costMapEntity, int index) {
        String resourceId = costMapEntity.getId();
        String costMode = costMapEntity.getCostMode();
        String costMetric = costMapEntity.getCostMetric();

        System.out.println(resourceId);
        System.out.println(costMode);
        System.out.println(costMetric);
        System.out.println(costMapEntity.getCostMappingsEntities());

        List<CostMappingsEntity> costMappingsEntities = costMapEntity.getCostMappingsEntities();
        CostMappingsEntity firstCostMappingsEntity = costMappingsEntities.get(0);
        return buildCostMapDTO(resourceId, costMode, costMetric, firstCostMappingsEntity);
    }
}
