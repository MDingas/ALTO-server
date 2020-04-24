package com.example.restservice.controller;

import com.example.restservice.dto.informationresourcedirectory.InformationResourceDirectoryDTO;
import com.example.restservice.exception.MultipleInformationResourceDirectoriesException;
import com.example.restservice.service.informationresourcedirectory.InformationResourceDirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.NotFoundException;

@RestController
@RequestMapping("")
public class InformationResourceDirectoryController {

    private InformationResourceDirectoryService informationResourceDirectoryService;

    @Autowired
    public InformationResourceDirectoryController(InformationResourceDirectoryService informationResourceDirectoryService) {
        this.informationResourceDirectoryService = informationResourceDirectoryService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    public InformationResourceDirectoryDTO getInformationResourceDirectory() throws MultipleInformationResourceDirectoriesException {
        InformationResourceDirectoryDTO informationResourceDirectoryDTO;

        try {
            informationResourceDirectoryDTO = informationResourceDirectoryService.getInformationResourceDirectory();
        } catch (NotFoundException e) {
            throw new NotFoundException("Could not find or create Information Resource Directory");
        } catch (MultipleInformationResourceDirectoriesException e) {
            throw new MultipleInformationResourceDirectoriesException(e.getExistingIRDsCount());
        }

        return informationResourceDirectoryDTO;
    }
}
