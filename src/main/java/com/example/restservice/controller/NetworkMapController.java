package com.example.restservice.controller;

import com.example.restservice.dto.networkmap.NetworkMapDTO;
import com.example.restservice.dto.networkmap.NetworkMapFilterDTO;
import com.example.restservice.service.networkmap.NetworkMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("networkmaps")
public class NetworkMapController extends ALTOResourceCRUDController<NetworkMapDTO, NetworkMapFilterDTO, NetworkMapService> {

    @Autowired
    public NetworkMapController(NetworkMapService networkMapService) {
        super(networkMapService, "Network Map");
    }
}
