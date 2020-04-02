package com.example.restservice.controller;

import com.example.restservice.dto.informationresourcedirectory.InformationResourceDirectoryDTO;
import com.example.restservice.exception.MultipleInformationResourceDirectoriesException;
import com.example.restservice.service.InformationResourceDirectoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.NotFoundException;

@RestController
@RequestMapping("")
public class InformationResourceDirectoryController {

    private static final Logger logger = LoggerFactory.getLogger(CostMapController.class);

    private InformationResourceDirectoryService informationResourceDirectoryService;

    @Autowired
    public InformationResourceDirectoryController(InformationResourceDirectoryService informationResourceDirectoryService) {
        this.informationResourceDirectoryService = informationResourceDirectoryService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    public InformationResourceDirectoryDTO getInformationResourceDirectory() throws MultipleInformationResourceDirectoriesException {
        logger.info("Request for information resource directory");

        InformationResourceDirectoryDTO informationResourceDirectoryDTO;

        try {
            informationResourceDirectoryDTO = informationResourceDirectoryService.getInformationResourceDirectory();
        } catch (NotFoundException e) {
            logger.warn("Could not find or generate a single Information Resource Directory");
            throw new NotFoundException("Could not find or create Information Resource Directory");
        } catch (MultipleInformationResourceDirectoriesException e) {
            logger.warn("Found more than one Information Resource Directories when only one should exist");
            throw new MultipleInformationResourceDirectoriesException(e.getExistingIRDsCount());
        }

        return informationResourceDirectoryDTO;
    }
}
