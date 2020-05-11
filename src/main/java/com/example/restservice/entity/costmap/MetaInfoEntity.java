package com.example.restservice.entity.costmap;

public class MetaInfoEntity {
    private String resourceId;

    private String costMode;

    private String costMetric;

    public MetaInfoEntity(String resourceId, String costMode, String costMetric) {
        this.resourceId = resourceId;
        this.costMode = costMode;
        this.costMetric = costMetric;
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
}
