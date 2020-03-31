package com.example.restservice.repository;

import com.example.restservice.dto.informationresourcedirectory.InformationResourceDirectoryDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformationResourceDirectoryRepository extends MongoRepository<InformationResourceDirectoryDTO, String> {
}
