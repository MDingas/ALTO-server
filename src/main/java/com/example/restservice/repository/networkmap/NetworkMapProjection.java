package com.example.restservice.repository.networkmap;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

public class NetworkMapProjection {

    private List<String> srcPIDs;

    private NetworkMapProjection(List<String> srcPIDs) {
        this.srcPIDs = srcPIDs;
    }

    public Optional<List<String>> getSrcPIDs() {
        return Optional.ofNullable(srcPIDs);
    }

    @Component
    public static class NetworkMapProjectionBuilder {

        private List<String> srcPIDs;

        public NetworkMapProjectionBuilder() {

        }

        public void setSrcPIDs(List<String> srcPIDs) {
            this.srcPIDs = srcPIDs;
        }

        public void clear() {
            this.srcPIDs = null;
        }

        public NetworkMapProjection build() {
            return new NetworkMapProjection(srcPIDs);
        }
    }
}
