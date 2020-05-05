package com.example.restservice.repository.calendarcostmap;

import com.example.restservice.entity.calendarcostmap.CalendarCostMapEntity;
import com.example.restservice.repository.ALTOResourceMongoRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CalendarCostMapMongoRepository extends ALTOResourceMongoRepository<CalendarCostMapEntity> implements CalendarCostMapRepository {
    public CalendarCostMapMongoRepository(MongoTemplate mongoTemplate) {
        super(CalendarCostMapEntity.class, mongoTemplate);
    }
}
