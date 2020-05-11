package com.example.restservice.entity.calendarcostmap;

import org.springframework.data.mongodb.core.mapping.Field;

public class MetaInfoEntity {
    @Field("resource-id")
    private String resourceId;

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

    public MetaInfoEntity(String resourceId, String costMode, String costMetric, String calendarStartTime, int calendarIntervalSize, int calendarIntervalNumber, int calendarIterations) {
        this.resourceId = resourceId;
        this.costMode = costMode;
        this.costMetric = costMetric;
        this.calendarStartTime = calendarStartTime;
        this.calendarIntervalSize = calendarIntervalSize;
        this.calendarIntervalNumber = calendarIntervalNumber;
        this.calendarIterations = calendarIterations;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
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
}
