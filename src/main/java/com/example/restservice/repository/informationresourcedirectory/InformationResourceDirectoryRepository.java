package com.example.restservice.repository.informationresourcedirectory;

import com.example.restservice.dto.informationresourcedirectory.InformationResourceDirectoryDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformationResourceDirectoryRepository extends MongoRepository<InformationResourceDirectoryDTO, String> {
}
