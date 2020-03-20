package com.example.restservice.entities;

import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class AbsALTONetworkMap extends ALTOResource {
    private static final MediaType MEDIA_TYPE = new MediaType("application", "alto-networkmap");
    private static final Set<MediaType> ACCEPT_TYPES = new HashSet(Collections.singletonList(new MediaType("application", "alto-networkmapfilter")));

    public AbsALTONetworkMap(String name, String tag) {
        super(MEDIA_TYPE, ACCEPT_TYPES, name, tag);
    }
}
