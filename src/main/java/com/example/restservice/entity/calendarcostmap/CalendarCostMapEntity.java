package com.example.restservice.entity.calendarcostmap;

import com.example.restservice.entity.ALTOResourceEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "CalendarCostMaps")
public class CalendarCostMapEntity implements ALTOResourceEntity {

    @Field("meta")
    private MetaInfoEntity metaInfoEntity;

    @Field("mappings")
    private List<CalendarCostMappingsEntity> calendarCostMappingsEntities;

    public CalendarCostMapEntity(MetaInfoEntity metaInfoEntity,
                                 List<CalendarCostMappingsEntity> calendarCostMappingsEntities) {
        this.metaInfoEntity = metaInfoEntity;
        this.calendarCostMappingsEntities = calendarCostMappingsEntities;
    }

    public MetaInfoEntity getMetaInfoEntity() {
        return metaInfoEntity;
    }

    public void setMetaInfoEntity(MetaInfoEntity metaInfoEntity) {
        this.metaInfoEntity = metaInfoEntity;
    }

    public List<CalendarCostMappingsEntity> getCalendarCostMappingsEntities() {
        return calendarCostMappingsEntities;
    }

    public void setCalendarCostMappingsEntities(List<CalendarCostMappingsEntity> calendarCostMappingsEntities) {
        this.calendarCostMappingsEntities = calendarCostMappingsEntities;
    }

    @Override
    public String getId() {
        return metaInfoEntity.getResourceId();
    }
}
