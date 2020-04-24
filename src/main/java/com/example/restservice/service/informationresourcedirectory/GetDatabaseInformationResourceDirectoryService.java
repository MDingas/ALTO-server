package com.example.restservice.service.informationresourcedirectory;

import com.example.restservice.dto.informationresourcedirectory.InformationResourceDirectoryDTO;
import com.example.restservice.exception.MultipleInformationResourceDirectoriesException;
import com.example.restservice.repository.informationresourcedirectory.InformationResourceDirectoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.NotFoundException;
import java.util.List;

@Component
public class GetDatabaseInformationResourceDirectoryService implements InformationResourceDirectoryService {

    private InformationResourceDirectoryRepository informationResourceDirectoryRepository;

    @Autowired
    public GetDatabaseInformationResourceDirectoryService(InformationResourceDirectoryRepository informationResourceDirectoryRepository) {
        this.informationResourceDirectoryRepository = informationResourceDirectoryRepository;
    }

    @Override
    public InformationResourceDirectoryDTO getInformationResourceDirectory() throws NotFoundException , MultipleInformationResourceDirectoriesException {
        List<InformationResourceDirectoryDTO> informationResourceDirectoryDTOList = informationResourceDirectoryRepository.findAll();
        int irdCount = informationResourceDirectoryDTOList.size();

        switch (irdCount) {
            case 0:
                throw new NotFoundException("Could not find saved Information Resource Directory");
            case 1:
                return informationResourceDirectoryDTOList.get(0);
            default:
                throw new MultipleInformationResourceDirectoriesException(irdCount);
        }
    }
}
