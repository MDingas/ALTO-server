package com.example.restservice.entity.calendarcostmap;

import com.example.restservice.entity.ALTOResourceEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "CalendarCostMaps")
public class CalendarCostMapEntity implements ALTOResourceEntity {

    @Field("resource-id")
    private String id;

    @Field("cost-mode")
    private String costMode;

    @Field("cost-metric")
    private String costMetric;

    @Field("calendar-start-time")
    private String calendarStartTime;

    @Field("calendar-time-interval-size")
    private int calendarIntervalSize;

    @Field("calendar-interval-number")
    private int calendarIntervalNumber;

    @Field("iterations")
    private int calendarIterations;

    @Field("mappings")
    private List<CalendarCostMappingsEntity> calendarCostMappingsEntities;

    public CalendarCostMapEntity(String id,
                                 String costMode,
                                 String costMetric,
                                 String calendarStartTime,
                                 int calendarIntervalSize,
                                 int calendarIntervalNumber,
                                 int calendarIterations,
                                 List<CalendarCostMappingsEntity> calendarCostMappingsEntities) {
        this.id = id;
        this.costMode = costMode;
        this.costMetric = costMetric;
        this.calendarStartTime = calendarStartTime;
        this.calendarIntervalSize = calendarIntervalSize;
        this.calendarIntervalNumber = calendarIntervalNumber;
        this.calendarIterations = calendarIterations;
        this.calendarCostMappingsEntities = calendarCostMappingsEntities;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCostMode() {
        return costMode;
    }

    public void setCostMode(String costMode) {
        this.costMode = costMode;
    }

    public String getCostMetric() {
        return costMetric;
    }

    public void setCostMetric(String costMetric) {
        this.costMetric = costMetric;
    }

    public String getCalendarStartTime() {
        return calendarStartTime;
    }

    public void setCalendarStartTime(String calendarStartTime) {
        this.calendarStartTime = calendarStartTime;
    }

    public int getCalendarIntervalSize() {
        return calendarIntervalSize;
    }

    public void setCalendarIntervalSize(int calendarIntervalSize) {
        this.calendarIntervalSize = calendarIntervalSize;
    }

    public int getCalendarIntervalNumber() {
        return calendarIntervalNumber;
    }

    public void setCalendarIntervalNumber(int calendarIntervalNumber) {
        this.calendarIntervalNumber = calendarIntervalNumber;
    }

    public int getCalendarIterations() {
        return calendarIterations;
    }

    public void setCalendarIterations(int calendarIterations) {
        this.calendarIterations = calendarIterations;
    }

    public List<CalendarCostMappingsEntity> getCalendarCostMappingsEntities() {
        return calendarCostMappingsEntities;
    }

    public void setCalendarCostMappingsEntities(List<CalendarCostMappingsEntity> calendarCostMappingsEntities) {
        this.calendarCostMappingsEntities = calendarCostMappingsEntities;
    }
}
