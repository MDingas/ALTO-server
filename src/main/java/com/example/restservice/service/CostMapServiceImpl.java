package com.example.restservice.service;

import com.example.restservice.dto.costmap.CostMapDTO;
import com.example.restservice.repository.CostMapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CostMapServiceImpl implements CostMapService {

    private CostMapRepository costMapRepository;

    @Autowired
    public CostMapServiceImpl(CostMapRepository costMapRepository) {
        this.costMapRepository = costMapRepository;
    }

    @Override
    public List<CostMapDTO> getAllCostMaps() {
        return costMapRepository.findAll();
    }
}
