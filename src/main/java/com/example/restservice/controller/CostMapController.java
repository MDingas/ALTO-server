package com.example.restservice.controller;

import com.example.restservice.dto.costmap.CostMapDTO;
import com.example.restservice.service.CostMapService;
import com.example.restservice.service.NetworkMapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("costmaps")
public class CostMapController {

    private static final Logger logger = LoggerFactory.getLogger(CostMapController.class);

    private CostMapService costMapService;

    @Autowired
    public CostMapController(CostMapService costMapService) {
        this.costMapService = costMapService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    public List<CostMapDTO> getCostMaps() {
        logger.info("Request for retrieval of all cost maps");
        return costMapService.getAllCostMaps();
    }
}
