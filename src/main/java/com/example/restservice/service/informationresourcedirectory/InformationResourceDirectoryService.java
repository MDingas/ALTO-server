package com.example.restservice.service.informationresourcedirectory;

import com.example.restservice.dto.informationresourcedirectory.InformationResourceDirectoryDTO;
import com.example.restservice.exception.MultipleInformationResourceDirectoriesException;

import javax.ws.rs.NotFoundException;

public interface InformationResourceDirectoryService {
    InformationResourceDirectoryDTO getInformationResourceDirectory() throws NotFoundException, MultipleInformationResourceDirectoriesException;
}
