package com.example.restservice.mapper;

import com.example.restservice.dto.VersionTagDTO;
import com.example.restservice.dto.costmap.*;
import com.example.restservice.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
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

    private CostMapDTO buildCostMapDTO(String resourceId, CostMappingsEntity costMappingsEntity) {
        String versionTag = costMappingsEntity.getVersionTag();
        String costMode = costMappingsEntity.getCostMode();
        String costMetric = costMappingsEntity.getCostMetric();

        CostTypeDTO costTypeDTO = new CostTypeDTO(CostModeDTO.fromString(costMode), CostMetricDTO.fromString(costMetric), null);
        VersionTagDTO versionTagDTO = new VersionTagDTO(resourceId, versionTag);
        MetaDataDTO metaDataDTO = new MetaDataDTO(versionTagDTO, null, costTypeDTO);
        Map<String, Map<String, Integer>> costMappings = buildSrcToDstCostMap(costMappingsEntity.getFromSrcCostsEntities());

        return new CostMapDTO(metaDataDTO, costMappings);
    }

    private List<CostMapDTO> buildCostMapDTOs(String resourceId, List<CostMappingsEntity> costMappingsEntities) {
        List<CostMapDTO> costMapDTOList = new ArrayList<>();

        for (CostMappingsEntity costMappingsEntity : costMappingsEntities) {
            costMapDTOList.add(buildCostMapDTO(resourceId, costMappingsEntity));
        }

        return costMapDTOList;
    }


    private Optional<CostMappingsEntity> findCostMappingsEntityVersion(List<CostMappingsEntity> costMappingsEntities, String versionTag) {
        return costMappingsEntities
                .stream()
                .filter(resource -> resource.getVersionTag().equals(versionTag))
                .findFirst();
    }

    public List<CostMapDTO> mapFromAllVersions(CostMapEntity costMapEntity) {
        List<CostMappingsEntity> costMappingsEntities = costMapEntity.getCostMappingsEntities();

        String resourceId = costMapEntity.getResourceId();
        String uri = costMapEntity.getUri();

        return buildCostMapDTOs(resourceId, costMappingsEntities);
    }

    //public Optional<CostMapDTO> mapFromVersion(CostMapEntity costMapEntity, String version) {
    //    String resourceId = costMapEntity.getResourceId();
    //    String uri = costMapEntity.getUri();

    //    List<CostMappingsEntity> costMappingsEntities = costMapEntity.getCostMappingsEntities();
    //    Optional<CostMappingsEntity> optionalCostMappingsEntity = findCostMappingsEntityVersion(costMappingsEntities, version);

    //    if (!optionalCostMappingsEntity.isPresent()) {
    //        return Optional.empty();
    //    } else {
    //        CostMappingsEntity costMappingsEntity = optionalCostMappingsEntity.get();
    //        CostMapDTO costMapDTO = buildCostMapDTO(resourceId, costMappingsEntity);
    //        return Optional.of(costMapDTO);
    //    }
    //}

    @Override
    public List<CostMapDTO> mapAllVersionsFrom(CostMapEntity costMapEntity) {
        String resourceId = costMapEntity.getResourceId();
        String uri = costMapEntity.getUri();

        List<CostMappingsEntity> costMappingsEntities = costMapEntity.getCostMappingsEntities();

        return buildCostMapDTOs(resourceId, costMappingsEntities);
    }

    @Override
    public Optional<CostMapDTO> mapFirstVersion(CostMapEntity costMapEntity) {
        String resourceId = costMapEntity.getResourceId();
        String uri = costMapEntity.getUri();

        List<CostMappingsEntity> costMappingsEntities = costMapEntity.getCostMappingsEntities();

        if (costMappingsEntities.isEmpty()) {
            return Optional.empty();
        } else {
            CostMappingsEntity firstCostMappingsEntity = costMappingsEntities.get(0);
            CostMapDTO costMapDTO = buildCostMapDTO(resourceId, firstCostMappingsEntity);
            return Optional.of(costMapDTO);
        }
    }
}
