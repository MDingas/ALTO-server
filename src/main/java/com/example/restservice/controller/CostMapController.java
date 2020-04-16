package com.example.restservice.controller;

import com.example.restservice.dto.costmap.CostMapDTO;
import com.example.restservice.dto.costmap.CostMapFilterDTO;
import com.example.restservice.dto.costmap.SrcDstMappingsDTO;
import com.example.restservice.service.CostMapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.QueryParam;
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

    @RequestMapping(method = RequestMethod.GET, value = "{resourceId}")
    public CostMapDTO getCostMap(@PathVariable(value = "resourceId") String resourceId,
                                 @QueryParam(value = "version") String version) {
        logger.info(String.format("Request for retrieval of a single cost map (Resource_id=%s, version=%s)", resourceId, version));

        if (version != null) {
            try {
                return costMapService.getCostMap(resourceId, version);
            } catch (NotFoundException e) {
                logger.warn(String.format("Could not find cost map (resource_id=%s, version=%s)", resourceId, version));
                throw new NotFoundException("Could not find cost map with this version");
            }
        } else {
            try {
                return costMapService.getLatestCostMap(resourceId);
            } catch (NotFoundException e) {
                logger.warn(String.format("Could not find any version of %s", resourceId));
                throw new NotFoundException("Cost map does not have any versions");
            }
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "{resourceId}")
    public CostMapDTO getCostMapWithFilter(@PathVariable(value = "resourceId") String resourceId,
                                           @QueryParam(value = "version") String version,
                                           @Valid @RequestBody CostMapFilterDTO costMapFilterDTO) {
        logger.info(String.format("Request for retrieval of a single cost map with filter (resource_id=%s, version=%s)", resourceId, version));

        CostMapDTO costMapDTO;

        try {
            if (version != null) {
                costMapDTO = costMapService.getCostMap(resourceId, version, costMapFilterDTO);
            } else {
                costMapDTO = null;
                //costMapDTO = costMapService.getLatestCostMap(resourceId, costMapFilterDTO);
            }
        } catch (NotFoundException e) {
            logger.warn(String.format("Could not find cost map (resource_id=%s, version=%s)", resourceId, version));
            throw new NotFoundException("Could not find cost map");
        }
        return costMapDTO;
    }
}
