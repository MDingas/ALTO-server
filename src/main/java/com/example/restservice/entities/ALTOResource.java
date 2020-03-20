package com.example.restservice.entities;

import javax.ws.rs.core.MediaType;
import java.util.Set;

/**
 * Abstract definition of an ALTO Resource. Concrete definitions (such as network or cost map),
 * must extend from this class
 */
public abstract class ALTOResource {
    private final MediaType mediaType;

    // It is usually one, or two if including a type to signal errors
    private final Set<MediaType> acceptTypes;

    private final String name;

    // Differentiates resources with the same name. Suggested to use a timestamp of current date
    // Final variable because if one wants to change the body of an ALTO resource, another integral
    // copy is created with a new tag, to preserve the old value
    private final String tag;

    public ALTOResource(MediaType mediaType, Set<MediaType> acceptTypes, String name, String tag) {
        this.mediaType = mediaType;
        this.acceptTypes = acceptTypes;
        this.name = name;
        this.tag = tag;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public Set<MediaType> getAcceptTypes() {
        return acceptTypes;
    }

    public String getName() {
        return name;
    }

    public String getTag() {
        return tag;
    }

}
