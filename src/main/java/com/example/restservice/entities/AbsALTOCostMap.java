package com.example.restservice.entities;

import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public abstract class AbsALTOCostMap extends ALTOResource {
    private static final MediaType MEDIA_TYPE = new MediaType("application", "alto-costmap");
    private static final Set<MediaType> ACCEPT_TYPES = new HashSet(Collections.singletonList(new MediaType("application", "alto-costmapfilter")));

    private final CostMode mode;
    private final CostMetric metric;
    private final Optional<String> description;

    public AbsALTOCostMap(String name, String tag, CostMode mode, CostMetric metric, String description) {
        super(MEDIA_TYPE, ACCEPT_TYPES, name, tag);
        this.mode = mode;
        this.metric = metric;
        this.description = Optional.ofNullable(description);
    }

    public AbsALTOCostMap(String name, String tag, CostMode mode, CostMetric metric) {
        this(name, tag, mode, metric, null);
    }

    public CostMode getMode() {
        return this.mode;
    }

    public CostMetric getMetric() {
        return this.metric;
    }

    public Optional<String> returnDescription() {
        return this.description;
    }
}

