package com.example.restservice.bootstrap;

import com.example.restservice.entity.CostMapEntity;
import com.example.restservice.entity.NetworkMapEntity;
import com.example.restservice.repository.CalendarCostMapRepository;
import com.example.restservice.repository.CostMapRepository;
import com.example.restservice.repository.InformationResourceDirectoryRepository;
import com.example.restservice.repository.NetworkMapRepository;
import com.example.restservice.validation.BeanValidationDeserializer;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.BeanDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//@Component
public class BootstrapFromResourcesDirectory implements CommandLineRunner {
    private static final String RESOURCES_DICECTORY = "resources/database-jsons";
    //private static final String INFORMATION_RESOURCE_DIRECTORY_DIRECTORY = RESOURCES_DICECTORY + "/informationresourcedirectories";
    private static final String NETWORKMAPS_DIRECTORY = RESOURCES_DICECTORY + "/networkmaps";
    private static final String COSTMAP_DIRECTORY = RESOURCES_DICECTORY + "/costmaps";
    //private static final String CALENDAR_COSTMAP_DIRECTORY = RESOURCES_DICECTORY + "/calendarcostmaps";

    //private List<InformationResourceDirectoryDTO> informationResourceDirectoryDTOList;
    private List<NetworkMapEntity> networkMapEntities;
    private List<CostMapEntity> costMapEntities;
    //private List<CalendarCostMapDTO> calendarCostMapDTOList;

    private static final Logger logger = LoggerFactory.getLogger(BootstrapFromResourcesDirectory.class);

    private InformationResourceDirectoryRepository informationResourceDirectoryRepository;
    private NetworkMapRepository networkMapRepository;
    private CostMapRepository costMapRepository;
    private CalendarCostMapRepository calendarCostMapRepository;

    private ObjectMapper objectMapper;

    @Autowired
    public BootstrapFromResourcesDirectory(InformationResourceDirectoryRepository informationResourceDirectoryRepository,
                                           NetworkMapRepository networkMapRepository,
                                           CostMapRepository costMapRepository,
                                           CalendarCostMapRepository calendarCostMapRepository,
                                           ObjectMapper objectMapper) {
        this.informationResourceDirectoryRepository = informationResourceDirectoryRepository;
        this.networkMapRepository = networkMapRepository;
        this.costMapRepository = costMapRepository;
        this.calendarCostMapRepository = calendarCostMapRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        loadResources();
        saveResources();
    }

    private void loadResources() {
        logger.info("Bootstrapping objects from resources directory");

        //logger.info("Loading information resource directories");
        //informationResourceDirectoryDTOList = loadFilesAsObjectsByDepthSearch(INFORMATION_RESOURCE_DIRECTORY_DIRECTORY, InformationResourceDirectoryDTO.class);

        logger.info("Loading network maps");
        networkMapEntities = loadFilesAsObjectsByDepthSearch(NETWORKMAPS_DIRECTORY, NetworkMapEntity.class);

        logger.info("Loading cost maps");
        costMapEntities = loadFilesAsObjectsByDepthSearch(COSTMAP_DIRECTORY, CostMapEntity.class);

        //logger.info("Loading calendar cost maps");
        //calendarCostMapDTOList = loadFilesAsObjectsByDepthSearch(CALENDAR_COSTMAP_DIRECTORY, CalendarCostMapDTO.class);
    }

    private void saveResources() {
        logger.info("Saving information resource directories");

        //logger.info("Saving information resource directories");
        //informationResourceDirectoryRepository.insert(informationResourceDirectoryDTOList);

        logger.info("Saving network maps");
        networkMapRepository.insertAll(networkMapEntities);

        logger.info("Saving cost maps");
        costMapRepository.insertAll(costMapEntities);

        //logger.info("Saving calendar cost maps");
        //calendarCostMapRepository.insert(calendarCostMapDTOList);
    }

    private void configureObjectMapper() {
        SimpleModule validationModule = new SimpleModule();
        validationModule.setDeserializerModifier(new BeanDeserializerModifier() {
            @Override
            public JsonDeserializer<?> modifyDeserializer(DeserializationConfig config, BeanDescription beanDesc, JsonDeserializer<?> deserializer) {
                if (deserializer instanceof BeanDeserializer) {
                    return new BeanValidationDeserializer((BeanDeserializer) deserializer);
                }
                return deserializer;
            }
        });
        objectMapper.registerModule(validationModule);
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
        logger.info(String.format("Loading %s as a %s", file.getName(), objectClass.getName())) ;
        T resource = null;
        try {
            resource = objectMapper.readValue(file, objectClass);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

        return resource;
    }
}
