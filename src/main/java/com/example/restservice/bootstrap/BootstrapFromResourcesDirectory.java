package com.example.restservice.bootstrap;

import com.example.restservice.dto.networkmap.NetworkMapDTO;
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
    private static final Logger logger = LoggerFactory.getLogger(BootstrapFromResourcesDirectory.class);

    private NetworkMapRepository networkMapRepository;
    private ObjectMapper objectMapper;

    private List<NetworkMapDTO> networkMapDTOList;

    @Autowired
    public BootstrapFromResourcesDirectory(NetworkMapRepository networkMapRepository, ObjectMapper objectMapper) {
        this.networkMapRepository = networkMapRepository;
        this.objectMapper = objectMapper;
        this.networkMapDTOList = new ArrayList<>();
    }

    private void loadFileAsNetworkMap(File file) {
        logger.info("Loading network map " + file.getName());
        try {
            NetworkMapDTO networkMapDTO = objectMapper.readValue(file, NetworkMapDTO.class);
            networkMapDTOList.add(networkMapDTO);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void loadFilesAsNetworkMaps(File[] files) {
        for (File file : files) {
            if (file.isDirectory()) {
                logger.info("Stepping into subdirectory " + file.getAbsolutePath());
                loadFilesAsNetworkMaps(file.listFiles());
            } else {
                loadFileAsNetworkMap(file);
            }
        }
    }

    private void loadNetworkMaps(String baseDirectory) {
        logger.info("Loading network maps");
        loadFilesAsNetworkMaps(new File(baseDirectory).listFiles());
    }

    private void saveResources() {
        logger.info("Saving all resources");
        for (NetworkMapDTO networkMapDTO : networkMapDTOList) {
            logger.info("Saving " + networkMapDTO.getMetaDataDTO().getVersionTagDTO().getResourceId());
            networkMapRepository.insert(networkMapDTO);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Bootstrapping from resources directory");
        loadNetworkMaps(NETWORKMAPS_DIRECTORY);
        saveResources();
    }
}
