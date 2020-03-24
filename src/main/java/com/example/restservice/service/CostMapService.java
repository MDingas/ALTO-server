package com.example.restservice.service;

import com.example.restservice.dto.costmap.CostMapDTO;

import java.util.List;

public interface CostMapService {
    List<CostMapDTO> getAllCostMaps();
}
