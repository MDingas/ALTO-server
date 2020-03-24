package com.example.restservice.repository;

import com.example.restservice.dto.costmap.CostMapDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostMapRepository extends MongoRepository<CostMapDTO, String> {
}
