package com.example.restservice.repository.costmap;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

public class CostMapProjection {

    private String costMode;
    private String costMetric;
    private List<String> srcPIDs;
    private List<String> dstPIDs;

    private CostMapProjection(String costMode, String costMetric, List<String> srcPIDs, List<String> dstPIDs) {
        this.costMode = costMode;
        this.costMetric = costMetric;
        this.srcPIDs = srcPIDs;
        this.dstPIDs = dstPIDs;
    }

    public Optional<String> getCostMode() {
        return Optional.ofNullable(costMode);
    }

    public Optional<String> getCostMetric() {
        return Optional.ofNullable(costMetric);
    }

    public Optional<List<String>> getSrcPIDs() {
        return Optional.ofNullable(srcPIDs);
    }

    public Optional<List<String>> getDstPIDs() {
        return Optional.ofNullable(dstPIDs);
    }

    @Component
    public static class CostMapProjectionBuilder {

        private String costMode;
        private String costMetric;
        private List<String> srcPIDs;
        private List<String> dstPIDs;

        public CostMapProjectionBuilder() {

        }

        public void setCostMode(String costMode) {
            this.costMode = costMode;
        }

        public void setCostMetric(String costMetric) {
            this.costMetric = costMetric;
        }

        public void setSrcPIDs(List<String> srcPIDs) {
            this.srcPIDs = srcPIDs;
        }

        public void setDstPIDs(List<String> dstPIDs) {
            this.dstPIDs = dstPIDs;
        }

        public void clear() {
            this.costMode = null;
            this.costMetric = null;
            this.srcPIDs = null;
            this.dstPIDs = null;
        }

        public CostMapProjection build() {
            return new CostMapProjection(costMode, costMetric, srcPIDs, dstPIDs);
        }
    }
}
