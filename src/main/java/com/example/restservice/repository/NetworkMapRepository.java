package com.example.restservice.repository;

import com.example.restservice.dto.NetworkMapDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NetworkMapRepository extends MongoRepository<NetworkMapDTO, String> {
}
