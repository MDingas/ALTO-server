package com.example.restservice.controller;

import com.example.restservice.dto.costmap.CostMapDTO;
import com.example.restservice.dto.costmap.CostMapFilterDTO;
import com.example.restservice.service.costmap.CostMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("costmaps")
public class CostMapController extends ALTOResourceCRUDController<CostMapDTO, CostMapFilterDTO, CostMapService> {

    @Autowired
    public CostMapController(CostMapService costMapService) {
        super(costMapService, "Cost Map");
    }
}
