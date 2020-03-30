package com.example.restservice.dto.calendarcostmap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.util.List;

public class CalendarAttributesDTO {

    @NotNull
    @Field("cost-type-names")
    private List<String> costTypeNames;

    @NotNull
    @Field("calendar-start-time")
    private String calendarStartTime;

    @NotNull
    @Field("time-interval-size")
    private int timeIntervalSize;

    @NotNull
    @Field("number-of-intervals")
    private int numberOfIntervals;

    @Field("repeated")
    private int numberOfRepetitions;

    @JsonCreator
    public CalendarAttributesDTO(@JsonProperty(value = "cost-type-names", required = false) List<String> costTypeNames,
                                 @JsonProperty(value = "calendar-start-time", required = true) String calendarStartTime,
                                 @JsonProperty(value = "time-interval-size", required = true) int timeIntervalSize,
                                 @JsonProperty(value = "number-of-intervals", required = true) int numberOfIntervals,
                                 @JsonProperty(value = "repeated", required = false) int numberOfRepetitions) {
        this.costTypeNames = costTypeNames;
        this.calendarStartTime = calendarStartTime;
        this.timeIntervalSize = timeIntervalSize;
        this.numberOfIntervals = numberOfIntervals;
        this.numberOfRepetitions = numberOfRepetitions;
    }

    public List<String> getCostTypeNames() {
        return costTypeNames;
    }

    public void setCostTypeNames(List<String> costTypeNames) {
        this.costTypeNames = costTypeNames;
    }

    public String getCalendarStartTime() {
        return calendarStartTime;
    }

    public void setCalendarStartTime(String calendarStartTime) {
        this.calendarStartTime = calendarStartTime;
    }

    public int getTimeIntervalSize() {
        return timeIntervalSize;
    }

    public void setTimeIntervalSize(int timeIntervalSize) {
        this.timeIntervalSize = timeIntervalSize;
    }

    public int getNumberOfIntervals() {
        return numberOfIntervals;
    }

    public void setNumberOfIntervals(int numberOfIntervals) {
        this.numberOfIntervals = numberOfIntervals;
    }

    public int getNumberOfRepetitions() {
        return numberOfRepetitions;
    }

    public void setNumberOfRepetitions(int numberOfRepetitions) {
        this.numberOfRepetitions = numberOfRepetitions;
    }
}
