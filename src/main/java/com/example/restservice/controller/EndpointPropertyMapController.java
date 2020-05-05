package com.example.restservice.controller;

import com.example.restservice.dto.endpointpropertymap.EndpointPropertyMapDTO;
import com.example.restservice.service.endpointpropertymap.EndpointPropertyMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("endpointpropmaps")
public class EndpointPropertyMapController {

    private EndpointPropertyMapService endpointPropertyMapService;

    @Autowired
    public EndpointPropertyMapController(EndpointPropertyMapService endpointPropertyMapService) {
        this.endpointPropertyMapService = endpointPropertyMapService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    public List<EndpointPropertyMapDTO> getEndpointPropertyMaps() {
        return endpointPropertyMapService.getAllEndpointPropertyMaps();
    }
}
