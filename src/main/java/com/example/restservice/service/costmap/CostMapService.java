package com.example.restservice.service.costmap;

import com.example.restservice.dto.costmap.CostMapDTO;
import com.example.restservice.dto.costmap.CostMapFilterDTO;

import javax.ws.rs.NotFoundException;
import java.util.List;

public interface CostMapService {
    List<CostMapDTO> getAllCostMaps();
    CostMapDTO getCostMap(String resourceId, String version) throws NotFoundException;
    CostMapDTO getLatestCostMap(String resourceId) throws NotFoundException;
    CostMapDTO getCostMap(String resourceId, String version, CostMapFilterDTO costMapFilterDTO);
    CostMapDTO getLatestCostMap(String resourceId, CostMapFilterDTO costMapFilterDTO);
    //void putCostMapDTO(CostMapDTO costMapDTO);
}
