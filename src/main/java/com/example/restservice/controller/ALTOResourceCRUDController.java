package com.example.restservice.controller;

import com.example.restservice.service.ALTOResourceService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.QueryParam;
import java.util.List;

public class ALTOResourceCRUDController<ALTOResourceDTOType,
                                ALTOResourceFilterDTOType,
                                ServiceType extends ALTOResourceService<ALTOResourceDTOType, ALTOResourceFilterDTOType>> {

    protected ServiceType service;
    protected String resourceName;

    public ALTOResourceCRUDController(ServiceType service, String resourceName) {
        this.service = service;
        this.resourceName = resourceName;
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    public List<ALTOResourceDTOType> getAllResources() {
        return service.getAllResources();
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public ALTOResourceDTOType getResource(@PathVariable(value = "id") String id,
                                           @QueryParam(value = "version") String version) {
        if (version != null) {
            return service.getResource(id, version)
                    .orElseThrow(()
                            -> new NotFoundException(String.format("Could not find %s with id %s and version %s", resourceName, id, version)));
        } else {
            return service.getLatestResource(id)
                    .orElseThrow(()
                            -> new NotFoundException(String.format("Could not find any %s version with id %s", resourceName, id)));
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "{id}")
    public ALTOResourceDTOType getResourceWithFilter(@PathVariable(value = "id") String id,
                                                     @QueryParam(value = "version") String version,
                                                     @Valid @RequestBody ALTOResourceFilterDTOType filterDTO) {
        if (version != null) {
            return service.getResourceWithFilter(id, version, filterDTO)
                    .orElseThrow(()
                            -> new NotFoundException(String.format("Could not find %s with id %s and version %s and given filter", resourceName, id, version)));
        }
        else {
            return service.getLatestResourceWithFilter(id, filterDTO)
                    .orElseThrow(()
                            -> new NotFoundException(String.format("Could not find any %s version with id %s and given filter", resourceName, id)));
        }
    }
}
