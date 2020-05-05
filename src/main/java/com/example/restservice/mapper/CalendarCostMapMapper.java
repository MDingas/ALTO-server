package com.example.restservice.mapper;

import com.example.restservice.dto.VersionTagDTO;
import com.example.restservice.dto.calendarcostmap.CalendarAttributesDTO;
import com.example.restservice.dto.calendarcostmap.CalendarCostMapDTO;
import com.example.restservice.dto.calendarcostmap.MetaDataDTO;
import com.example.restservice.dto.CostMetricDTO;
import com.example.restservice.dto.CostModeDTO;
import com.example.restservice.dto.CostTypeDTO;
import com.example.restservice.entity.calendarcostmap.CalendarCostMapEntity;
import com.example.restservice.entity.calendarcostmap.CalendarCostMappingsEntity;
import com.example.restservice.entity.calendarcostmap.DstListCostEntity;
import com.example.restservice.entity.calendarcostmap.FromSrcListCostsEntity;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CalendarCostMapMapper implements ALTOMapper<CalendarCostMapEntity, CalendarCostMapDTO> {

    private Map<String, Map<String, List<Integer>>> buildSrcToDstCostsMap(List<FromSrcListCostsEntity> fromSrcCostsEntities) {
        Map<String, Map<String, List<Integer>>> srcToDstCostsMap = new HashMap<>();

        for (FromSrcListCostsEntity fromSrcCostsEntity : fromSrcCostsEntities) {
            Map<String, List<Integer>> dstsCosts = new HashMap<>();

            String srcNode = fromSrcCostsEntity.getSrcNode();

            List<DstListCostEntity> dstCostsEntities = fromSrcCostsEntity.getDstListCostEntities();

            for (DstListCostEntity dstListCostEntity : dstCostsEntities) {
                String dstNode = dstListCostEntity.getDstNode();
                List<Integer> costValues = dstListCostEntity.getValues();
                dstsCosts.put(dstNode, costValues);
            }

            srcToDstCostsMap.put(srcNode, dstsCosts);
        }

        return srcToDstCostsMap;
    }

    private CalendarCostMapDTO buildCalendarCostMapDTO(String resourceId,
                                                       String costMode,
                                                       String costMetric,
                                                       String calendarStartTime,
                                                       int calendarIntervalSize,
                                                       int calendarIntervalNumber,
                                                       int calendarIntervalIterations,
                                                       CalendarCostMappingsEntity calendarCostMappingsEntity) {
        String versionTag = calendarCostMappingsEntity.getVersionTag();

        CostTypeDTO costTypeDTO = new CostTypeDTO(CostModeDTO.fromString(costMode), CostMetricDTO.fromString(costMetric), null);
        VersionTagDTO versionTagDTO = new VersionTagDTO(resourceId, versionTag);
        CalendarAttributesDTO calendarAttributesDTO = new CalendarAttributesDTO(null, calendarStartTime, calendarIntervalSize, calendarIntervalNumber, calendarIntervalIterations);
        MetaDataDTO metaDataDTO = new MetaDataDTO(versionTagDTO, null, costTypeDTO, calendarAttributesDTO);
        Map<String, Map<String, List<Integer>>> costsMappings = buildSrcToDstCostsMap(calendarCostMappingsEntity.getFromSrcCostsEntities());

        return new CalendarCostMapDTO(metaDataDTO, costsMappings);
    }

    @Override
    public List<CalendarCostMapDTO> mapAllVersions(CalendarCostMapEntity calendarCostMapEntity) {
        List<CalendarCostMapDTO> calendarCostMapDTOList = new ArrayList<>();

        String resourceId = calendarCostMapEntity.getId();
        String costMode = calendarCostMapEntity.getCostMode();
        String costMetric = calendarCostMapEntity.getCostMetric();
        String calendarStartTime = calendarCostMapEntity.getCalendarStartTime();
        int calendarIntervalSize = calendarCostMapEntity.getCalendarIntervalSize();
        int calendarIntervalNumber = calendarCostMapEntity.getCalendarIntervalNumber();
        int calendarIntervalIterations = calendarCostMapEntity.getCalendarIterations();
        List<CalendarCostMappingsEntity> calendarCostMappingsEntities = calendarCostMapEntity.getCalendarCostMappingsEntities();

        for (CalendarCostMappingsEntity calendarCostMappingsEntity : calendarCostMappingsEntities) {
            calendarCostMapDTOList.add(buildCalendarCostMapDTO(resourceId, costMode, costMetric, calendarStartTime, calendarIntervalSize, calendarIntervalNumber, calendarIntervalIterations, calendarCostMappingsEntity));
        }

        return calendarCostMapDTOList;
    }

    @Override
    public CalendarCostMapDTO mapVersionAtPosition(CalendarCostMapEntity calendarCostMapEntity, int index) {
        String resourceId = calendarCostMapEntity.getId();
        String costMode = calendarCostMapEntity.getCostMode();
        String costMetric = calendarCostMapEntity.getCostMetric();
        String calendarStartTime = calendarCostMapEntity.getCalendarStartTime();
        int calendarIntervalSize = calendarCostMapEntity.getCalendarIntervalSize();
        int calendarIntervalNumber = calendarCostMapEntity.getCalendarIntervalNumber();
        int calendarIntervalIterations = calendarCostMapEntity.getCalendarIterations();
        List<CalendarCostMappingsEntity> calendarCostMappingsEntities = calendarCostMapEntity.getCalendarCostMappingsEntities();
        CalendarCostMappingsEntity firstCalendarCostMappingsEntity = calendarCostMappingsEntities.get(index);

        return buildCalendarCostMapDTO(resourceId, costMode, costMetric, calendarStartTime, calendarIntervalSize, calendarIntervalNumber, calendarIntervalIterations, firstCalendarCostMappingsEntity);
    }
}
