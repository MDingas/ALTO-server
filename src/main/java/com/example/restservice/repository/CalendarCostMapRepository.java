package com.example.restservice.repository;

import com.example.restservice.dto.calendarcostmap.CalendarCostMapDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarCostMapRepository extends MongoRepository<CalendarCostMapDTO, String> {
}
