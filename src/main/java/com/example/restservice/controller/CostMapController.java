package com.example.restservice.controller;

import com.example.restservice.dto.costmap.CostMapDTO;
import com.example.restservice.dto.costmap.CostMapFilterDTO;
import com.example.restservice.service.costmap.CostMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
@RequestMapping("costmaps")
public class CostMapController {

    private CostMapService costMapService;

    @Autowired
    public CostMapController(CostMapService costMapService) {
        this.costMapService = costMapService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    public List<CostMapDTO> getCostMaps() {
        return costMapService.getAllCostMaps();
    }

    @RequestMapping(method = RequestMethod.GET, value = "{resourceId}")
    public CostMapDTO getCostMap(@PathVariable(value = "resourceId") String resourceId,
                                 @QueryParam(value = "version") String version) {
        if (version != null) {
            try {
                return costMapService.getCostMap(resourceId, version);
            } catch (NotFoundException e) {
                throw new NotFoundException("Could not find cost map with this version");
            }
        } else {
            try {
                return costMapService.getLatestCostMap(resourceId);
            } catch (NotFoundException e) {
                throw new NotFoundException("Cost map does not have any versions");
            }
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "{resourceId}")
    public CostMapDTO getCostMapWithFilter(@PathVariable(value = "resourceId") String resourceId,
                                           @QueryParam(value = "version") String version,
                                           @Valid @RequestBody CostMapFilterDTO costMapFilterDTO) {
        CostMapDTO costMapDTO;

        try {
            if (version != null) {
                costMapDTO = costMapService.getCostMap(resourceId, version, costMapFilterDTO);
            } else {
                costMapDTO = null;
                //costMapDTO = costMapService.getLatestCostMap(resourceId, costMapFilterDTO);
            }
        } catch (NotFoundException e) {
            throw new NotFoundException("Could not find cost map");
        }
        return costMapDTO;
    }
}
