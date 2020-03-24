package com.example.restservice.bootstrap;

import com.example.restservice.dto.costmap.CostMapDTO;
import com.example.restservice.dto.networkmap.NetworkMapDTO;
import com.example.restservice.repository.CostMapRepository;
import com.example.restservice.repository.NetworkMapRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class BootstrapFromResourcesDirectory implements CommandLineRunner {
    private static final String RESOURCES_DICECTORY = "resources";
    private static final String NETWORKMAPS_DIRECTORY = RESOURCES_DICECTORY + "/networkmaps";
    private static final String COSTMAP_DIRECTORY = RESOURCES_DICECTORY + "/costmaps";

    private static final Logger logger = LoggerFactory.getLogger(BootstrapFromResourcesDirectory.class);

    private NetworkMapRepository networkMapRepository;
    private CostMapRepository costMapRepository;

    private ObjectMapper objectMapper;

    @Autowired
    public BootstrapFromResourcesDirectory(NetworkMapRepository networkMapRepository, CostMapRepository costMapRepository, ObjectMapper objectMapper) {
        this.networkMapRepository = networkMapRepository;
        this.costMapRepository = costMapRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Bootstrapping from resources directory");

        logger.info("Loading network maps");
        List<NetworkMapDTO> networkMapDTOList = loadFilesAsObjectsByDepthSearch(NETWORKMAPS_DIRECTORY, NetworkMapDTO.class);

        logger.info("Loading cost maps");
        List<CostMapDTO> costMapDTOList = loadFilesAsObjectsByDepthSearch(COSTMAP_DIRECTORY, CostMapDTO.class);

        logger.info("Saving network maps");
        networkMapRepository.insert(networkMapDTOList);

        logger.info("Saving cost maps");
        costMapRepository.insert(costMapDTOList);
    }

    private <T> List<T> loadFilesAsObjectsByDepthSearch(String baseDirectory, Class<T> objectClass) {
        File[] fileList = new File(baseDirectory).listFiles();
        return loadFilesAsObjects(fileList, objectClass);
    }


    private <T> List<T> loadFilesAsObjects(File[] fileList, Class <T> objectClass) {
        List<T> loadedResources = new ArrayList<>();
        for (File file : fileList) {
            if (file.isDirectory()) {
                List<T> recursiveLoadedResources = loadFilesAsObjects(file.listFiles(), objectClass);
                loadedResources.addAll(recursiveLoadedResources);
            } else {
                T resource = loadFileAsObject(file, objectClass);
                loadedResources.add(resource);
            }
        }

        return loadedResources;
    }

    private <T> T loadFileAsObject(File file, Class<T> objectClass) {
        T resource = null;
        try {
            resource = objectMapper.readValue(file, objectClass);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

        return resource;
    }
}
